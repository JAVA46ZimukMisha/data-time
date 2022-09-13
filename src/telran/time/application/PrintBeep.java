package telran.time.application;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;

public class PrintBeep extends TimerTask{
	LocalDateTime lt;
	Timer timer;
	long stopMillis;
	public PrintBeep(LocalDateTime lt, Timer timer, long stopMillis) {
		this.lt = lt;
		this.timer = timer;
		this.stopMillis = stopMillis;
	}
	@Override
	public void run() {
		long stopMilli = getMilli(lt) + stopMillis;
		System.out.println("\007\007\007");
		if(stopMilli <= getMilli(LocalDateTime.now())) {
			timer.cancel();
		}
		
	}
	private long getMilli(LocalDateTime ldt) {
		return ldt.atZone(ZoneId.of("Asia/Jerusalem")).toInstant().toEpochMilli();
	}

}
