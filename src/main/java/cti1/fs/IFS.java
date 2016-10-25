package cti1.fs;

public interface IFS {
	public ITaskStatus read(String fileName, ICallback callback);
	public ITaskStatus write(String fileName, byte[] data, SaveMode saveMode, ICallback callback);
}
