package com.cameramonitor.client;
import java.io.PrintStream;
import java.net.Socket;

public class Communicator {
	public static void main(String[] args) throws Exception { 
		//Check to make sure that communicator is given a command
		if (args.length >= 3) { 
			communicate(args[0] + " " + args[1] + " " + args[2]);
		}
	}		
	
	
	private static void communicate(String message) throws Exception { 
		Socket socket = new Socket("127.0.0.1",6500);
		PrintStream outputStream = new PrintStream(socket.getOutputStream());
		outputStream.println(message);
		socket.close();
	}
	
	}

