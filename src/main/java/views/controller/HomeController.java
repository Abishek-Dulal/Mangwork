package views.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import scrapmodel.MangaFetcherFactory;
import scrapmodel.QueryException;
import views.MangaMain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


public class HomeController {

    @FXML
    private AnchorPane homeAnchor;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    public  void initialize(){
        setSearchFieldAction();
        initialiseTilePane();
    }



    private void  setSearchFieldAction(){
        searchButton.setOnAction(this::doSearch);
        searchField.setOnAction(this::doSearch);

    }


    private void initialiseTilePane(){
        final TilePane tilePane = new TilePane();
        tilePane.setAlignment(Pos.TOP_CENTER);
        tilePane.setStyle("-fx-background-color:red");
        tilePane.setHgap(10);
        tilePane.setVgap(10);

        IntStream.rangeClosed(0,100).forEach(i->{
            MangaTileController mangaTileController = new MangaTileController("loxo"+i+"i+" , i+"");
            try {
             Node node = MangaTileController.getMangaTile(mangaTileController);
             tilePane.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });




        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(tilePane);
        scrollPane.setLayoutX(31.0);
        scrollPane.setLayoutY(48.8);

        //binding scrollpane to anchorpane for width
        scrollPane.prefWidthProperty().bind(homeAnchor.widthProperty().subtract(60));
        scrollPane.prefHeightProperty().bind(homeAnchor.heightProperty().subtract(100));


        //binding tilePane  height and width to scroll pane
        tilePane.prefWidthProperty().bind(scrollPane.widthProperty().subtract(15));
        tilePane.prefHeightProperty().bind(scrollPane.heightProperty());



        homeAnchor.getChildren().add(scrollPane);

    }

    private void doSearch(ActionEvent event){
        if(searchField.getText().isEmpty() ){
            return;
        }
        Map<String,String>  property = new HashMap<>();
        property.put("bookname",searchField.getText());
        try {
            System.out.println(MangaFetcherFactory.getMangasite("bato").getSearchByAuthorOrBookName().getMangaData(property));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (QueryException e) {
            e.printStackTrace();
        }

    }


    public static void getHomePage(HomeController homeController) throws IOException {
        FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("home.fxml"));
        loader.setController(homeController);
        Parent parent = loader.load();
        MangaMain.primaryStage.setScene(new Scene(parent));
    }

}
