package prob1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import prob2.*;

public class MartianManagerIO {

	/**
	 *  DO NOT ALTER THIS METHOD.
	 */
	public static void writeMartians(String fileName, MartianManager mm) {
		File file = new File(fileName);
        try {
			writeMartiansFile(file, mm);
		}
        catch (FileNotFoundException e) {
			System.out.println("Error writing file");
			e.printStackTrace();
		}
	}
	
	/**
	 *  YOU  WRITE THIS METHOD.
	 *  
	 *  Write the martians in the MartianManager to the file. The format is exactly the same
	 *  as specified in the homework document for reading valid data: G I V or R I V T.
	 */
	private static void writeMartiansFile(File file, MartianManager mm) throws FileNotFoundException {
		String g = "G";
		String r = "R";
		
		try {
			PrintWriter writer = new PrintWriter( file);

			for( int i=0; i < mm.getNumMartians(); i++ ) {
				Martian m = mm.getMartianAt(i);
				if (m instanceof RedMartian) {
					int id = m.getId();
					int v = m.getVolume();
					int t =  ((RedMartian)m).getTenacity();
					writer.print(""+ r + " " +  id + " " + v + " " + t + "\n");
				}
				else {
				int id = m.getId();
				int v = m.getVolume();
				writer.print(""+ g + " " +  id + " " + v + "\n");
				}
			}
			
			writer.close();
			System.out.println( "File written" );
		}
		catch (IOException ioe) {
			System.out.println("Problem creating file or writing");
		}
	}

	/**
	 *  DO NOT ALTER THIS METHOD.
	 */
	public static ReadReport readMartians(String fileName) {
		File file = new File(fileName);
		ReadReport report = null;
        try {
			report = readMartiansFile(file);
		}
        catch (FileNotFoundException e) {
			System.out.println("Error reading file");
			e.printStackTrace();
		}
		return report;
	}
	
	/**
	 * YOU WRITE THIS METHOD.
	 * 
	 * Reads a text file that contains Martian data and returns a ReadReport object. Details
	 * are in the homework document. 
	 * 
	 * @param file
	 * @return
	 * @throws RuntimeException
	 * @throws FileNotFoundException
	 */
	private static ReadReport readMartiansFile(File file) throws RuntimeException, FileNotFoundException {
		MartianManager mm = new MartianManager();
		String fileName = file.getName();
		int numLinesRead = 0;
		int numSuccessfullyAdded = 0;
		int numAlreadyExist = 0;
	    int numIllFormed = 0;
	 
		
		try {
			Scanner input = new Scanner(file);
			while(input.hasNext()) {
				
				String line = input.nextLine();
				String[] tokens = line.split("\\s");
			
				if(	((tokens[0].equals("G")) && (tokens.length == 3)) || ((tokens[0].equals("R")) && (tokens.length == 4))) {
				
					Martian m = martianilizer(tokens);
					if(m instanceof GreenMartian || m instanceof RedMartian) {
						if(!mm.addMartian(m)) {
							numAlreadyExist++;
						}
						else{
							mm.addMartian(m);
							numSuccessfullyAdded++;
						}
					
				}
					
			}
				else {
					numIllFormed++;
				}
				}
			
			input.close();
		}
		
		catch(IOException e) {
			System.out.println("Error reading file");
		}
		numLinesRead = numSuccessfullyAdded + numAlreadyExist + numIllFormed;
		ReadReport report= new ReadReport(mm, fileName, numLinesRead,numSuccessfullyAdded,numAlreadyExist,numIllFormed);
		return report;
	}
	public static Martian martianilizer(String[] tokens) {
		String name = tokens[0];
		int id = 0;
		int v = 0;
		int t = 0;
		if(name.equals("G")) {
			if(isIntegerParseInt(tokens[1]) && isIntegerParseInt(tokens[2])) {
				 id = Integer.parseInt(tokens[1]);
				  v = Integer.parseInt(tokens[2]);
				  Martian g = new GreenMartian(id, v);
		    return g;}
		}
		if(name.equals("R")) {
			if(isIntegerParseInt(tokens[1]) && isIntegerParseInt(tokens[2]) && isIntegerParseInt(tokens[3])) {
				  id = Integer.parseInt(tokens[1]);
				   v = Integer.parseInt(tokens[2]);
				   t = Integer.parseInt(tokens[3]);
				   Martian r = new RedMartian(id, v, t);
			return r;}
			
	}
		else {
			return null;
		}
		
		return null;
}
	public static boolean isIntegerParseInt(String str) {
		try {
			int x = Integer.parseInt(str);
			return true;
		}
		catch(NumberFormatException nfe) {
			return false;
		}
	}
	

}