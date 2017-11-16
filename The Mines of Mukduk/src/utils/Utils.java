package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for utility functions.
 * 
 * @author Dustin
 */
public class Utils {

	// Reads in a file
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null)
				builder.append(line + "\n");

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}
	
	public static double clamp(double var, double min, double max) {
		if (var >= max)
			return var = max;
		else if (var < min)
			return var = min;
		else
			return var;
	}
}