package cdiofinal.server;

public class SQLStates {
	public static final int INTEGRITY_FAILED = 23000;
	public static final int DUPLICATE_PRIMARY = 1062;
	public static boolean isRecoverable(String s)
	{
		int e = Integer.parseInt(s);
		return isRecoverable(e);
	}
	public static boolean isRecoverable(int e)
	{
		return (e == INTEGRITY_FAILED) || (e == DUPLICATE_PRIMARY);
	}
}
