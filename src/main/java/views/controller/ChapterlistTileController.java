package views.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import scrapmodel.MangaFetcherFactory;
import scrapmodel.QueryException;
import views.MangaMain;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChapterlistTileController {


    @FXML
    private AnchorPane chapter_tile_anchorPane;

    @FXML
    private Text chapter_no_textView;



    @FXML
    private Text date_textView;

    private final String chapter_name;
    private final String chapter_id;
    private  final String chapter_date;

    public ChapterlistTileController(String chapter_name, String chapter_id, String chapter_date) {
        this.chapter_name = chapter_name;
        this.chapter_id = chapter_id;
        this.chapter_date = chapter_date;

    }

    @FXML
    public void initialize(){
        chapter_no_textView.setText(chapter_name);
        date_textView.setText(chapter_date);
        chapter_tile_anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
            Map i = new HashMap();
            i.put("latest-chapter-link",chapter_id);
            try {
               ChapterController chapterController = new ChapterController(MangaFetcherFactory.getMangasite("bato").getChapterload().getMangaData(i));
               ControllerUtil.getControllerPage(chapterController,"chapterPage.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (QueryException ex) {
                ex.printStackTrace();
            }
        });

        chapter_tile_anchorPane.addEventHandler(MouseEvent.MOUSE_ENTERED,e->{
            MangaMain.primaryStage.getScene().setCursor(Cursor.HAND);
            chapter_tile_anchorPane.setStyle("-fx-background-color:purple");
        });


        chapter_tile_anchorPane.addEventHandler(MouseEvent.MOUSE_EXITED,e->{
            MangaMain.primaryStage.getScene().setCursor(Cursor.DEFAULT);
            chapter_tile_anchorPane.setStyle("-fx-background-color:white");
        });

    }


    public static Node getMangaTile(ChapterlistTileController chapterlistTileController) throws IOException {
        FXMLLoader loader = new FXMLLoader(chapterlistTileController.getClass().getResource("chapterTile.fxml"));
        loader.setController(chapterlistTileController);
        return loader.load();
    }

}
