package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAppController {
    public TextArea txtmsgArea;
    public TextField txtSendmsg;

    final int PORT=8001;
    public VBox txtmsgArea1;

    ServerSocket serverSocket;
    Socket accept;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    String message="";



    public void initialize(){

        new Thread(()->{
            try {

                serverSocket=new ServerSocket(PORT);
                txtmsgArea.appendText("Server Started..");
                accept=serverSocket.accept();
                txtmsgArea.appendText("\n Client Conected..");
                dataOutputStream=new DataOutputStream(accept.getOutputStream());
                dataInputStream=new DataInputStream(accept.getInputStream());

                while (!message.equals("exit")){
                    message=dataInputStream.readUTF();
                    txtmsgArea.appendText("\n Client : " +message);


                }



            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }).start();

    }

    public void sendOnAction(ActionEvent actionEvent) throws IOException {
    dataOutputStream.writeUTF(txtSendmsg.getText().trim());
    dataOutputStream.flush();
    txtmsgArea.appendText("\n Server : " +txtSendmsg.getText());
    txtSendmsg.clear();
    }
}
