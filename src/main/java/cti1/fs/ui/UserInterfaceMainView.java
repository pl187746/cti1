package cti1.fs.ui;

import cti1.fs.DiskFS;
import cti1.fs.IFS;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Michał on 2016-11-06.
 */
public class UserInterfaceMainView extends Application {

    private Button saveButton;
    private Button loadButton;
    private TextArea textArea;
    private IFS diskFS;

    @Override
    public void start(Stage primaryStage) throws Exception {

        diskFS = new DiskFS();

        BorderPane root = new BorderPane();
        root.setBottom(addHBox());
        root.setCenter(createMainTextArea());

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Modify File");
        primaryStage.show();
    }

    private TextArea createMainTextArea() {
        textArea = new TextArea();
        return textArea;
    }

    private HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        //hbox.setStyle("-fx-background-color: #336699;");

        saveButton = new Button("SAVE");
        saveButton.setPrefSize(100, 20);

        loadButton = new Button("LOAD");
        loadButton.setPrefSize(100, 20);
        loadButton.setOnAction((event) -> {
            diskFS.read("siemka", new CallbackImpl());
        });
        hbox.getChildren().addAll(saveButton, loadButton);

        return hbox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
