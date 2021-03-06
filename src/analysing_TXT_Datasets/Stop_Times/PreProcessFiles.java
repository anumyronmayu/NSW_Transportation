package analysing_TXT_Datasets.Stop_Times;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PreProcessFiles {

	public static void main(String[] args) throws IOException, ParseException {

		String folderName = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/";
		String csv_file = folderName + "stop_times.csv";
		FileInputStream fstream = new FileInputStream(csv_file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		String firstLine = br.readLine();
		List<String> lines = new ArrayList<String>();

		while ((strLine = br.readLine()) != null) {

			String[] splitStr = strLine.split("\",\"");

			if (splitStr[1].equals("")
					|| splitStr[2].equals("")
					|| splitStr[8].substring(0, splitStr[8].length() - 1)
							.equals("")) {

				continue;
			}

			lines.add(strLine);

		}

		br.close();

		FileWriter writer = new FileWriter(folderName
				+ "Analysis_Results/stop_times_modified.csv");
		writer.write(firstLine + "\n");
		for (String s : lines) {
			writer.write(s + "\n");
		}
		writer.close();
	}
}