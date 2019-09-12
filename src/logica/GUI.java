package logica;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class GUI {

    public static Stage createInterface(Stage primaryStage) throws MalformedURLException {
        primaryStage.setTitle("Circuit Designer");

        /**
         * Creación de stages, scenes, canvas, graphicsContext, bases de la aplicación
         **/
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,800, 600, Color.LIGHTGRAY );
        // Create a wrapper Pane first
        Pane wrapperPane = new Pane();
        root.setCenter(wrapperPane);
        // Put canvas in the center of the window
        Canvas canvas = new Canvas(600, 400);
        wrapperPane.getChildren().add(canvas);

        MyFlowPane myFlowPane = new MyFlowPane();
        root.setRight(myFlowPane.addFlowPane());

        HBox myHBox = addHBox();
        root.setTop(myHBox);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(myFlowPane.getANDimage(), 30, 30);
        gc.setFill(Color.BEIGE);

        primaryStage.setScene(scene);
        primaryStage.show();

        DragFeature dragdrop = new DragFeature();
        dragdrop.myDrag(myFlowPane, canvas, gc);

        canvas.setOnMouseEntered(event -> {
            System.out.println("hay un canvas");
            event.consume();
        });



        return primaryStage;
    }


    /**
     * Método para obtener un HBox personalizado
     * */
    private static HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button buttonCurrent = new Button("Run");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Save");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);

        return hbox;
    }

    /**
     * Método para obtener un VBox personalizado
     * */
    private static VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        return vbox;
    }

}
