package com.sample.fileLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tools.ant.DirectoryScanner;


public class Main {
	
	private static Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		
		logger.info("\n");
		logger.info("Starting...");
		
		try{
			ConfigReader config = new ConfigReader();
			Properties prop = config.getConfig();
			
			Filer filer = new Filer(prop.getProperty("sourceDir"));
			List<String> fileList = filer.getCurrentDayFiles();
		}
		catch(Exception e)
		{
			logger.fatal(e.getMessage());
			logger.fatal(e);
		}
		
		logger.info("Completed!");
	}

}
