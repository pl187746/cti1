package cti1.fs;

class TaskStatus implements ITaskStatus {
	
	private Status status;
	private final OpType type;
	private byte[] data;
	
	TaskStatus(OpType type) {
		this.status = Status.InProgress;
		this.type = type;
	}
	
	void setStatus(Status status) {
		this.status = status;
	}
	
	void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public OpType getType() {
		return type;
	}

	@Override
	public byte[] getData() {
		return data;
	}

}
