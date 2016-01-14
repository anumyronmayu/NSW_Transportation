package analysing_TXT_Datasets.Shapes;

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

		double latMin = Double.MAX_VALUE;
		double latMax = Double.MIN_VALUE;
		double lonMin = Double.MAX_VALUE;
		double lonMax = Double.MIN_VALUE;

		for (Shapes s : list) {

			double lat = s.getShape_pt_lat() * -1;
			double lon = s.getShape_pt_lon();

			if (lat < latMin) {
				latMin = lat;
			}
			if (lat > latMax) {
				latMax = lat;
			}
			if (lon < lonMin) {
				lonMin = lon;
			}
			if (lon > lonMax) {
				lonMax = lon;
			} // get lat, lon

		}

		int N = 100;
		int[][] quantity = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				quantity[i][j] = 0;
			}
		}

		String id = " ";

		for (Shapes s : list) {
			double lat = s.getShape_pt_lat() * -1;
			double lon = s.getShape_pt_lon();

			double latcopy = (latMax - latMin) / N;
			double loncopy = (lonMax - lonMin) / N;

			int i = (int) Math.floor((lat - latMin) / latcopy);
			int j = (int) Math.floor((lon - lonMin) / loncopy);

			String shapeId = s.getShape_id();

			if (id != shapeId) {
				if (i == N && j == N) {
					quantity[i - 1][j - 1]++;
				} else if (i == N) {
					quantity[i - 1][j]++;
				} else if (j == N) {
					quantity[i][j - 1]++;
				} else {
					quantity[i][j]++;
				}// quantity
			}

			id = shapeId;

		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(quantity[i][j] + " ");
			}
			System.out.println();
		}
	}
}