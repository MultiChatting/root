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
    private JButton btnNewButton; // 이벤트 처리

    private String name; // 값 반환

    /**
     * Create the frame.
     */
    public EnterGui() {
        windowPanel();
        nickName();
        enterButton();
        setVisible(true);

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

    public void nickName() {
        JLabel jlblNewLabel = new JLabel("닉네임 입력");
        jlblNewLabel.setBounds(100, 59, 80, 15);
        contentPane.add(jlblNewLabel);

        textField = new JTextField();
        textField.setBounds(89, 76, 116, 21);
        contentPane.add(textField);
        textField.setColumns(10);
    }


    public void enterButton() {
        btnNewButton = new JButton("입장");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                name = textField.getText();
                if (!name.isEmpty()) {
                    // 텍스트 필드에 값이 입력되어 있을 때
                    // name 변수에 입력된 값이 들어갑니다.
                    // 이후 필요한 처리를 수행할 수 있습니다.
                }
            }
        });
        btnNewButton.setBounds(100, 134, 97, 23);
        contentPane.add(btnNewButton);
    }

    public String getName() {
        return name;
    }


}