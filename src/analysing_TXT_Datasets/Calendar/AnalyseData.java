package analysing_TXT_Datasets.Calendar;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilities.Utilities;

public class AnalyseData {

	public List<Calendar> getCalendarList() throws IOException {

		String calendar_CSV_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/calendar.csv";
		List<String> fileLines = Utilities.readFile(calendar_CSV_file, true);

		AnalyseData obj = new AnalyseData();
		List<Calendar> calendarList = obj.parseStrLines(fileLines);

		return calendarList;
	}

	public List<Calendar> parseStrLines(List<String> list) {

		List<Calendar> calendarList = new ArrayList<Calendar>();

		for (String s : list) {

			String[] splitStr = s.split("\",\"");

			Calendar calendar = new Calendar();

			calendar.setService_id(splitStr[0].substring(1,
					splitStr[0].length()));
			calendar.setMonday(splitStr[1]);
			calendar.setTuesday(splitStr[2]);
			calendar.setWednesday(splitStr[3]);
			calendar.setThursday(splitStr[4]);
			calendar.setFriday(splitStr[5]);
			calendar.setSaturday(splitStr[6]);
			calendar.setSunday(splitStr[7]);
			calendar.setStart_date(splitStr[8]);
			calendar.setEnd_date(splitStr[9].substring(0,
					splitStr[9].length() - 1));

			calendarList.add(calendar);

		}

		return calendarList;

	}

	public void getWeekDistribution(String folderName,
			List<Calendar> calendarListForEachType, String typeShort)
			throws IOException {

		int[] quantityForEachDay = new int[7];
		for (int i = 0; i < 7; i++) {
			quantityForEachDay[i] = 0;
		}

		for (Calendar calendar : calendarListForEachType) {
			String monday = calendar.getMonday();
			String tuesday = calendar.getTuesday();
			String wednesday = calendar.getWednesday();
			String thursday = calendar.getThursday();
			String friday = calendar.getFriday();
			String saturday = calendar.getSaturday();
			String sunday = calendar.getSunday();

			if (monday.equals("1")) {
				quantityForEachDay[0]++;
			}

			if (tuesday.equals("1")) {
				quantityForEachDay[1]++;
			}

			if (wednesday.equals("1")) {
				quantityForEachDay[2]++;
			}

			if (thursday.equals("1")) {
				quantityForEachDay[3]++;
			}

			if (friday.equals("1")) {
				quantityForEachDay[4]++;
			}

			if (saturday.equals("1")) {
				quantityForEachDay[5]++;
			}

			if (sunday.equals("1")) {
				quantityForEachDay[6]++;
			}
		}

		Utilities.makeDir(folderName
				+ "Analysis_Results/Calendar/WeekDistribution");

		FileWriter writer = new FileWriter(folderName
				+ "Analysis_Results/Calendar/WeekDistribution/" + typeShort
				+ ".txt");

		writer.write("Monday: " + quantityForEachDay[0] + "\n");
		writer.write("Tuesday: " + quantityForEachDay[1] + "\n");
		writer.write("Wednesday: " + quantityForEachDay[2] + "\n");
		writer.write("Thursday: " + quantityForEachDay[3] + "\n");
		writer.write("Friday: " + quantityForEachDay[4] + "\n");
		writer.write("Saturday: " + quantityForEachDay[5] + "\n");
		writer.write("Sunday: " + quantityForEachDay[6] + "\n");

		writer.close();

	}

}
