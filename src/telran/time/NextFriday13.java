package telran.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		int daysUntil13 = temporal.get(ChronoField.DAY_OF_MONTH) < 13 
				? (int)(13-temporal.get(ChronoField.DAY_OF_MONTH)) 
				: (int)(ChronoUnit.DAYS.between(temporal, temporal.plus(1, ChronoUnit.MONTHS)));
		Temporal res = temporal.plus(daysUntil13, ChronoUnit.DAYS);
		while(res.get(ChronoField.DAY_OF_WEEK)!= DayOfWeek.FRIDAY.ordinal() + 1) {
			res = res.plus(1, ChronoUnit.MONTHS);
		}
		return res;
	}
}