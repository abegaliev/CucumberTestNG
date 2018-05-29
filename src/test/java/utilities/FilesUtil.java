package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class FilesUtil {

	public static void writeFile(String fileName, String text) {

		BufferedWriter buffWr = null;
		FileWriter fileWr = null;

		try {
			fileWr = new FileWriter(fileName);
			buffWr = new BufferedWriter(fileWr);

			// String [] arr = text.split("[.]+"); // "[,/. ]+"
			// for (String str : arr) {
			// buffWr.write(str);
			// buffWr.newLine();
			// }
			buffWr.write(text);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffWr != null)
					buffWr.close();

				if (fileWr != null)
					fileWr.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static List<String> readFile(String fileName) {

		BufferedReader buffR = null;
		FileReader fileR = null;
		String line = null;
		ArrayList<String> text = new ArrayList<>();
		try {
			fileR = new FileReader(fileName);
			buffR = new BufferedReader(fileR);

			while ((line = buffR.readLine()) != null) {
				text.add(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		} catch (IOException e) {
			// System.out.println("Exception wile reading the file: "+fileName);
			new IOException();
		}

		return text;
	}

	
	public static void copyFile(File sourceFile, File file) {
		
		FileChannel sourceChannel = null;
		FileChannel destChannel = null;
		try {
			sourceChannel = new FileInputStream(sourceFile).getChannel();
			destChannel = new FileOutputStream(file).getChannel();
			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e);
		} catch (IOException e ) {
			System.out.println("Error while coping the file: "+e);
		} finally {
			try {
				sourceChannel.close();
				destChannel.close();
			} catch (IOException e) {
				System.out.println("Error while closing files: "+ e);
			}
		}

	}
	
	
	
	

}
