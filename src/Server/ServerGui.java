package Server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ServerGui {
    private JFrame clientFrame;
    private TextArea chatTextArea;
    private TextField chatTextField;
    public ServerGui(){

        clientFrame = new JFrame();
        clientFrame.setDefaultCloseOperation(clientFrame.EXIT_ON_CLOSE);
        clientFrame.setBounds(100, 100, 822, 475);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        clientFrame.setContentPane(contentPane);
        contentPane.setLayout(null);

        chatTextArea = new TextArea();
        chatTextArea.setEditable(false);
        chatTextArea.setBounds(18,12,567,384);
        contentPane.add(chatTextArea);

        chatTextField = new TextField();
        chatTextField.setColumns(30);
        chatTextField.setBounds(17, 403, 572, 22);
        contentPane.add(chatTextField);


        Label userListLabel = new Label("유저 목록");
        userListLabel.setBounds(679, 16, 61, 16);
        contentPane.add(userListLabel);

        TextArea userListTextArea = new TextArea();
        userListTextArea.setEditable(false);
        userListTextArea.setBounds(613, 42, 192, 370);
        contentPane.add(userListTextArea);


        chatTextField.addKeyListener();  // 엔터 이벤트

    }

    public void setFrameVisible(){
        clientFrame.setVisible(true);
    }

    public void setTextFieldBlank(){
        chatTextField.setText(null);
    }

    public void appendMessage(String Message){
        chatTextArea.append(Message + "\n");
    }

    public String getChatMessage(){ return chatTextField.getText(); }
}