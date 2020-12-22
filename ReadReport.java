package prob1;

import prob2.*;

public class ReadReport {
	private MartianManager mm;
	private String fileName;
	private int numLinesRead;
	private int numSuccessfullyAdded;
	private int numAlreadyExist;
	private int numIllFormed;
	public ReadReport(MartianManager mm, String fileName, int numLinesRead, int numSuccessfullyAdded,
			int numAlreadyExist, int numIllFormed) {
		this.mm = mm;
		this.fileName = fileName;
		this.numLinesRead = numLinesRead;
		this.numSuccessfullyAdded = numSuccessfullyAdded;
		this.numAlreadyExist = numAlreadyExist;
		this.numIllFormed = numIllFormed;
	}
	
	public String getReport() {
		StringBuilder strb = new StringBuilder();
		
		strb.append("*** Read Report ***" + "\n");
		strb.append("File: " + fileName + "\n");
		strb.append("Martian Manager:\n");
		strb.append(mm.toString() + "\n");
		strb.append("Martian Manager:\n");
		strb.append("Num lines read               :" + numLinesRead + "\n");
		strb.append("Num Martians added           :" + numSuccessfullyAdded + "\n");
		strb.append("Num ill-formed lines         :" + numIllFormed + "\n");
		strb.append("Num already exist (not added):" + numAlreadyExist + "\n");
		
		return strb.toString();
	}
	

}
