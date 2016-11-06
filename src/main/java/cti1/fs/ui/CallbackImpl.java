package cti1.fs.ui;

import cti1.fs.ICallback;
import cti1.fs.ITaskStatus;
import cti1.fs.OpType;
import cti1.fs.Status;

/**
 * Created by Michał on 2016-11-06.
 */
public class CallbackImpl implements ICallback {

    @Override
    public void finished(ITaskStatus taskStatus) {
        final UIController uiController = UIController.getInstance();
        if (taskStatus.getType().equals(OpType.Read)) {
            if (taskStatus.getStatus().equals(Status.Succeeded)) {
                uiController.setContent(new String(taskStatus.getData()));
            }

            uiController.setTaskStatus(taskStatus.getStatus().name()).enableButtons();

        } else if (taskStatus.getType().equals(OpType.Write)) {

            uiController.setTaskStatus(taskStatus.getStatus().name()).enableButtons();

        } else {
            throw new IllegalArgumentException("Nieobslugiwany typ operacji");
        }

    }
}
