package cti1.fs;

public class FSUtils {
	
	private static IFS defaultFS = new DiskFS();
	
	public static IFS getDefaultFS() {
		return defaultFS;
	}
}
