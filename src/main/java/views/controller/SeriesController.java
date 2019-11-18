package views.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import views.MangaMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class SeriesController {
    private final String series_text;
    private final String series_id;
    private final String series_name;
    private final ArrayList chapters;
    private final String series_image;
    @FXML
    private Text series_name_textView;

    @FXML
    private VBox chapters_Vbox;


    @FXML
    private ImageView series_imageView;

    @FXML
    private Text series_textView;

    /*{chapters=[{
                  chaplink ="",
                  chaptext ="",
                  chapdate = " ",
                }
        ],
        series-name : .....
        series : 22341,
        text:''''''''''''''''''''''''''''''''''''''''''''''
        }
     */

    public  SeriesController(Map i ){
           chapters = (ArrayList) i.get("chapters");
           series_name= (String) i.get("series-name");
           series_id = (String) i.get("series");
           series_text= (String) i.get("text");
           series_image = (String)i.get("series-image");
    }

    @FXML
    public  void initialize() throws IOException {
        series_textView.setText(series_text);
        series_name_textView.setText(series_name);
        ControllerUtil.downloadImage(series_image,series_imageView);
        initChapterList();
    }

    public void initChapterList(){
        chapters.stream().forEach(( i)->{
            Map val =(Map)i;
            ChapterlistTileController chapterlistTileController =
                    new  ChapterlistTileController( (String)val.get("chaptext"),(String)val.get("chaplink"),(String)val.get("chapdate"));
            try {
                Node n = ChapterlistTileController.getMangaTile(chapterlistTileController);
                chapters_Vbox.getChildren().add(n);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    public static void getControllerPage(SeriesController seriesController) throws IOException {
        FXMLLoader loader = new FXMLLoader(SeriesController.class.getResource("seriesPage.fxml"));
        loader.setController(seriesController);
        Parent parent = loader.load();
        MangaMain.primaryStage.setScene(new Scene(parent));
    }


}
