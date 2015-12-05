package analysingFilesInFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

public class CountQuantityOfFiles {

	public static void main(String[] args) {

		String folderName = "/Users/Myron/Documents/资料/施红黎/SHL";
		File allFile = new File(folderName);
		File[] listOfAllFiles = allFile.listFiles();
		List<String> subFolders = new ArrayList<String>();

		for (int i = 0; i < listOfAllFiles.length; i++) {
			if (listOfAllFiles[i].isDirectory()) {
				subFolders.add(listOfAllFiles[i].getName());
			}
		}

		for (String folder : subFolders) {

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

			System.out.println("Folder: " + folder + " Number of XML files: " + xmlFiles.size());

		}
	}
}