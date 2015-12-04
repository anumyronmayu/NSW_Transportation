package analysingFilesInFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

public class CountQuantityOfFiles {

	public static void main(String[] args) {

		HashMap<String, Integer> map = new HashMap<String, Integer>();

		List<String> xmlFileName = new ArrayList<String>();
		List<String> folders = new ArrayList<String>();

		String folderName = "/Users/Myron/Downloads/SHL";
		File othersFolder = new File(folderName);
		File[] listOfFilesOthers = othersFolder.listFiles();

		for (int i = 0; i < listOfFilesOthers.length; i++) {
			if (listOfFilesOthers[i].isFile()
					&& FilenameUtils.getExtension(
							listOfFilesOthers[i].getPath()).equals("xml")) {
				xmlFileName.add(listOfFilesOthers[i].getName());
			} else if (listOfFilesOthers[i].isDirectory()) {
				folders.add(listOfFilesOthers[i].getName());
			}
		}

		for (String folder : folders) {

			String subFolder = folderName + "/" + folder;

			File subFolderFile = new File(subFolder);
			File[] listOfFiles = subFolderFile.listFiles();

			List<String> xmlFiles = new ArrayList<String>();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()
						&& FilenameUtils.getExtension(listOfFiles[i].getPath())
								.equals("xml")) {
					xmlFiles.add(listOfFiles[i].getName());
					System.out.println(listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {
					folders.add(listOfFiles[i].getName());
				}
			}

			System.out.println(folder + ": " + xmlFiles.size());
		}

	}

}
