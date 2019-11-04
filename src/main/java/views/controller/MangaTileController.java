package views.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class MangaTileController {

    private  String title ;
    private  String latestChapter;

    @FXML
    private Label titleText;

    @FXML
    private ImageView mangaImage;

    @FXML
    private Label latestChText;


    public MangaTileController(String title, String latestChapter) {
        this.title = title;
        this.latestChapter = latestChapter;
    }

   @FXML
   public void initialize() throws IOException {
        titleText.setText(title);
        latestChText.setText(latestChapter);
       URLConnection connection = null;
       try {
           connection = new URL("https://file-thumb.anyacg.co/W300/2a/53/2a53ac1c49c41b6f8b9bc46a49a939faf92f4dae_105793_420_560.jpg").openConnection();
       } catch (IOException e) {
           e.printStackTrace();
       }
       connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        Image image = new Image(connection.getInputStream());

        mangaImage.setImage(image);
   }







    public static  Node getMangaTile(MangaTileController mangaTileController) throws IOException {
        FXMLLoader loader = new FXMLLoader(mangaTileController.getClass().getResource("mangaTile.fxml"));
        loader.setController(mangaTileController);
        return loader.load();
    }




}
