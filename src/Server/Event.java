package Server;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Event implements KeyListener{
    private ServerGui serverGui;
    public Event(ServerGui serverGui){
        this.serverGui = serverGui;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            String s = serverGui.getChatMessage();
            serverGui.appendMessage("server : " + s);
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
