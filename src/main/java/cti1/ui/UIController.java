package cti1.ui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Created by Michał on 2016-11-06.
 */
public class UIController {
    private static UIController ourInstance;

    private Button mLoadButton;
    private Button mSaveButton;
    private TextArea mTextArea;
    private TextField mFileName;
    private TextField mStatusFieldValue;

    public static UIController initiate(Button loadButton, Button saveButton,
                                        TextArea textArea, TextField fileName, TextField statusFieldValue) {
        if (null == ourInstance)
            ourInstance = new UIController(loadButton, saveButton,
                    textArea, fileName, statusFieldValue);
        return ourInstance;
    }

    public static UIController getInstance() {
        if (null == ourInstance)
            throw new IllegalStateException("Nie zainicjalizowany UIController");
        return ourInstance;
    }

    private UIController(Button loadButton, Button saveButton,
                         TextArea textArea, TextField fileName, TextField statusFieldValue) {
        mLoadButton = loadButton;
        mSaveButton = saveButton;
        mTextArea = textArea;
        mFileName = fileName;
        mStatusFieldValue = statusFieldValue;
    }

    public UIController disableButtons() {
        mSaveButton.setDisable(true);
        mLoadButton.setDisable(true);

        return this;
    }

    public UIController enableButtons() {
        mSaveButton.setDisable(false);
        mLoadButton.setDisable(false);

        return this;
    }

    public UIController setContent(String content) {
        mTextArea.setText(content);

        return this;
    }


    public UIController setTaskStatus(String status) {
        mStatusFieldValue.setVisible(true);
        mStatusFieldValue.setText("Status: " + status);

        return this;
    }
}
