package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Place")
public class Place {

	private String NptgLocalityRef;
	private String Suburb;

	private Location Location;

	public String getNptgLocalityRef() {
		return NptgLocalityRef;
	}

	public void setNptgLocalityRef(String nptgLocalityRef) {
		NptgLocalityRef = nptgLocalityRef;
	}

	public String getSuburb() {
		return Suburb;
	}

	public void setSuburb(String suburb) {
		Suburb = suburb;
	}

	public Location getLocation() {
		return Location;
	}

	public void setLocation(Location location) {
		Location = location;
	}

}
