package Client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        EnterGui enterGui = new EnterGui();
        enterGui.setVisible(true);

        // EnterGui 창이 닫힐 때까지 기다린 후 name 값을 받습니다.
        while (enterGui.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String name = enterGui.getName();
//        System.out.println(name);
        // ID:qwdqwda ID 전달
        String id = "ID:" + name;
        System.out.println(id);
    }
    private void sendIDToServer(String id) {
        // 서버와의 통신을 위한 코드 작성
        try {
            Socket socket = new Socket("localhost", 8888);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(id.getBytes());
            outputStream.flush();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

