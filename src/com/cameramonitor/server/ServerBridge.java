package com.cameramonitor.server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

public class ServerBridge extends Thread {
	private CameraService cameraService;
	
	
	public ServerBridge(CameraService cameraService) { 
		this.cameraService = cameraService;
	}
	
	public void run() { 	
		ServerSocket serverSocket;
		Socket socket;
		InputStreamReader IR;
		BufferedReader BR;
		
		while (1==1) { 
			try { 
				//Create Server Socket
				serverSocket = new ServerSocket(6500);
				socket = serverSocket.accept();
				
				//Read Data That Is Send
					IR = new InputStreamReader(socket.getInputStream());
					BR = new BufferedReader(IR);
					
					String message;
						while ((message = BR.readLine()) != null) { 
							// Read Message And Return Data To CameraService
								System.out.println("Recieved Message: " + message +  " - " + LocalTime.now());
								cameraService.parseData(message);
						}
					
					serverSocket.close();
			}catch (Exception e) { 
				
			}	
	}
	}
}
