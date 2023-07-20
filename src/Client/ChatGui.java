package Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

public class ChatGui extends JFrame {

    private JPanel contentPane;
    private JTextField textmsg;   //textmsg
    private TextArea chatlog;  //textArea
    private BufferedReader reader;
    public PrintWriter writer;


//    public static void main(String[] args) throws IOException {
//        ChatGui frame = new ChatGui();
//    }

    public ChatGui(String id) {
        String msg = "login/" + id;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 255, 255));
        panel.setBounds(0, 0, 685, 460);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("채팅방");
        lblNewLabel.setBounds(240, 10, 97, 15);
        panel.add(lblNewLabel);

        chatlog = new TextArea();
        chatlog.setEditable(false);
        chatlog.setText("채팅 로그입니다.");
        chatlog.setBounds(12, 26, 510, 393);
        panel.add(chatlog);

        textmsg = new JTextField();
        textmsg.setText("메세지를 입력하세요");
        textmsg.setBounds(12, 429, 510, 21);
        panel.add(textmsg);
        textmsg.setColumns(10);
        //엔터키 이벤트 처리
        textmsg.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //엔터키 눌렸을때 실행될 코드
                    String text = textmsg.getText();
                    //System.out.println(text);   //출력되는지 콘솔에 테스트
                    writer.println(text);// 텍스트를 서버로 전송
                    textmsg.setText(""); // 텍스트 필드의 값을 지움
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        setVisible(true);

        try {
            // text값 소켓통신으로 서버로 전송하는 코드
            // 서버 정보
            String serverIP = "localhost";
            //String serverIP = "192.168.0.17";
            int serverPort = 8888; // 서버 포트 번호

            // 서버에 연결
            Socket socket = new Socket(serverIP, serverPort);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 서버로부터 메시지를 읽는 스레드 시작
            Thread readThread = new Thread(new ServerMessageReader());
            readThread.start();

            // 서버로 데이터 전송
            OutputStream outputStream = socket.getOutputStream();
            writer = new PrintWriter(outputStream, true);
            writer.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void updateText(String message) {chatlog.append(message + "\n");}

    private class ServerMessageReader implements Runnable {
        @Override
        public void run() {
            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("서버로부터 메시지: " + message);
                    //textArea.append(message + "\n");
                    updateText(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}