package analysing_TXT_Datasets.Shapes;

import java.util.ArrayList;
import java.util.List;

public class AnalyseData {

	public List<Shapes> parseStrLines(List<String> list) {

		List<Shapes> shapesList = new ArrayList<Shapes>();

		for (String s : list) {

			String[] splitStr = s.split("\",\"");

			Shapes shapes = new Shapes();

			shapes.setShape_id(splitStr[0].substring(1, splitStr[0].length()));
			shapes.setShape_pt_lat(Double.parseDouble(splitStr[1]));
			shapes.setShape_pt_lon(Double.parseDouble(splitStr[2]));
			shapes.setShape_pt_sequence(Integer.parseInt(splitStr[3]));
			shapes.setShape_dist_traveled(Double.parseDouble(splitStr[4]
					.substring(0, splitStr[4].length() - 1)));

			shapesList.add(shapes);

		}

		return shapesList;

	}

	public int[][] calculateDensity(List<Shapes> list) {

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

		return quantity;

	}

}
