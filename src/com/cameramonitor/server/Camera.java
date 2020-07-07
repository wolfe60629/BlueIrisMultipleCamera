package com.cameramonitor.server;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Camera {
	private String name;
	private boolean isTriggered; 
	private Date lastModified;
	
	//Constructor
	public Camera(String name){ 
		this.name = name;
		this.isTriggered = false;
		lastModified = java.util.Calendar.getInstance().getTime();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isTriggered() {
		return isTriggered;
	}
	public void setTriggered(boolean isTriggered) {
		this.isTriggered = isTriggered;
		this.lastModified = java.util.Calendar.getInstance().getTime();

	}
	
	public Date getLastModified() { 
		return lastModified;
	}
	
	public static boolean isOutsideBounds(Camera camera1, Camera camera2) { 
		//Returns if the modified time is outside of the threshold
		long threshold = 15;
		Date date1 = camera1.lastModified;
		Date date2 = camera2.lastModified;
		long diffInMillies = Math.abs(date1.getTime() - date2.getTime());
	    long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		//System.out.println(diff);
	    if (diff < threshold) { 
	    	return false;
	    }else {
	    	return true;
	    }
	}
	public static boolean isOutsideResetBounds(Date date1, Date date2) { 
		//Returns if the modified time is outside of the threshold
		long threshold = 20;
		long diffInMillies = Math.abs(date1.getTime() - date2.getTime());
	    long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		//System.out.println(diff);
	    if (diff < threshold) { 
	    	return false;
	    }else {
	    	return true;
	    }
	}
}
