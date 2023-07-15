package Client;

import java.io.*;
import java.net.Socket;

public class Main {

    private static PrintWriter writer;
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
        String id = "login/" + name;
        sendIDToServer(id);

    }
    private static void sendIDToServer(String id) {

        try {
            Socket socket = new Socket("localhost", 8888);

            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(id);
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
//                writer.println(line);
                System.out.println(line);
            }
            reader.close();
            inputStream.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

