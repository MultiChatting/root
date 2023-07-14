package Server;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Event implements KeyListener{
    private ServerGui serverGui;
    private Server server;
    public Event(Server server, ServerGui serverGui){
        this.serverGui = serverGui;
        this.server = server;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            String s = serverGui.getChatMessage().trim();// 앞뒤 공백제거
            if(!s.isEmpty()){ // 비어있지 않은 경우에만 추가
                serverGui.appendMessage("SERVER : " + s);
                server.sendToAll(s);
            }
            serverGui.setTextFieldBlank();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
