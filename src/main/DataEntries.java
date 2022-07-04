package main;

public class DataEntries {
	int whenAdded;
	String dataLine;
	String dataStatus;
	String dataComment;

	int id;
	String fullName;
	float riskFactor;
	String separator = ",";

	String rawId;
	String rawFullName;
	String rawRiskFactor;

	public DataEntries() {
		///
	}

	public void AddDataEntries(int whenAdded, String dLine, String dStatus, String dComment) {
		this.whenAdded = whenAdded;
		this.dataLine = dLine;
		this.dataStatus = dStatus;
		this.dataComment = dComment;

		String separator = ",";
		String[] arrOfStr = dataLine.split(separator);

		try {

			this.rawId = arrOfStr[0];

		} catch (ArrayIndexOutOfBoundsException ex) {
			this.rawId = "";
		}

		try {

			this.rawFullName = arrOfStr[1];

		} catch (ArrayIndexOutOfBoundsException ex) {
			this.rawFullName = "";
		}

		try {

			this.rawRiskFactor = arrOfStr[2];

		} catch (ArrayIndexOutOfBoundsException ex) {
			this.rawRiskFactor = "";
		}

	}

	public void AddDataPoints(int id, String fullName, float riskFactor) {
		this.id = id;
		this.fullName = fullName;
		this.riskFactor = riskFactor;

	}

	public String getDataLine() {
		return this.dataLine;
	}

	public String getdataStatus() {
		return this.dataStatus;
	}

	public String getdataComment() {
		return this.dataComment;
	}

	public void setdataComment(String dComment) {
		this.dataComment = dComment;
	}

	public void setdataStatus(String dStatus) {
		this.dataStatus = dStatus;

	}

	public String getRawID() {
		return this.rawId;
	}

	public String getRawFullName() {
		return this.rawFullName;
	}

	public String getRawRiskFactor() {
		return this.rawRiskFactor;
	}

	public void printAll() {

		System.out.println(dataLine + " --- " + dataStatus + " - " + dataComment);
	}
	
}
