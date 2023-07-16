package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiChatClient implements ActionListener, Runnable {
    private String ip;
    private String id;
    private Socket socket;
    private BufferedReader inMsg = null;
    private PrintWriter outMsg = null;

    // 로그인 패널
    private JPanel loginPanel;
    // 로그인 버튼
    private JButton loginButton;
    // 화면명 레이블
    private JLabel label1;
    // 화면명 입력 텍스트 필드
    private JTextField idInput;

    // 로그아웃 패널
    private JPanel logoutPanel;
    // 화면명 표시 레이블
    private JLabel label2;
    // 로그아웃 버튼
    private JButton logoutButton;

    // 입력 패널
    private JPanel msgPanel;
    // 메시지 입력 텍스트 필드
    private JTextField msgInput;
    // 전송 버튼
    private JButton exitButton;

    // 메인 프레임
    private JFrame jframe;
    // 채팅 메시지 출력용 텍스트 영역
    private JTextArea msgOut;

    // 카드 레이아웃과 컨테이너
    private Container tab;
    private CardLayout clayout;
    private Thread thread;

    // 연결 상태
    boolean status;

    public MultiChatClient(String ip) {
        this.ip = ip;

        // 로그인 패널 생성
        loginPanel = new JPanel();
        // 레이아웃 설정
        loginPanel.setLayout(new BorderLayout());
        idInput = new JTextField(15);
        loginButton = new JButton("로그인");
        // 이벤트 리스너 등록
        loginButton.addActionListener(this);
        label1 = new JLabel("아이디");
        // 컴포넌트 추가
        loginPanel.add(label1, BorderLayout.WEST);
        loginPanel.add(idInput, BorderLayout.CENTER);
        loginPanel.add(loginButton, BorderLayout.EAST);

        // 로그아웃 패널 생성
        logoutPanel = new JPanel();
        // 레이아웃 설정
        logoutPanel.setLayout(new BorderLayout());
        label2 = new JLabel();
        logoutButton = new JButton("로그아웃");
        // 이벤트 리스너 등록
        logoutButton.addActionListener(this);
        // 컴포넌트 추가
        logoutPanel.add(label2, BorderLayout.CENTER);
        logoutPanel.add(logoutButton, BorderLayout.EAST);

        // 입력 패널 생성
        msgPanel = new JPanel();
        // 레이아웃 설정
        msgPanel.setLayout(new BorderLayout());
        msgInput = new JTextField(30);
        // 이벤트 리스너 등록
        msgInput.addActionListener(this);
        exitButton = new JButton("전송");
        exitButton.addActionListener(this);
        // 컴포넌트 추가
        msgPanel.add(msgInput, BorderLayout.CENTER);
        msgPanel.add(exitButton, BorderLayout.EAST);

        // 로그인/로그아웃 패널을 전환하는 카드 레이아웃을 가진 컨테이너 생성
        tab = new JPanel();
        clayout = new CardLayout();
        tab.setLayout(clayout);
        tab.add(loginPanel, "login");
        tab.add(logoutPanel, "logout");

        // 메인 프레임 생성
        jframe = new JFrame("::멀티챗::");
        msgOut = new JTextArea("", 10, 30);
        // JTextArea는 편집이 불가능하도록 설정
        msgOut.setEditable(false);
        // 스크롤 가능하도록 스크롤 패널 추가
        JScrollPane jsp = new JScrollPane(msgOut, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jframe.add(tab, BorderLayout.NORTH);
        jframe.add(jsp, BorderLayout.CENTER);
        jframe.add(msgPanel, BorderLayout.SOUTH);
        // 로그인 패널을 처음에 보이도록 설정
        clayout.show(tab, "login");
        // 프레임 크기 자동 조절
        jframe.pack();
        // 프레임 크기 조절 비활성화
        jframe.setResizable(false);
        // 프레임 표시
        jframe.setVisible(true);
    }

    public void connectServer() {
        try {
            // 서버에 접속
            socket = new Socket(ip, 8888);
            System.out.println("[Client] 서버에 접속!");

            // 입출력 스트림 생성
            inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outMsg = new PrintWriter(socket.getOutputStream(), true);

            // 로그인 메시지 전송
            outMsg.println("login" + "/" + id);

            // 메시지 수신을 처리하는 스레드 시작
            thread = new Thread(this);
            thread.start();
        } catch (Exception e) {
            // 예외 처리
            System.out.println("[MultiChatClient] connectServer() Exception 발생!!");
        }
    }

    // 이벤트 처리
    public void actionPerformed(ActionEvent arg0) {
        Object obj = arg0.getSource();

        // 종료 버튼 클릭
        if (obj == exitButton) {
            outMsg.println(".quit");
            jframe.dispose();
            System.exit(0);
        }
        // 로그인 버튼 클릭
        else if (obj == loginButton) {
            id = idInput.getText();

            label2.setText("아이디: " + id);
            clayout.show(tab, "logout");
            connectServer();
        }
        // 로그아웃 버튼 클릭
        else if (obj == logoutButton) {
            // 로그아웃 메시지를 서버에 전송
            outMsg.println(".quit");
            // 채팅 메시지 영역 초기화
            msgOut.setText("");
            // 로그인 패널로 전환
            clayout.show(tab, "login");
            outMsg.close();
            try {
                inMsg.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            status = false;
        }
        // 메시지 입력 텍스트 필드에서 엔터 키 입력
        else if (obj == msgInput) {
            // 메시지 전송
            outMsg.println(msgInput.getText());
            // 입력 필드 초기화
            msgInput.setText("");
        }
    }

    public void run() {
        // 서버로부터 받은 메시지를 처리하는 메서드
        String msg;
        String[] rmsg;

        status = true;

        while (status) {
            try {
                // 메시지 수신
                msg = inMsg.readLine();
                rmsg = msg.split("/");

                // JTextArea에 메시지 추가
                msgOut.append(msg + "\n");

                // 스크롤을 가장 아래로 이동하여 최신 메시지 보이기
                msgOut.setCaretPosition(msgOut.getDocument().getLength());
            } catch (IOException e) {
                // 에러 발생 시 연결 종료
                status = false;
            }
        }

        System.out.println("[MultiChatClient] " + thread.getName() + " 종료");
    }

    public static void main(String[] args) {
        MultiChatClient mcc = new MultiChatClient("127.0.0.1");
    }
}