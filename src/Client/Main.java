package Client;

import java.io.IOException;

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
        System.out.println(name);
        // ID:qwdqwda ID 전달

    }
}

