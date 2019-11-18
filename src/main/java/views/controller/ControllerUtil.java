package views.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import views.MangaMain;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ControllerUtil {

    public static void downloadImage(String thumlink, ImageView imageView) throws IOException {
        CompletableFuture.supplyAsync(()->{
            URLConnection urlConnection = null;
            try {
                urlConnection = new URL(thumlink).openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            try {
                return  Optional.ofNullable(new Image(urlConnection.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Optional.ofNullable(null);
        }).thenAccept(o -> {
            Platform.runLater(()->{
                if(o.isPresent()){
                    imageView.setImage((Image) o.get());
                }
            });
        });

    }

    public   static <T> void getControllerPage(T controller,String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(controller.getClass().getResource(fxml));
        loader.setController(controller);
        Parent parent = loader.load();
        MangaMain.primaryStage.setScene(new Scene(parent));
    }


}
