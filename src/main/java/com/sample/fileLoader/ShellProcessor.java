package com.sample.fileLoader;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ShellProcessor {
	
	private static Logger logger = LogManager.getLogger(ShellProcessor.class);
	private String shellCmd;
	
	public ShellProcessor(String cmdString){
		shellCmd = cmdString;
	}
	
	public int executeShellCommand(String param1, String param2, String param3){
		
		int returnVal = -1;
		try{
			
			// -- Linux, Run a shell command
			//Process process = Runtime.getRuntime().exec("ls /home/vks/");
			//String[] cmd = { "bash", "-c", "~/path/to/shellscript.sh foo bar" };
							
			// -- Windows Run a bat file
			//String[] cmd = {"cmd", "/c", shellCmd, "admin", "pass", outputPath};
			String[] cmd = {"cmd", "/c", shellCmd, param1, param2, param3};
			Process process = Runtime.getRuntime().exec(cmd);
			
			returnVal = process.waitFor();
			if (returnVal == 0) {
				logger.info("Successfully executed command!");
			} else {
				logger.error("Failed to execte command");
			}
		}
		catch(IOException e){
			logger.error(e.getMessage());
			logger.error(e);
		}
		catch(InterruptedException e){
				logger.error(e.getMessage());
				logger.error(e);
		}
		return returnVal;
	}
}
