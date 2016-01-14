package analysing_TXT_Datasets.Shapes;

import java.io.IOException;
import java.util.List;

import utilities.Utilities;

public class App {

	public static void main(String[] args) throws IOException {

		String shapes_csv_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static/shapes.txt";
		List<String> fileLines = Utilities.readFile(shapes_csv_file, true);
		AnalyseData obj = new AnalyseData();
		List<Shapes> shapesList = obj.parseStrLines(fileLines);

		System.out.println(shapesList.size());

		int[][] quantity = obj.calculateDensity(shapesList);

		for (int i = 0; i < quantity.length; i++) {
			for (int j = 0; j < quantity.length; j++) {
				System.out.print(quantity[i][j] + " ");
			}
			System.out.println();
		}

	}
}