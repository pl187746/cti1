package cti1.fs.ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Michał on 2016-11-06.
 */
public class UserInterfaceMainView extends Application {

    private Button saveButton;
    private Button loadButton;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Title");
        BorderPane root = new BorderPane();
        root.setBottom(addHBox());
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Modify File");
        primaryStage.show();
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
        hbox.getChildren().addAll(saveButton, loadButton);

        return hbox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
