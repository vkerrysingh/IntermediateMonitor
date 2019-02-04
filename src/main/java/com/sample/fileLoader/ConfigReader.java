package com.sample.fileLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConfigReader {
	
	private static Logger logger = LogManager.getLogger(ConfigReader.class);
	private Properties prop = new Properties();
	private InputStream input = null;
	
	public void readConfig(){
		
		try{
			//Read config file
			//input = new FileInputStream(".\\config\\config.properties"); //Windows
			input = new FileInputStream(".//config//config.properties");  //unix
			prop.load(input);
			
			logger.info("Will look for files in: "+ prop.getProperty("sourceDir"));
			logger.info("Shell commad to run: " + prop.getProperty("shellCmd"));
			logger.info("Output path: " + prop.getProperty("outputPath"));
			String sourceDir = prop.getProperty("sourceDir");
			String shellCmd = prop.getProperty("shellCmd");
			String outputPath = prop.getProperty("outputPath");
		}
		catch(IOException ex){
			logger.error(ex.getMessage());
			logger.error(ex);
		}
		finally{
			if(input != null){
				try{
					input.close();
				}
				catch(IOException e){
					logger.error(e.getMessage());
					logger.error(e);
				}
			}
		}
	}
	
	public Properties getConfig(){
		return prop;
	}
}
