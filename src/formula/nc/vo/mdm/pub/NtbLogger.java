package nc.vo.mdm.pub;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class NtbLogger {
	static PrintStream ps = new PrintStream(new FileOutputStream(
			FileDescriptor.out));
	

	public static void error(Throwable t) {
	}

	public static void error(String msg) {
	}

	public static void printException(Throwable t) {
	}

	public static void info(Object msg) {
	}

	public static void print(Object msg) {
	}
}
