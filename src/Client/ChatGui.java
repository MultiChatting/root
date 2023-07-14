package Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatGui extends JFrame {

    private JPanel contentPane;
    private JTextField textField;

//    public static void main(String[] args) {
//        ChatGui frame = new ChatGui();
//        frame.setVisible(true);
//    }

    //ChatGui chat = new ChatGui();
    //chat.setVisible(true);

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
