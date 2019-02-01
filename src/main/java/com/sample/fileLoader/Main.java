package com.sample.fileLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Main {
	
	private static Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		
		logger.info("\n");
		logger.info("Starting...");
		Properties prop = new Properties();
		InputStream input = null;
		
		try{
			//Read config file
			input = new FileInputStream(".\\config\\config.properties");			
			prop.load(input);
			
			logger.info("Will look for files in: "+ prop.getProperty("sourceDir"));
			logger.info("Shell commad to run: " + prop.getProperty("shellCmd"));
			logger.info("Output path: " + prop.getProperty("outputPath"));
			String sourceDir = prop.getProperty("sourceDir");
			String shellCmd = prop.getProperty("shellCmd");
			String outputPath = prop.getProperty("outputPath");
			
			//Execute shell command
			try{
				
				// -- Linux --
				// Run a shell command
				//Process process = Runtime.getRuntime().exec("ls /home/vks/");
				//String[] cmd = { "bash", "-c", "~/path/to/shellscript.sh foo bar" };
								
				// Run a bat file
				String[] cmd = {"cmd", "/c", shellCmd, "admin", "pass", outputPath};
				Process process = Runtime.getRuntime().exec(cmd);
				
				int exitVal = process.waitFor();
				if (exitVal == 0) {
					logger.info("Successfully executed command!");					
				} else {
					logger.error("Failed to execte command");
				}
			}
			catch(InterruptedException e){
					logger.error(e.getMessage());
					logger.error(e);
			}
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
		logger.info("Completed!");
	}

}
