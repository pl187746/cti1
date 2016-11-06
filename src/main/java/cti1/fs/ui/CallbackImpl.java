package cti1.fs.ui;

import cti1.fs.ICallback;
import cti1.fs.ITaskStatus;

/**
 * Created by Michał on 2016-11-06.
 */
public class CallbackImpl implements ICallback {
    @Override
    public void finished(ITaskStatus taskStatus) {
        System.out.print("finished!");
    }
}
