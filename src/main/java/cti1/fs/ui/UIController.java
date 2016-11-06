package cti1.fs.ui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Created by Michał on 2016-11-06.
 */
public class UIController {
    private Button mLoadButton;
    private Button mSaveButton;
    private TextArea mTextArea;
    private TextField mFileName;

    public UIController() {
    }

    public UIController(Button loadButton, Button saveButton,
                        TextArea textArea, TextField fileName) {

        mLoadButton = loadButton;
        mSaveButton = saveButton;
        mTextArea = textArea;
        mFileName = fileName;
    }



}
