package Server;

import java.io.BufferedReader;
import java.io.IOException;

public class ReceptionMsgThread implements Runnable{
    private BufferedReader reader;
    private ServerGui serverGui;
    public ReceptionMsgThread(ServerGui serverGui, BufferedReader reader){
        this.serverGui = serverGui;
        this.reader = reader;
    }
    public void run(){
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                serverGui.appendMessage("클라이언트 : " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
