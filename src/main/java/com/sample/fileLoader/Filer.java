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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/*
 * Used to capture an initail list of files with today's timestamp
 */

public class Filer {
	
	private static Logger logger = LogManager.getLogger(Filer.class);
	private String sourceDir;
	private String[] filesInDIr;
	private List<String> currentDayFiles = new ArrayList<String>();
	
	public Filer(String sourceDir){
		this.sourceDir = sourceDir;
	}
	
	/**
	 * get a list of files in the sourceDir
	 */
	private void getFilPathomSourceDir(){		
		File sourcePath = new File(sourceDir);		
		filesInDIr = sourcePath.list();
	}
	/**
	 * Gets a list of files with the current day's time stamp.
	 * @return a list of files with the current day's data
	 */
	public List<String> getCurrentDayFiles(){
		currentDayFiles.clear();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDateTime now = LocalDateTime.now();
		logger.info("Looking for files with "+ dtf.format(now) + " in the file name");
		
		getFilPathomSourceDir();
		for (String file : filesInDIr) {
			if (file.contains(dtf.format(now)+"_"))
					currentDayFiles.add(file);
		}
		
		for (String file : currentDayFiles) {
			logger.info("Files with timestamp: "+dtf.format(now) + ": " + file);
		}
		
		return currentDayFiles;
	}
	
	
}
