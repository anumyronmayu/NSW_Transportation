package analysing_TXT_Datasets.Calendar;

import java.util.ArrayList;
import java.util.List;

public class AnalyseData {

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

}
