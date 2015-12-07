package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Operator")
public class Operator {

	@XStreamAsAttribute
	private String id;

	private String OperatorCode;
	private String OperatorShortName;
	private String OperatorNameOnLicence;
	private String TradingName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperatorCode() {
		return OperatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		OperatorCode = operatorCode;
	}

	public String getOperatorShortName() {
		return OperatorShortName;
	}

	public void setOperatorShortName(String operatorShortName) {
		OperatorShortName = operatorShortName;
	}

	public String getOperatorNameOnLicence() {
		return OperatorNameOnLicence;
	}

	public void setOperatorNameOnLicence(String operatorNameOnLicence) {
		OperatorNameOnLicence = operatorNameOnLicence;
	}

	public String getTradingName() {
		return TradingName;
	}

	public void setTradingName(String tradingName) {
		TradingName = tradingName;
	}

}
