package analysing_CSV_Datasets.Stops;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import analysing_CSV_Datasets.Shapes.Shapes;

public class Extract_Stops_Data {

	public static void main(String[] args) throws IOException {

		String csv_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static/stops.txt";
		FileInputStream fstream = new FileInputStream(csv_file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine = br.readLine();

		List<Stops> list = new ArrayList<Stops>();

		while ((strLine = br.readLine()) != null) {

			String[] splitStr = strLine.split("\",\"");

			Stops s = new Stops();

			s.setStop_id(splitStr[0].substring(1, splitStr[0].length()));

			s.setStop_code(splitStr[1]);
			s.setStop_name(splitStr[2]);
			s.setStop_lat(Double.parseDouble(splitStr[3]));
			s.setStop_lon(Double.parseDouble(splitStr[4]));
			s.setLocation_type(splitStr[5]);
			s.setParent_station(splitStr[6]);
			s.setWheelchair_boarding(splitStr[7]);
			s.setPlatform_code(splitStr[8].substring(0,
					splitStr[8].length() - 1));

			list.add(s);
		}

		System.out.println(list.size());
		br.close();

	}

}
