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

public class EnterGui extends JFrame {

    private JPanel contentPane; // 이벤트 처리
    private JTextField textField; // 이벤트 처리
    private JTextField textField_1; // 이벤트 처리
    private JLabel jblNewLabel; // 멤버필드 분리를 위해서 예시로 사용
    private JButton btnNewButton; // 이벤트 처리

    /**
     * Create the frame.
     */
    public EnterGui() {
        windowPanel();
        accountNum();
        confirmButton();

    }

    public void windowPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 패널 닫는 코드?
        setBounds(100, 100, 450, 300); // 패널 사이즈 코드
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.info);
        contentPane.setForeground(new Color(255, 255, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    public void accountNum() {
        JLabel jlblNewLabel_1 = new JLabel("닉네임 입력");
        jlblNewLabel_1.setBounds(100, 59, 80, 15);
        contentPane.add(jlblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.setBounds(89, 76, 116, 21);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
    }


    public void confirmButton() {
        btnNewButton = new JButton("입장");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setBounds(100, 134, 97, 23);
        contentPane.add(btnNewButton);
    }

}
