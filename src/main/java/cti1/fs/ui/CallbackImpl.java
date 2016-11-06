package cti1.fs.ui;

import cti1.fs.ICallback;
import cti1.fs.ITaskStatus;
import cti1.fs.OpType;

/**
 * Created by Michał on 2016-11-06.
 */
public class CallbackImpl implements ICallback {

    @Override
    public void finished(ITaskStatus taskStatus) {
        final UIController uiController = UIController.getInstance();
        if (taskStatus.getType().equals(OpType.Read)) {
            uiController.setContent(new String(taskStatus.getData()));
            uiController.enableButtons();

            System.out.print("finished!");
        } else if (taskStatus.getType().equals(OpType.Write)) {

            uiController.enableButtons();

            System.out.print("finished!");
        } else {
            throw new IllegalArgumentException("Nieobslugiwany typ operacji");
        }

    }
}
