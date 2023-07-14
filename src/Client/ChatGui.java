package Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    //엔터키 눌렸을때 실행될 코드
                    String text = textField.getText();
                    System.out.println(text);   //출력되는지 콘솔에 테스트
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
