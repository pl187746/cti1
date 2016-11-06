package cti1.fs.ui;

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

    public static UIController initiate(Button loadButton, Button saveButton,
                                        TextArea textArea, TextField fileName) {
        if (null == ourInstance)
            ourInstance = new UIController(loadButton, saveButton,
                    textArea, fileName);
        return ourInstance;
    }

    public static UIController getInstance() {
        if (null == ourInstance)
            throw new IllegalStateException("Nie zainicjalizowany UIController");
        return ourInstance;
    }

    private UIController(Button loadButton, Button saveButton,
                         TextArea textArea, TextField fileName) {
        mLoadButton = loadButton;
        mSaveButton = saveButton;
        mTextArea = textArea;
        mFileName = fileName;
    }

}
