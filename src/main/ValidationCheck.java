package main;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationCheck {
	static String separator = ",";
	public List<DataEntries> dataEntriesObj = new ArrayList<DataEntries>();

	private static void main(String[] args) {

	}

	private Boolean commaCheck(String line) {

		int sepCounter = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == separator.toCharArray()[0]) {
				sepCounter++;
			}

		}
		if (sepCounter != 2) {
			return true;
		} else
			return false;

	}

	private Boolean idCheck(String id) {
		// String separator = ",";

		try {
			Integer.parseInt(id);
			return false;
		} catch (NumberFormatException ex) {
			// ex.printStackTrace();
			return true;
		}

	}

	private Boolean riskFactorCheck(String riskFactor) {

		try {
			float number = Float.parseFloat(riskFactor);
			if (number >= 1 && number <= 2.5)

				return false;
			else {
				return true;

			}
		} catch (NumberFormatException ex) {
			// ex.printStackTrace();
			return true;

		}

		catch (ArrayIndexOutOfBoundsException ex) {
			// ex.printStackTrace();
			return true;
		}
	}

	private Boolean fullNameCheck(String fullName) {
		// Pattern pattern = Pattern.compile("^[^\\s][a-žA-Ž]+[\\s][a-žA-Ž]+[^\\s]$",
		// Pattern.CASE_INSENSITIVE); //LT language not working on export
		Pattern pattern = Pattern.compile("^[^\\s]+[\\s][^\\s]+[^\\s]$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(fullName);
		boolean matchFound = matcher.find();
		if (matchFound) {
			return false; // Match found
		} else {
			return true; // Match not found
		}

	}

	public void validateAllFromFile(String filePath) {

		List<String> DataLines = new ArrayList<String>();
		List<DataEntries> dataEntriesObj = new ArrayList<DataEntries>();
		ReadFile file = new ReadFile();
		DataLines = file.main(filePath);

		for (int i = 0; i < DataLines.size(); i++) { // adding all data
			DataEntries da = new DataEntries();
			da.AddDataEntries(i, DataLines.get(i), "", "");
			dataEntriesObj.add(da);

		}

		for (int i = 0; i < dataEntriesObj.size(); i++) { // format validation
			ValidationCheck vc = new ValidationCheck();
			String id = dataEntriesObj.get(i).getRawID();
			String fullName = dataEntriesObj.get(i).getRawFullName();
			String riskFactor = dataEntriesObj.get(i).getRawRiskFactor();
			String dataLine = dataEntriesObj.get(i).getDataLine();

			if (vc.commaCheck(dataLine)) {
				dataEntriesObj.get(i).setdataStatus("DECLINED");
				dataEntriesObj.get(i).setdataComment("COMMA ERROR");

			} else {
				if (vc.idCheck(id)) {
					dataEntriesObj.get(i).setdataStatus("DECLINED");
					dataEntriesObj.get(i).setdataComment("WRONG ID");

				}

				if (vc.riskFactorCheck(riskFactor)) {
					dataEntriesObj.get(i).setdataStatus("DECLINED");
					dataEntriesObj.get(i).setdataComment("WRONG riskFactor");

				}

				if (vc.fullNameCheck(fullName)) {
					dataEntriesObj.get(i).setdataStatus("DECLINED");
					dataEntriesObj.get(i).setdataComment("WRONG fullName");

				}

			}

		}

		for (int i = 0; i < dataEntriesObj.size(); i++) {

			if (dataEntriesObj.get(i).getdataStatus() != "DECLINED") { //ID and Name check

				String currentID = dataEntriesObj.get(i).getRawID();
				String currentFullName = dataEntriesObj.get(i).getRawFullName();

				for (int x = i + 1; x < dataEntriesObj.size(); x++) {
					String checkID = dataEntriesObj.get(x).getRawID();
					String checkFullName = dataEntriesObj.get(x).getRawFullName();
					if (checkFullName.equals(currentFullName) && !checkID.equals(currentID)) {
						dataEntriesObj.get(x).setdataComment("DIFERENT ID/SAME NAME");
						dataEntriesObj.get(x).setdataStatus("DECLINED");

					}

				}

			}

		}

		for (int i = 0; i < dataEntriesObj.size(); i++) { // Newest Entries check
			if (dataEntriesObj.get(i).getdataStatus() != "DECLINED") {

				for (int x = i + 1; x < dataEntriesObj.size(); x++) {
					String currentID = dataEntriesObj.get(i).getRawID();
					String checkID = dataEntriesObj.get(x).getRawID();

					if (currentID.equals(checkID)) {
						dataEntriesObj.get(i).setdataComment("OLDER ID VALUE");
						dataEntriesObj.get(i).setdataStatus("DECLINED");

					}

				}

			}

		}

		for (int i = 0; i < dataEntriesObj.size(); i++) { // Add approved entries
			if (dataEntriesObj.get(i).getdataStatus() == "") {

				dataEntriesObj.get(i).setdataStatus("APPROVED");
				dataEntriesObj.get(i).setdataComment("");
			}

		}

		for (int a = 0; a < dataEntriesObj.size(); a++) { // Print Result
			String dl = dataEntriesObj.get(a).getDataLine();
			String ds = dataEntriesObj.get(a).getdataStatus();
			String dc = dataEntriesObj.get(a).getdataComment();

			System.out.println(dl + " " + ds + " " + dc);

		}

	}

}
