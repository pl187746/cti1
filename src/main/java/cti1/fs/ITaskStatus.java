package cti1.fs;

public interface ITaskStatus {
	public Status getStatus();
	public OpType getType();
	public byte[] getData();
}
