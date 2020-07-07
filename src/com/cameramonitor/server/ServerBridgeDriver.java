package com.cameramonitor.server;
public class ServerBridgeDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Camera fCam = new Camera("fCam");
		Camera gCam = new Camera("gCam");
		CameraService cameraService = new CameraService(fCam,gCam);
		
		ServerBridge bridge = new ServerBridge(cameraService);
		
		
		bridge.start();
		
	}

}
