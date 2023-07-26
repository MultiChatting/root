package Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

public class ChatGui extends JFrame {

    private JPanel chatPanel;
    private JTextField textMsg;
    private TextArea chatLog;
    private BufferedReader reader;
    public PrintWriter writer;

    //ChatGui 구현
    public ChatGui(String id) {
        // EnterGui에서 보내는 닉네임 값을 매개변수로 받음
        String msg = "login/" + id;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 500);
        chatPanel = new JPanel();
        chatPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(chatPanel);
        chatPanel.setBackground(new Color(128, 255, 255));
        chatPanel.setLayout(null);

        // chatLabel과 chatLog
        JLabel chatLabel = new JLabel("채팅방"); // chatLabel 생성
        chatLabel.setBounds(240, 10, 95, 15);
        chatPanel.add(chatLabel);

        chatLog = new TextArea(); // chatLog 생성
        chatLog.setEditable(false);
        chatLog.setText("채팅 로그입니다.");
        chatLog.setBounds(10, 25, 500, 400);
        chatPanel.add(chatLog);

        textMsg = new JTextField();
        textMsg.setText("메세지를 입력하세요");
        textMsg.setBounds(10, 430, 510, 20);
        chatPanel.add(textMsg);
        textMsg.setColumns(10);

//        메세지 전송 엔터키 이벤트 처리
        textMsg.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //엔터키 눌렸을때 실행될 코드
                    String text = textMsg.getText();
                    writer.println(text);// 텍스트를 서버로 전송
                    textMsg.setText(""); // 텍스트 필드의 값 지움 => 초기화
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setVisible(true);
        try {
            // 소켓통신으로 서버로 메세지 전송하는 코드
            // 서버 정보
            String serverIP = "localhost";
//            String serverIP = "192.168.0.17";
            int serverPort = 8888; // 서버 포트 번호

            // 서버에 연결
            Socket socket = new Socket(serverIP, serverPort);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 서버로부터 메시지를 읽는 스레드 시작
            Thread readThread = new Thread(new ServerMessageReader());
            readThread.start();

            // 서버로 메세지 전송
            OutputStream outputStream = socket.getOutputStream();
            writer = new PrintWriter(outputStream, true);
            writer.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //서버에서 주는 메세지 처리
    private class ServerMessageReader implements Runnable {
        @Override
        public void run() {
            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("서버로부터 메시지: " + message);
                    uploadText(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //받은 메세지 채팅창에 업로드
    public void uploadText(String message) {
        chatLog.append(message + "\n");
    }
}