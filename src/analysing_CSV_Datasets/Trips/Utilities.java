package analysing_CSV_Datasets.Trips;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utilities {

	public static List<String> readFile(String file, boolean discardFirstLine)
			throws IOException {

		FileInputStream fstream = new FileInputStream(file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		if (discardFirstLine) {
			br.readLine();
		}

		String strLine;

		List<String> list = new ArrayList<String>();

		while ((strLine = br.readLine()) != null) {
			list.add(strLine);
		}

		br.close();

		return list;

	}

}
