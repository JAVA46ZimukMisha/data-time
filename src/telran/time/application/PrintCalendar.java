package telran.time.application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class PrintCalendar {

	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		DayOfWeek weekDay = DayOfWeek.valueOf("THURSDAY");
		System.out.printf("%s\n", weekDay.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru")));
		int monthYearDay[];
		try {
			monthYearDay = getMonthYearDay(args);
			printCalendar(monthYearDay[0], monthYearDay[1], monthYearDay[2]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void printCalendar(int month, int year, int weekDay) {
		printTitle(month, year);
		printWeekDays(weekDay);
		printDates(month, year, weekDay);

	}

	private static void printDates(int month, int year, int weekDay) {
		int column = getFirstColumn(month, year, weekDay);
		printOffset(column);
		int nDays = getMonthDays(month, year);
		int nWeekDays = DayOfWeek.values().length;
		for (int day = 1; day <= nDays; day++) {
			System.out.printf("%4d", day);
			column++;
			if (column == nWeekDays) {
				column = 0;
				System.out.println();
			}

		}

	}

	private static int getMonthDays(int month, int year) {
		YearMonth ym = YearMonth.of(year, month);

		return ym.lengthOfMonth();
	}

	private static void printOffset(int column) {
		System.out.printf("%s", " ".repeat(column * 4));

	}

	private static int getFirstColumn(int month, int year, int firstWeekDay) {
		LocalDate firstMonthDate = LocalDate.of(year, month, 1);
		int weekDay = firstMonthDate.getDayOfWeek().getValue();
		return firstWeekDay<=weekDay ? weekDay - firstWeekDay : weekDay+7 - firstWeekDay;
	}

	private static void printWeekDays(int weekDay) {
		DayOfWeek dayWeeks[] = new DayOfWeek[7];
		int ind = 0;
		for (int i = weekDay; i <= 7; i++) {
			dayWeeks[ind++] = DayOfWeek.of(i);
		}
		for (int i = 1; i < weekDay; i++) {
			dayWeeks[ind++] = DayOfWeek.of(i);
		}
		System.out.print("  ");
		for (DayOfWeek weekDays : dayWeeks) {
			System.out.printf("%s ", weekDays.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
		}
		System.out.println();

	}

	private static void printTitle(int month, int year) {
		Month monthEn = Month.of(month);
		System.out.printf("%s, %d\n", monthEn.getDisplayName(TextStyle.FULL, Locale.getDefault()), year);

	}

	private static int[] getMonthYearDay(String[] args) throws Exception {
		LocalDate current = LocalDate.now();
		int[] res = { current.getMonthValue(), current.getYear(), DayOfWeek.MONDAY.getValue() };
		if (args.length > 0) {
			res[0] = getMonth(args[0]);
			if (args.length > 1) {
				res[1] = getYear(args[1]);
				if (args.length > 2) {
					res[2] = DayOfWeek.valueOf(args[2].toUpperCase()).getValue();
				}
			}
		}

		return res;
	}

	private static int getYear(String yearStr) throws Exception {
		try {
			int res = Integer.parseInt(yearStr);
			if (res <= 0) {
				throw new Exception("year should be a positive number");
			}
			return res;
		} catch (NumberFormatException e) {
			throw new Exception("year should be a number");
		}

	}

	private static int getMonth(String monthStr) throws Exception {
		try {
			int res = Integer.parseInt(monthStr);
			int nMonths = Month.values().length;
			if (res < 1 || res > nMonths) {
				throw new Exception(
						String.format("month %d is wrong value;" + " should be in the range [1, %d]", res, nMonths));
			}
			return res;
		} catch (NumberFormatException e) {
			throw new Exception("month should be a number");
		}

	}

}