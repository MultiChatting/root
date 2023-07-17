package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 8888);
            System.out.println("서버에 연결되었습니다.");

            // 서버와의 입출력 스트림 생성
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // 서버로부터 메시지 수신 및 콘솔에 출력
            Thread receiveThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String message;
                        while ((message = in.readLine()) != null) {
                            System.out.println("서버: " + message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receiveThread.start();

            // 콘솔에서 입력받은 메시지를 서버로 전송
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String consoleInput;
            while ((consoleInput = consoleReader.readLine()) != null) {
                out.println(consoleInput);
            }

            // 연결 종료
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}