package com.cameramonitor.server;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class IniReader {
	private ArrayList<IniKey> iniKeys = new ArrayList<>(); 
	
	public IniReader(String path) throws Exception { 
		//Declarations
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		String[] keyValue;
		
		//Read INI
		while ((line = br.readLine()) != null) { 
			keyValue = line.split("=");
			if (keyValue.length > 1 ) { 
				iniKeys.add(new IniKey(keyValue[0],keyValue[1]));
			}else { 
				iniKeys.add(new IniKey(keyValue[0],""));
			}
			
		}
		
		br.close();
		fr.close();
	}
	
	public String getValue(String key) { 
		for (IniKey currKey : iniKeys) {
			if (currKey.key.equalsIgnoreCase(key)) { 
				return currKey.value;
			}
		}
		return null;
	}
	

	//Ini Key Storage Class
	 class IniKey { 
		public String key;
		public String value;
		
		public IniKey(String key, String value) { 
			this.key = key;
			this.value = value;
		}
	}
}

