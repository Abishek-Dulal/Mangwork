package views.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChapterController {
    private final Map images;
    private final String series_name;
    private final String series_id;
    private final Integer pages;
    private final String prevChap;
    private final String nextChap;
    private final String chapterid;
    @FXML
    private CheckBox orient_checkBox;


    @FXML
    private ScrollPane container_scrollPane;

    @FXML
    private Text series_name_textView;


    private  HBox hBox = new HBox();

    private VBox vBox = new VBox();


    public ChapterController(Map i) {
          series_name=(String) i.get("series-name");
          series_id = (String) i.get("seriesId");
          pages=Integer.valueOf(((String) i.get("pages")).trim());
          prevChap=String.valueOf(i.get("prevChap")) ;
          nextChap=String.valueOf(i.get("nextChap")) ;
          chapterid =(String) i.get("chapterid");
          images = (LinkedHashMap)i.get("images");

    }

    @FXML
    public void initialize() throws IOException {
         series_name_textView.setText(series_name);
         orient_checkBox.setSelected(true);
         initialiseImages();
         processCheck();
    }

    public void initialiseImages() throws IOException {
        for(int i=1;i<=pages;i++){
           ImageView v = new ImageView();
         String image = (String) images.get(""+i);
         ControllerUtil.downloadImage(image,v);
         v.minWidth(container_scrollPane.getWidth()-10);
         vBox.getChildren().add(v);
       }

        container_scrollPane.setContent(vBox);
        vBox.setStyle("-fx-background-color:orange");
    }

    public void swapChildren(ScrollPane scrollPane,Pane a , Pane b ){
          b.getChildren().addAll(a.getChildren());
          a.getChildren().clear();
          scrollPane.setContent(b);
    }

    private void processCheck(){
         orient_checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
             @Override
             public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                 if(newValue){
                     swapChildren(container_scrollPane,hBox,vBox);
                     return;
                 }
                  swapChildren(container_scrollPane,vBox,hBox);
             }
         });
    }


}
