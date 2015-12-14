package analysing_CSV_Datasets.Shapes;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Extract_Shapes_Data {

	public static void main(String[] args) throws IOException {

		String csv_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static/shapes.txt";
		FileInputStream fstream = new FileInputStream(csv_file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine = br.readLine();

		List<Shapes> list = new ArrayList<Shapes>();

		while ((strLine = br.readLine()) != null) {

			String[] splitStr = strLine.split("\",\"");

			Shapes s = new Shapes();

			s.setShape_id(splitStr[0].substring(1, splitStr[0].length()));
			s.setShape_pt_lat(Double.parseDouble(splitStr[1]));
			s.setShape_pt_lon(Double.parseDouble(splitStr[2]));
			s.setShape_pt_sequence(Integer.parseInt(splitStr[3]));
			s.setShape_dist_traveled(Double.parseDouble(splitStr[4].substring(
					0, splitStr[4].length() - 1)));

			list.add(s);
		}
		
		System.out.println(list.size());
		br.close();
	}
}