package Server;

import Model.User;

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

            //GUI에 이벤트 추가(서버에 전달을 위해 서버 클래스에서 추가
            serverGui.chatTextField.addKeyListener(new Event(this, serverGui));

            // 서버 소켓 생성
            serverSocket = new ServerSocket(8888);
            serverGui.appendMessage("서버가 시작 되었습니다");

            // 여러 클라이언트 연결 대기
            while (true) {
                clientSocket = serverSocket.accept();
                ChatThread chatthread = new ChatThread();
                chatlist.add(chatthread);
                serverGui.appendMessage("클라이언트가 연결됐습니다." + "(IP : " + clientSocket.getInetAddress().toString().substring(1) + ")");
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
        private User user;

        public void run() {
            boolean loginBool = true;
            user = new User(); // user 객체 생성(스레드 접속 클라이언트)
            user.setThreadName(this.getName()); // user 스레드 이름 입력
            try {
                // 클라이언트와 입출력 스트림 생성
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writerToClient = new PrintWriter(clientSocket.getOutputStream(), true);
                writerToClient.println("서버에 연결 됐습니다(serverIP : " + serverSocket.getInetAddress().toString().substring(1) + ")"); //클라이언트에 연결 사실 전송

                //클라이언트로부터 메세지를 반복해서 읽어옴
                while (loginBool) {
                    msg = reader.readLine();
                    rmsg = msg.split("/");

                    serverGui.appendMessage(user.getId() + " : " + msg);

                    if (rmsg[0].equals("login")) { //로그인 처리
                        user.setId(rmsg[1]);
                        sendToAll("server : " + user.getId() + "님이 입장했습니다");
                        serverGui.appendUserList(user.getId());
                    } else if (msg.equals(".quit")) { //로그아웃 처리
                        sendToAll("server : " + user.getId() + "님이 나갔습니다");
                        serverGui.appendMessage(user.getId() + "님이 나갔습니다");
                        loginBool = false;
                    } else {
                        sendToAll(user.getId() + ":" + msg); // 모든 클라이언트에 클라이언트의 메세지 전송
                    }
                }
                //클라이언트 챗스레드 종료
                chatlist.remove(this);
                serverGui.removeUserList(user.getId());
                serverGui.appendMessage(this.getName() + " stopped!");
                this.interrupt();
            } catch (IOException e) {
                //클라이언트와 연결이 끊어짐
                serverGui.appendMessage("클라이언트 " + user.getId() + "의 연결이 끊어졌습니다");
                serverGui.removeUserList(user.getId());
                chatlist.remove(this);
            }
        }


    }// chatThread 클래스


} //class
