package cti1.fs.ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Michał on 2016-11-06.
 */
public class UserInterfaceMainView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Title");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
