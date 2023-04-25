package application;



import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SampleController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextArea textArea;

    private Circle kırmızı;
    private Circle sarı;
    private Circle hedef;
   

    
    
    int sayac=0;

    public void initialize() throws  IOException{
        // kırmızı sensör
        kırmızı = new Circle(10, Color.RED);
        anchorPane.getChildren().add(kırmızı);

        // sarı sensör
        sarı = new Circle(10, Color.YELLOW);
        anchorPane.getChildren().add(sarı);

        // hedef
        hedef = new Circle(10, Color.BLACK);
        anchorPane.getChildren().add(hedef);

        // rastgele 500x500 anchorpane'de atanması
        Random rand = new Random();
        AnchorPane.setLeftAnchor(kırmızı, (double) rand.nextInt(500));
        AnchorPane.setTopAnchor(kırmızı, (double) rand.nextInt(500));
        AnchorPane.setLeftAnchor(sarı, (double) rand.nextInt(500));
        AnchorPane.setTopAnchor(sarı, (double) rand.nextInt(500));
        AnchorPane.setLeftAnchor(hedef, (double) rand.nextInt(500));
        AnchorPane.setTopAnchor(hedef, (double) rand.nextInt(500));
        
       
        
        
    }
       
 

    @FXML
    public void hesapla() throws IOException {
        // konum
    	double ortaNoktaX = anchorPane.getWidth() / 2;
    	double ortaNoktaY = anchorPane.getHeight() / 2;

    	double kırmızıX = AnchorPane.getLeftAnchor(kırmızı) + kırmızı.getRadius() - ortaNoktaX;
    	double kırmızıY = ortaNoktaY - AnchorPane.getTopAnchor(kırmızı) - kırmızı.getRadius();

    	double sarıX = AnchorPane.getLeftAnchor(sarı) + sarı.getRadius() - ortaNoktaX;
    	double sarıY = ortaNoktaY - AnchorPane.getTopAnchor(sarı) - sarı.getRadius();

    	double hedefX = AnchorPane.getLeftAnchor(hedef) + hedef.getRadius() - ortaNoktaX;
    	double hedefY = ortaNoktaY - AnchorPane.getTopAnchor(hedef) - hedef.getRadius();
        
        double KırmızıUzaklıkX = (hedefX - kırmızıX);
        double KırmızıUzaklıkY = (hedefY - kırmızıY);
        double SarıUzaklıkX = hedefX - sarıX;
        double SarıUzaklıkY = hedefY - sarıY;

     // saat yönüne göre açılar
        double kırmızıAcı = Math.toDegrees(Math.atan2(KırmızıUzaklıkY,KırmızıUzaklıkX));
        double sarıAcı = Math.toDegrees(Math.atan2(SarıUzaklıkY,SarıUzaklıkX));

        kırmızıAcı = 90 + kırmızıAcı;
        if (kırmızıAcı < 0) {
        	kırmızıAcı += 360;
        }
        sarıAcı = 90 + sarıAcı;
        if (sarıAcı < 0) {
        	sarıAcı += 360;
        }


        textArea.setText("Kırmızı koordinat: x(" + kırmızıX + "), y(" + kırmızıY + ")\n");
        textArea.appendText("Sarı koordinat: x(" + sarıX + "), y(" + sarıY + ")\n");
        textArea.appendText("Hedef koordinat: x(" + hedefX + "), y(" + hedefY + ")\n");
        textArea.appendText("Kırmızının hedefe açısı: " + kırmızıAcı + "°\n");
        textArea.appendText("Sarının hedefe açısı: " + sarıAcı + "°\n");
        textArea.appendText("***************");
        sayac++;
        textArea.appendText(" "+sayac+". hesaplama\n");
        
       
        //tcp/ip       
        String serverAddress = "localhost";
        int port = 4444;
        Socket socket = new Socket(serverAddress, port);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("kirmizi konum: "+ "x(" + kırmızıX + "), y(" + kırmızıY + ")");

        out.println("sari konum: "+ "x(" + sarıX + "), y(" + sarıY + ")");

        out.close();
        socket.close();
    
            
    }
    
    
    
    @FXML
    public void yenile() {
    	anchorPane.getChildren().removeAll(kırmızı, sarı, hedef);
    	
        kırmızı = new Circle(10, Color.RED);
        anchorPane.getChildren().add(kırmızı);

      
        sarı = new Circle(10, Color.YELLOW);
        anchorPane.getChildren().add(sarı);

       
        hedef = new Circle(10, Color.BLACK);
        anchorPane.getChildren().add(hedef);

      
        Random rand = new Random();
        AnchorPane.setLeftAnchor(kırmızı, (double) rand.nextInt(500));
        AnchorPane.setTopAnchor(kırmızı, (double) rand.nextInt(500));
        AnchorPane.setLeftAnchor(sarı, (double) rand.nextInt(500));
        AnchorPane.setTopAnchor(sarı, (double) rand.nextInt(500));
        AnchorPane.setLeftAnchor(hedef, (double) rand.nextInt(500));
        AnchorPane.setTopAnchor(hedef, (double) rand.nextInt(500));
        
        
    }
}