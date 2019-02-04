package com.sample.fileLoader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.util.StringUtils;

/*
 * Used to capture an initail list of files with today's timestamp
 */

public class Filer {
	
	private static Logger logger = LogManager.getLogger(Filer.class);
	private String sourceDir;
	private String[] filesInDIr;
	private List<String> currentDayFiles = new ArrayList<String>();
	private String currentyyyyMMdd;
	private Map<String,Integer> fileMap = new HashMap<String, Integer>();
	
	public Filer(String sourceDir){
		this.sourceDir = sourceDir;
	}
	
	/**
	 * get a list of files in the sourceDir, using a (date)wild card
	 */
	private void getFileFromSourceDir(String dateStrWildCard){		
		DirectoryScanner scanner = new DirectoryScanner();
		
		if (dateStrWildCard.length() == 0)
			scanner.setIncludes(new String[]{"**/*.java"});
		else
			scanner.setIncludes(new String[]{dateStrWildCard+"_"});
		scanner.setBasedir("C:/Temp");
		scanner.setCaseSensitive(false);
		scanner.scan();
		String[] files = scanner.getIncludedFiles();
		
		File sourcePath = new File(sourceDir);		
		filesInDIr = sourcePath.list();
	}
	
	/**
	 * Gets a list of files with the current day's time stamp.
	 * @return a list of files with the current day's data
	 */
	public Map<String,Integer> getCurrentDayFiles(String isBackfill){
		currentDayFiles.clear();
		
		//get current Date, search for files with this timestamp.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDateTime now = LocalDateTime.now();
		currentyyyyMMdd = dtf.format(now);
		logger.info("Looking for files with "+ currentyyyyMMdd + " in the file name");
		
		//if backfill is false, then process current day's file.
		String wildCard = currentyyyyMMdd;
		if (isBackfill.equalsIgnoreCase("TRUE"))
			wildCard = "";
		
		getFileFromSourceDir(wildCard);
		
		for (String file : filesInDIr) {
			if (!fileMap.containsKey(file)){
				logger.info("Found file: "+file);
				fileMap.put(file, 0);
			}
				
		}
		return fileMap;
	}
	
	//public 
	
}
