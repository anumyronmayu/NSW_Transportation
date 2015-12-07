package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Note")
public class Note {

	private String NoteCode;
	private String NoteText;

	public String getNoteCode() {
		return NoteCode;
	}

	public void setNoteCode(String noteCode) {
		NoteCode = noteCode;
	}

	public String getNoteText() {
		return NoteText;
	}

	public void setNoteText(String noteText) {
		NoteText = noteText;
	}

}
