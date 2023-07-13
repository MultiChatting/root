package Server;

import java.io.BufferedReader;
import java.io.IOException;

public class ReceptionMsgThread implements Runnable{
    private BufferedReader reader;
    public ReceptionMsgThread(BufferedReader reader){
        this.reader = reader;
    }
    public void run(){
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("클라이언트 : " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
