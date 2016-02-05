package utilities;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utilities {

	public static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

	public static void makeDir(String dir) {
		new File(dir).mkdirs();
	}

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

	private static boolean isEmpty(int[] n) {

		return n == null || n.length == 0;

	}

	public static void quickSort(int[] n) {

		if (isEmpty(n))

			return;

		quickSort(n, 0, n.length - 1);

	}

	private static void quickSort(int[] n, int l, int h) {

		if (isEmpty(n))

			return;

		if (l < h) {

			int pivot = partion(n, l, h);

			quickSort(n, l, pivot - 1);

			quickSort(n, pivot + 1, h);

		}

	}

	private static int partion(int[] n, int start, int end) {

		int tmp = n[start];

		while (start < end) {

			while (n[end] >= tmp && start < end)

				end--;

			if (start < end) {

				n[start++] = n[end];

			}

			while (n[start] < tmp && start < end)

				start++;

			if (start < end) {

				n[end--] = n[start];

			}

		}

		n[start] = tmp;

		return start;

	}

	public static void main(String... args) {
		File[] files = new File("C:/").listFiles();
		showFiles(files);
	}

	public static void showFiles(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.println("Directory: " + file.getName());
				showFiles(file.listFiles()); // Calls same method again.
			} else {
				System.out.println("File: " + file.getName());
			}
		}
	}
}
