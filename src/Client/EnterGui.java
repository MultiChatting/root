package Client;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

public class EnterGui extends JFrame {

    private JPanel enterPane; // 전체틀 생성
    private JTextField textField; // 닉네임 읽어오는 텍스트 필드
    private JButton enterButton; // 입력 버튼 이벤트 처리
    private String name; // 닉네임


    /**
     * Create the frame.
     */
    public EnterGui() {
        enterPanel(); // 전체틀 생성
        nickNameLabel(); // 닉네임 입력 라벨, 텍스트 필드 생성
        enterButtonEvent(); // 입장 버튼 생성 및 처리
        setVisible(true); // Gui 켜기

    }

    public void enterPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 패널 닫는 코드
        setBounds(100, 100, 450, 300); // 패널 사이즈 코드
        enterPane = new JPanel();
        enterPane.setBackground(new Color(120, 255, 255));
        enterPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(enterPane);
        enterPane.setLayout(null);
    }

    public void nickNameLabel() {
        JLabel nickName = new JLabel("닉네임 입력");
        nickName.setBounds(180, 60, 80, 15);
        enterPane.add(nickName);

        textField = new JTextField();
        textField.setBounds(155, 75, 115, 20);
        enterPane.add(textField);
        textField.setColumns(10); // 글자수 제한
    }


    public void enterButtonEvent() {
        enterButton = new JButton("입장");
        enterButton.addActionListener(new ActionListener() {
            // 입장 버튼 클릭시 이벤트 처리
            public void actionPerformed(ActionEvent e) {
                sendToChatGui();
            }
        });
        enterButton.setBounds(165, 134, 97, 23);
        enterPane.add(enterButton);

        // 엔터 입력시 입장 처리
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                // 엔터키 입력시 이벤트 처리
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendToChatGui();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void sendToChatGui() {
        name = textField.getText(); // 닉네임 name 읽어오기
        setVisible(false); // EnterGui 창 끄기
        new ChatGui(name); // ChatGui에 닉네임 name 값 전달
    }

}