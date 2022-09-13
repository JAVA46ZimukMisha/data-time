package telran.time.application;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Reminder {

	public static void main(String[] args) {
		try { 
		int interval = getInterval(args[0]);
		String chU = args[1].toUpperCase();
		long intervalMillis = getInMillis(interval, chU);
		int stopInterval = 1;
		String stopChU = "HOURS";
		if(args.length > 2) {
			stopInterval = getStopInterval(args[2], interval);
			stopChU = chU;
		}
		long stopMillis = getInMillis(stopInterval, stopChU);
		printBeepFunction(stopMillis, intervalMillis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void printBeepFunction(long stopMillis, long intervalMillis) {
		LocalDateTime lt = LocalDateTime.now();
		Timer timer = new Timer();
		timer.schedule(new PrintBeep(lt, timer, stopMillis), 0, intervalMillis);
		
	}

	private static int getStopInterval(String limit, int interval) throws Exception {
		try {
			int res = Math.abs(Integer.parseInt(limit));
			if (res < interval) {
				throw new Exception("limit should be greater than interval");
			}
			return Math.abs(res);
			} catch (NumberFormatException e) {
				throw new Exception("limit should be a number");
			}
	}

	private static long getInMillis(int interval, String chU) throws Exception {
		try {
		return TimeUnit.valueOf(chU).toMillis(interval);
		}catch(Exception e) {
			throw new Exception("wrong time unit name " + chU);
		}
	}

	private static int getInterval(String interval) throws Exception {
		try {
		int res = Math.abs(Integer.parseInt(interval));
		return res;
		} catch (NumberFormatException e) {
			throw new Exception("interval should be a number");
		}
	}

}