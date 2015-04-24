package com.undebugged.trees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EdgeWeightTreeParser {

	private final File file;
	private Scanner read;
	private final Pattern pattern = Pattern.compile("(\\d+);(\\d+);(\\d+)");

	public EdgeWeightTreeParser(String filename) {
		file = new File(filename);

	}

	public boolean hasNext() {
		if (read == null) {
			try {
				read = new Scanner(file);
			} catch (FileNotFoundException e) {
				throw new IllegalStateException("file not found");
			}
		}
		return (read != null && read.hasNextLine());
	}

	public WeightedNode next() {
		if (!hasNext()) {
			throw new IllegalStateException("file parsing finished");
		}
		String line = read.nextLine();
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			long id = Long.valueOf(matcher.group(1));
			long parent = Long.valueOf(matcher.group(2));
			long weight = Long.valueOf(matcher.group(3));
			return new WeightedNode(id, parent, weight);
		}
		throw new IllegalStateException("error while parsing file");
	}

}
