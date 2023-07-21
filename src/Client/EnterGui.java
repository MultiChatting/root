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

    private JPanel contentPane; // 전체틀 생성
    private JTextField textField; // 닉네임 읽어오는 텍스트 필드
    private JButton btnNewButton; // 입력 버튼 이벤트 처리
    private String name; // 닉네임


    /**
     * Create the frame.
     */
    public EnterGui() {
        windowPanel(); // 전체틀 생성
        nickName(); // 닉네임 입력 라벨, 텍스트 필드 생성
        enterButton(); // 입장 버튼 생성 및 처리
        setVisible(true); // Gui 켜기

    }

    public void windowPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 패널 닫는 코드?
        setBounds(100, 100, 450, 300); // 패널 사이즈 코드
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.info);
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    public void nickName() {
        JLabel jlblNewLabel = new JLabel("닉네임 입력");
        jlblNewLabel.setBounds(180, 59, 80, 15);
        contentPane.add(jlblNewLabel);

        textField = new JTextField();
        textField.setBounds(155, 76, 116, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        // 엔터 입력시 입장 처리
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                // 엔터키 입력시 처리
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    name = textField.getText(); // 닉네임 name 읽어오기
                    setVisible(false); // EnterGui 창 끄기
                    new ChatGui(name); // ChatGui에 닉네임 name 값 전달
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }


    public void enterButton() {
        btnNewButton = new JButton("입장");
        btnNewButton.addActionListener(new ActionListener() {
            // 입장 버튼 클릭처리
            public void actionPerformed(ActionEvent e) {
                name = textField.getText(); // 닉네임 name 읽어오기
                setVisible(false); // EnterGui 창 끄기
                new ChatGui(name); // ChatGui에 닉네임 name 값 전달
            }
        });
        btnNewButton.setBounds(165, 134, 97, 23);
        contentPane.add(btnNewButton);
    }


}