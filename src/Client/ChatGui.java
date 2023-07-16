package Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatGui extends JFrame {

    private JPanel contentPane;
    private JTextField textField;

    public static void main(String[] args) {
        ChatGui frame = new ChatGui();
        frame.setVisible(true);
    }


    public ChatGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 255, 255));
        panel.setBounds(0, 0, 685, 460);
        contentPane.add(panel);
        panel.setLayout(null);

        textField = new JTextField();
        //엔터키 이벤트 처리
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //엔터키 눌렸을때 실행될 코드
                    String text = textField.getText();
                    System.out.println(text);   //출력되는지 콘솔에 테스트

                    // text값 소켓통신으로 서버로 전송하는 코드
                    // 서버 정보
                    String serverIP = "localhost";
                    int serverPort = 8888; // 서버 포트 번호

                    try {
                        // 서버에 연결
                        Socket socket = new Socket(serverIP, serverPort);

                        // 서버로 데이터 전송
                        OutputStream outputStream = socket.getOutputStream();
                        PrintWriter writer = new PrintWriter(outputStream, true);
                        writer.println(text); // 텍스트를 서버로 전송

                        // 연결 종료
                        writer.close();
                        outputStream.close();
                        socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    textField.setText(""); // 텍스트 필드의 값을 지움
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        textField.setText("메세지를 입력하세요");
        textField.setBounds(12, 429, 661, 21);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("채팅방 입장");
        lblNewLabel.setBounds(280, 10, 97, 15);
        panel.add(lblNewLabel);

        JTextArea textArea = new JTextArea();
        textArea.setText("채팅 로그입니다.");
        textArea.setBounds(12, 26, 510, 393);
        panel.add(textArea);

        JTextArea textArea_1 = new JTextArea();
        textArea_1.setText("접속 인원");
        textArea_1.setBounds(534, 26, 139, 393);
        panel.add(textArea_1);
    }
}
