package analysing_CSV_Datasets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Extract_Agency_Data {

	public static void main(String[] args) throws IOException, ParseException {

		String csv_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static/agency.txt";
		FileInputStream fstream = new FileInputStream(csv_file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine = br.readLine();

		List<Agency> list = new ArrayList<Agency>();

		while ((strLine = br.readLine()) != null) {

			String[] splitStr = strLine.split(",");

			Agency a = new Agency();
			a.setId(splitStr[0]);
			a.setName(splitStr[1]);
			a.setUrl(splitStr[2]);
			a.setTimezone(splitStr[3]);
			a.setLang(splitStr[4]);
			a.setPhone(splitStr[5]);
			list.add(a);
		}

		br.close();

		System.out.println(list.size());

	}

}
