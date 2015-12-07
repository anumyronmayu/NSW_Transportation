package analysing_XML_Datasets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ExtractData {

	public static void main(String[] args) throws IOException {
		
		String file = "";
		
		FileInputStream ft = new FileInputStream(file);
		String reqXml = IOUtils.toString(ft);
		
		XStream xs = new XStream(new DomDriver());

		
	}

}
