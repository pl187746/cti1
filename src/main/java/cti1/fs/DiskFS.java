package cti1.fs;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Random;

public class DiskFS implements IFS {
	
	static final Random rand = new Random();

	@Override
	public ITaskStatus read(String fileName, ICallback callback) {
		TaskStatus taskStatus = new TaskStatus(OpType.Read);
		runTask(new Runnable() {
			private final TaskStatus status = taskStatus;
			private final String file = fileName;
			private final ICallback cb = callback;
			@Override
			public void run() {
				Status st = Status.Failed;
				try {
					randDelay();
					byte[] data;
					try(FileChannel channel = FileChannel.open(Paths.get(file), StandardOpenOption.READ)) {
						int size = (int)channel.size();
						data = new byte[size];
						int r = channel.read(ByteBuffer.wrap(data));
						if(r == size) {
							status.setData(data);
							st = Status.Succeeded;
						}
					}
				} catch(Exception e) {
				}
				finishTask(status, st, cb);
			}
		});
		return taskStatus;
	}

	@Override
	public ITaskStatus write(String fileName, byte[] data, SaveMode saveMode, ICallback callback) {
		byte[] cdata = Arrays.copyOf(data, data.length);
		TaskStatus taskStatus = new TaskStatus(OpType.Write);
		runTask(new Runnable() {
			private final TaskStatus status = taskStatus;
			private final String file = fileName;
			private final ICallback cb = callback;
			private final byte[] data = cdata;
			private final SaveMode mode = saveMode;
			@Override
			public void run() {
				Status st = Status.Failed;
				try {
					randDelay();
					OpenOption[] oo = null;
					switch(mode) {
					case SaveAlways:
						oo = new OpenOption[]{ StandardOpenOption.WRITE, StandardOpenOption.CREATE };
						break;
					case DontModify:
						oo = new OpenOption[]{ StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW };
						break;
					case DontCreate:
						oo = new OpenOption[]{ StandardOpenOption.WRITE };
					}
					try(FileChannel channel = FileChannel.open(Paths.get(file), oo)) {
						channel.truncate(data.length);
						int w = channel.write(ByteBuffer.wrap(data));
						if(w == data.length) {
							st = Status.Succeeded;
						}
					}
				} catch(Exception e) {
				}
				finishTask(status, st, cb);
			}
		});
		return taskStatus;
	}
	
	private static Thread runTask(Runnable task) {
		Thread thread = new Thread(task);
		thread.start();
		return thread;
	}
	
	static void randDelay() {
		int ms = rand.nextInt(9000) + 1000;
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}
	
	static void finishTask(TaskStatus taskStatus, Status status, ICallback callback) {
		taskStatus.setStatus(status);
		if(callback != null) {
			callback.finished(taskStatus);
		}
	}
	
}
