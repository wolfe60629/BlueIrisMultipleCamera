package com.cameramonitor.server;

public class CameraService {
	Camera fCam;
	Camera gCam;
	String lastMessage ="";
	IniReader iniConfig;
	
	//Constructor
	public CameraService(Camera camera1,Camera camera2) {
		
		this.fCam = camera1;
		this.gCam = camera2;
		
		//Read INI
		try {
			iniConfig = new IniReader("Alert.ini");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parseData(String messageFromBridge) {
		switch (messageFromBridge) { 
			
		case "fCam -t true" : 
			fCam.setTriggered(true);
			if (messageFromBridge.equals(lastMessage) && !(Camera.isOutsideResetBounds(fCam.getLastModified(), gCam.getLastModified()))) { 
				
			}else 
			if (!(Camera.isOutsideBounds(gCam, fCam)) && checkForAlert() == true ) { 
				//If inside time frame and both are triggered
				Alert.sendEmail(iniConfig);
				resetStatus();
				
				}else if (!(Camera.isOutsideBounds(gCam, fCam)) && checkForAlert() == false ) {
				//If inside time frame and both are not triggered	
					
				}else if ((Camera.isOutsideBounds(gCam, fCam)) && checkForAlert() == false) { 
				//If outside time frame and both are not triggered
					
				}else if ((Camera.isOutsideBounds(gCam, fCam)) && checkForAlert() == true)  { 
				// if outside time frame and both are triggered
					resetStatus();
					fCam.setTriggered(true);
				}

			break;
		case "gCam -t true" :
			gCam.setTriggered(true);
			if (messageFromBridge.equals(lastMessage) && !(Camera.isOutsideResetBounds(fCam.getLastModified(), gCam.getLastModified()))) { 
				
			}else 
			if (!(Camera.isOutsideBounds(gCam, fCam)) && checkForAlert() == true ) { 
				//If inside time frame and both are triggered
				Alert.sendEmail(iniConfig);
				resetStatus();
				
				}else if (!(Camera.isOutsideBounds(gCam, fCam)) && checkForAlert() == false ) {
				//If inside time frame and both are not triggered	
					
				}else if ((Camera.isOutsideBounds(gCam, fCam)) && checkForAlert() == false) { 
				//If outside time frame and both are not triggered
					
				}else if ((Camera.isOutsideBounds(gCam, fCam)) && checkForAlert() == true)  { 
				// if outside time frame and both are triggered
					resetStatus();
					gCam.setTriggered(true);
				}
			
			
			break;
		case "fCam -t false" :
			gCam.setTriggered(false);
			break;
		case "gCam -t false" :
			gCam.setTriggered(false);
			break;
		}
	lastMessage = messageFromBridge;	
	}
	
	private boolean checkForAlert() { 
		if (fCam.isTriggered() && gCam.isTriggered()) { 
			return true;
		} else { 
			return false;
		}
	}
	
	private void resetStatus() { 
		fCam.setTriggered(false);
		gCam.setTriggered(false);

	}
}

