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
import java.io.*;
import java.net.Socket;

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
    }


    public void enterButton() {
        btnNewButton = new JButton("입장");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                name = textField.getText();
                setVisible(false);
                new ChatGui(name);
//                System.out.println(name);
//                new ChatGui();
            }
        });
        btnNewButton.setBounds(165, 134, 97, 23);
        contentPane.add(btnNewButton);
    }

//    public String getName() {
//        return name;
//    }

//    private void sendIDToServer(String id) {
//        String msg = "login/" + id;
//        try {
//            Socket socket = new Socket("localhost", 8888);
//
//            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
//            writer.println(msg);
//            InputStream inputStream = socket.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            String line;
////            while ((line = reader.readLine()) != null) {
//////                writer.println(line);
////                System.out.println(line);
////            }
//            reader.close();
//            inputStream.close();
//            writer.close();
//            socket.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}