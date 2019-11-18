package views.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import scrapmodel.MangaFetcherFactory;
import scrapmodel.QueryException;
import views.MangaMain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MangaTileController {

    private final String latestChLink;
    private final String title ;
    private final String latestChapter ;
    private final String thumbUrl ;
    private final String serieslink;


    @FXML
    private VBox homeVbox;

    @FXML
    private Label titleText;

    @FXML
    private ImageView mangaImage;

    @FXML
    private Label latestChText;


    public MangaTileController(Map i) {
        this.title = (String) i.get("title");
        this.latestChapter = (String) i.get("latest-chapter");
        this.thumbUrl =(String) i.get("thumb-image");
        this.serieslink =(String) i.get("series-link");
        this.latestChLink =(String) i.get("latest-chapter-link");
    }

   @FXML
   public void initialize() throws IOException {
        titleText.setText(title);
        latestChText.setText("Latest Chapter : "+latestChapter);

        EventHandler serieshandler = e->{
            Map prop = new HashMap();
            prop.put("series",serieslink);
            try {
                 Map seriesData = MangaFetcherFactory.getMangasite("bato").getSeriesload().getMangaData(prop);
                 SeriesController controller = new SeriesController(seriesData);
                 ControllerUtil.getControllerPage(controller,"seriesPage.fxml");


            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (QueryException ex) {
                ex.printStackTrace();
            }
        };
        EventHandler chaphandler = e->{
            Map a = new HashMap();
            a.put("latest-chapter-link",latestChLink);
            try {
                System.out.println(MangaFetcherFactory.getMangasite("bato").getChapterload().getMangaData(a));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (QueryException ex) {
                ex.printStackTrace();
            }
        };
        EventHandler mouseEnter = e->{
            MangaMain.primaryStage.getScene().setCursor(Cursor.HAND);
        };
        EventHandler mouseLeave = e->{
            MangaMain.primaryStage.getScene().setCursor(Cursor.DEFAULT);
        };

        titleText.setOnMouseClicked(serieshandler);
        mangaImage.setOnMouseClicked(serieshandler);
        latestChText.setOnMouseClicked(chaphandler);

        titleText.setOnMouseEntered(mouseEnter);
        mangaImage.setOnMouseEntered(mouseEnter);
        latestChText.setOnMouseEntered(mouseEnter);


       latestChText.setOnMouseExited(mouseLeave);
       titleText.setOnMouseExited(mouseLeave);
       mangaImage.setOnMouseExited(mouseLeave);


       ControllerUtil.downloadImage(thumbUrl,mangaImage);

   }



    public static  Node getMangaTile(MangaTileController mangaTileController) throws IOException {
        FXMLLoader loader = new FXMLLoader(mangaTileController.getClass().getResource("mangaTile.fxml"));
        loader.setController(mangaTileController);
        return loader.load();
    }

}
