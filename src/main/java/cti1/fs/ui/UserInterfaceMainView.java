package cti1.fs.ui;

import cti1.fs.FSUtils;
import cti1.fs.IFS;
import cti1.fs.ITaskStatus;
import cti1.fs.SaveMode;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private TextField textField;
    private TextField statusFieldValue;
    private IFS diskFS;

    private enum ButtonType {
        LOAD, SAVE
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        diskFS = FSUtils.getDefaultFS();
        BorderPane root = new BorderPane();
        root.setTop(createTextField());
        root.setBottom(addHBox());
        root.setCenter(createMainTextArea());
        UIController.initiate(loadButton, saveButton, textArea, textField, statusFieldValue);

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Modify File");
        primaryStage.show();
    }

    private Node createTextField() {
        textField = new TextField();

        return textField;
    }

    private TextArea createMainTextArea() {
        textArea = new TextArea();
        return textArea;
    }

    private HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);

        saveButton = new Button("SAVE");
        saveButton.setPrefSize(100, 20);

        createButton(ButtonType.SAVE);
        createButton(ButtonType.LOAD);
        createStatusView();

        hbox.getChildren().addAll(saveButton, loadButton, statusFieldValue);

        return hbox;
    }

    private void createStatusView() {
        statusFieldValue = new TextField();
        statusFieldValue.setEditable(false);
        statusFieldValue.setText("");
        statusFieldValue.setVisible(false);
    }

    private void createButton(ButtonType buttonType) {
        switch (buttonType) {
            case SAVE:
                saveButton = new Button("SAVE");
                saveButton.setPrefSize(100, 20);

                saveButton.setOnAction((event) -> Platform.runLater(() -> {
                    String fileName = textField.getText().trim();
                    byte[] content = textArea.getText().getBytes();
                    if (null != fileName && fileName.length() > 0 && null != content) {
                        ITaskStatus taskStatus = diskFS.write(fileName, content, SaveMode.SaveAlways, new CallbackImpl());
                        UIController.getInstance().disableButtons().setTaskStatus(taskStatus.getStatus().name());
                    }
                }));
                break;
            case LOAD:
                loadButton = new Button("LOAD");
                loadButton.setPrefSize(100, 20);

                loadButton.setOnAction((event) -> Platform.runLater(() -> {
                    String fileName = textField.getText().trim();

                    if (null != fileName && fileName.length() > 0) {
                        ITaskStatus taskStatus = diskFS.read(fileName, new CallbackImpl());
                        UIController.getInstance().disableButtons().setTaskStatus(taskStatus.getStatus().name());
                    }
                }));
                break;
            default:
                throw new IllegalArgumentException("Nie obslugiwany typ przycisku");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
