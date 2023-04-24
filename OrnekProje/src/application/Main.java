package application;
	
import java.io.*;
import java.net.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Application");
        primaryStage.setScene(new Scene(root, 650, 650));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception{
        launch(args);
        
        ServerSocket serverSocket = new ServerSocket(404);
        Socket clientSocket = serverSocket.accept();
        

        InputStream in = clientSocket.getInputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = in.read(buffer);
        String data = new String(buffer, 0, bytesRead);
        String[] parts = data.split("\\|");
        String s1 = parts[0];
        String s2 = parts[1];
        System.out.println("kirmizi " + s1);
        System.out.println("sari " + s2);

        clientSocket.close();
        serverSocket.close();
    }
}
