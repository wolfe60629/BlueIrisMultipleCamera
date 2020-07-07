# Blue Iris Multiple Camera


## How it Works

When a camera in Blue Iris gets triggered, it will run the client side of this application. The client application will communicate with the server application through a TCP stream. If both cameras are triggered within a 15 second window, an alert will be made. This double trigger method is used to minimize the amount of false alerts made through Blue Iris.


## Client Side

The client application's only job is to contact the server. It processes the user requests, and will contact the server with a command. This was implemented so multiple clients could send messages to the server simultaneously. Since the application only has a 15 second window in which two triggers can be made, this helped the processing time for each alert.


## Server Side

The server side follows through with a request that was made from the client. Once a request is given to the application to trigger a camera, a trigger bit is set, and the time that the trigger occured is documented. If another trigger is set within 15 seconds of the first trigger, an alert will be made to the user. This application is multi-threaded to enhance the performance and eliminate hang up during the process.



## Java Files

### Communicator.java

Communicates with the ServerBridge over port 6500 on localhost. The communicator and server are meant to be on the same machine as the Blue Iris installation. This can be changed if needed.


### ServerBridgeDriver.java

Defines the two cameras that are needed for the ServerBridge. It also sets up an instance of the ServerBridge, as well as the CameraService. 


### ServerBridge.java

The serverBridge is the class that is directly in contact with the communicator. This class acts as a bridge between the cameraService and the communicator. This class is multithreaded, so messages can be indefinitely processed without hangup.


### Camera.java

This is a node class to hold camera information such as the last triggered time, and triggered state.


### CameraService.java

This class contains the logic on when to alert the user. It reads values from the triggered Node to see if an alert should be made.


### IniReader.java

This class was implemented to read an INI file containing the information of the SMTP server that will be used to send alerts. By default, for security, the INI file is not uploaded. The program reads data using the "=" seperator in the below syntax. In the current state the Alert.ini file will need to be placed in the bin folder.  

username={username for smtp}
password={password for smtp}
recipient1={recipient}
recipient2={optional recipient}
host={host smtp}
alertFolder={folder that contains photo attachments}
smtpport={port for smtp}
starttls={true or false}


### Alert.java

This class is what reaches out to the user to let them know that both cameras have been triggered. When sending an alert, all photos that will need to be sent with the alert should be saved in the AlertFolder from the Alert.ini


## Batch Files

### runServer.bat

This calls the main class from the ServerBridgeDriver.


### runscript.bat



