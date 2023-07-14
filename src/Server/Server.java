import Client.EnterGui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Server.ServerGui;

import Server.ReceptionMsgThread;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Server() {
        this.start();
        this.send();
        this.close();
    }

    public static void main(String[] args) {
        new Server();

    } //main

    public void start() {
        try {
            // 서버 소켓 생성
            serverSocket = new ServerSocket(8888);
            System.out.println("서버가 시작되었습니다.");

            // 클라이언트 연결 대기
            clientSocket = serverSocket.accept();
            System.out.println("클라이언트가 연결되었습니다.");

            // 클라이언트와 입출력 스트림 생성
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

            writer.println("서버에 연결 됐습니다"); //클라이언트에 연결 사실 전송

            // 클라이언트로부터 메시지 수신 및 콘솔에 출력
            Thread receiveThread = new Thread(new ReceptionMsgThread(reader));
            receiveThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void send() {
        try {
            // 콘솔에서 입력받은 메시지를 클라이언트로 전송
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String consoleInput;
            while ((consoleInput = consoleReader.readLine()) != null) {
                writer.println(consoleInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            reader.close();
            writer.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


} //class
