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
			
			/*
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
			*/
		}
		
		logger.info("Completed!");
	}

}
