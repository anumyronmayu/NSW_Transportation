package analysingFilesInFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

public class CountQuantityOfFiles {

	private static boolean isEmpty(int[] n) {

		return n == null || n.length == 0;

	}

	public static void quickSort(int[] n) {

		if (isEmpty(n))

			return;

		quickSort(n, 0, n.length - 1);

	}

	public static void quickSort(int[] n, int l, int h) {

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

	public static void main(String[] args) {

		String folderName = "/Users/Myron/Documents/2015_nswtransport/Transx/transxchange";
		File allFile = new File(folderName);
		File[] listOfAllFiles = allFile.listFiles();
		List<String> subFolders = new ArrayList<String>();

		for (int i = 0; i < listOfAllFiles.length; i++) {
			if (listOfAllFiles[i].isDirectory()) {
				subFolders.add(listOfAllFiles[i].getName());
			}
		}

		int[] array = new int[subFolders.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(subFolders.get(i));
		}

		quickSort(array);

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int j = 0; j < array.length; j++) {

			String folder = String.valueOf(array[j]);

			String subFolder = folderName + "/" + folder;
			File subFolderFile = new File(subFolder);
			File[] listOfFiles = subFolderFile.listFiles();
			List<String> xmlFiles = new ArrayList<String>();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()
						&& FilenameUtils.getExtension(listOfFiles[i].getPath())
								.equals("xml")) {
					xmlFiles.add(listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("Directory " + listOfFiles[i].getName());
				}
			}

			map.put(array[j], xmlFiles.size());

			System.out.println("Folder: " + folder + " Number of XML files: "
					+ xmlFiles.size());

		}
	}
}