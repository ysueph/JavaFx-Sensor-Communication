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
        

        int port = 4444;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Sunucu port numarasini dinliyor.. " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("istemciye baglandi " + clientSocket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("istemciden gelen mesaj: " + inputLine);
            }

            in.close();
            System.out.println("mesaj gonderimi tamamlandi.");
            clientSocket.close();
            System.out.println("baglanti kesildi...");
        }
        
    }
        
        
    }
    

    
