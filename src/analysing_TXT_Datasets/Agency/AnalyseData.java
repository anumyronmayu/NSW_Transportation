package analysing_TXT_Datasets.Agency;

import java.util.ArrayList;
import java.util.List;

public class AnalyseData {
	public List<Agency> parseStrLines(List<String> list) {

		List<Agency> agencyList = new ArrayList<Agency>();

		for (String s : list) {

			String[] splitStr = s.split("\",\"");

			Agency agency = new Agency();

			agency.setId(splitStr[0].substring(1, splitStr[0].length()));
			agency.setName(splitStr[1]);
			agency.setUrl(splitStr[2]);
			agency.setTimezone(splitStr[3]);
			agency.setLang(splitStr[4]);
			agency.setPhone(splitStr[5].substring(0, splitStr[5].length() - 1));

			agencyList.add(agency);

		}

		return agencyList;

	}
}
