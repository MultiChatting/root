package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ServerGui serverGui;
    ArrayList<ChatThread> chatlist = new ArrayList<ChatThread>();

    public Server() {
        this.start();
    }

    public static void main(String[] args) {
        new Server();
    } //main

    public void start() {
        try {
            //GUI 켜기
            serverGui = new ServerGui();

            //GUI에 이벤트 추가(서버에 전달을 위해 서버 클래스에서 추가)
            serverGui.chatTextField.addKeyListener(new Event(this, serverGui));

            // 서버 소켓 생성
            serverSocket = new ServerSocket(8888);
            serverGui.appendMessage("서버가 시작 되었습니다");

            // 여러 클라이언트 연결 대기
            while (true) {
                clientSocket = serverSocket.accept();
                ChatThread chatthread = new ChatThread();
                chatlist.add(chatthread);
                serverGui.appendMessage("클라이언트가 연결되었습니다." + "(IP" + clientSocket.getInetAddress() + ")");
                serverGui.appendName(chatthread.getName()); // 유저 목록에 이름넣기
                chatthread.start();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendToAll(String s) {
        for (ChatThread thread : chatlist) {
            thread.writerToClient.println(s);
        }
    }

    class ChatThread extends Thread {
        String msg;
        String[] rmsg;
        private BufferedReader reader = null;
        private PrintWriter writerToClient = null;

        public void run() {
            boolean loginBool = true;

            try {
                // 클라이언트와 입출력 스트림 생성
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writerToClient = new PrintWriter(clientSocket.getOutputStream(), true);
                writerToClient.println("서버에 연결 됐습니다"); //클라이언트에 연결 사실 전송

                while (loginBool) {
                    msg = reader.readLine();
                    serverGui.appendMessage(this.getName() + " : " + msg);
                    if (msg.equals(".quit")) {
                        loginBool = false;

                    }
                }
                //클라이언트 챗스레드 종료
                this.interrupt();
                serverGui.appendMessage(this.getName() + " stopped!");
            } catch (IOException e) {
                //클라이언트와 연결이 끊어짐
                chatlist.remove(this);
                serverGui.appendMessage("ChatThread Class run() IOException");
            }
        }


    }// chatThread 클래스


} //class
