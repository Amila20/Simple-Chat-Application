package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientAppController {
    public TextArea txtmsgArea;
    public TextField txtSendMsg;

    final int PORT=8001;
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    String message="";

    public void initialize(){

        new Thread(()->{
            try {
                socket=new Socket("localhost",PORT);

                dataOutputStream=new DataOutputStream(socket.getOutputStream());
                dataInputStream=new DataInputStream(socket.getInputStream());

                while (!message.equals("exit")){
                    message=dataInputStream.readUTF();
                    txtmsgArea.appendText("\n Server : " +message);


                }



            } catch (IOException e) {

                throw new RuntimeException(e);
            }


        }).start();

    }

    public void sendOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtSendMsg.getText().trim());
        dataOutputStream.flush();
        txtmsgArea.appendText("\n Client : "+txtSendMsg.getText());
        txtSendMsg.clear();

    }
}
