package telran.time;

import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;

public class WorkingDaysAdjuster implements TemporalAdjuster {

int[] daysOff;
int nDays;
public int[] getDaysOff() {
	return daysOff;
}
public void setDaysOff(int[] daysOff) {
	this.daysOff = daysOff;
}
public int getnDays() {
	return nDays;
}
public void setnDays(int nDays) {
	this.nDays = nDays;
}
public WorkingDaysAdjuster(int[] daysOff, int nDays) {
	
	this.daysOff = daysOff;
	this.nDays = nDays;
}
public WorkingDaysAdjuster() {
}
	@Override
	public Temporal adjustInto(Temporal temporal) {
		if(this.daysOff.length == 7) {
			return temporal;
		}
		while(nDays>0) {
			temporal = temporal.plus(1, ChronoUnit.DAYS);
			if(!isHolliday(temporal)) {
				nDays--;
			}
		}
		return temporal;
	}
	private boolean isHolliday(Temporal temporal) {
		for(int i = 0; i<daysOff.length; i++) {
			if(temporal.get(ChronoField.DAY_OF_WEEK)==daysOff[i]) {
				return true;
			}
		}
		return false;
	}

}