package Client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        EnterGui E = new EnterGui();
        E.setVisible(true);
        // 상속이라 가능
        // 패널을 만들어서 붙여주면 좋음

        //        Socket socket = null;
//        PrintWriter out = null;
//        BufferedReader in = null;
//
//        try {
//            // 서버에 소켓 연결
//            socket = new Socket("localhost", 12345);
//            System.out.println("서버에 연결되었습니다.");
//
//            // 서버와의 입출력 스트림 생성
//            out = new PrintWriter(socket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            // 사용자로부터 입력 받아 서버로 전송
//            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//            String userInput;
//            while ((userInput = stdIn.readLine()) != null) {
//                // 서버로 메시지 전송
//                out.println(userInput);
//                // 서버에서의 응답 출력
//                System.out.println("서버: " + in.readLine());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 연결 종료 시 리소스 해제
//            out.close();
//            in.close();
//            socket.close();
//        }
    }
}

