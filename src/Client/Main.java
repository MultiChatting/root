package Client;

import java.io.*;
import java.net.Socket;

public class Main {

    private static PrintWriter writer;
    public static void main(String[] args) throws IOException {
        // EnterGui 실행
        new EnterGui();
        // EnterGui 창이 닫힐 때까지 기다린 후 name 값을 받습니다.
//        while (enterGui.isVisible()) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }


}

