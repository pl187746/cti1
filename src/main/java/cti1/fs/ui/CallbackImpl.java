package cti1.fs.ui;

import cti1.fs.ICallback;
import cti1.fs.ITaskStatus;
import cti1.fs.OpType;

/**
 * Created by Michał on 2016-11-06.
 */
public class CallbackImpl implements ICallback {

    private OpType opType;

    public CallbackImpl(OpType opType) {
        this.opType = opType;
    }

    @Override
    public void finished(ITaskStatus taskStatus) {
        System.out.print("finished!");
    }
}
