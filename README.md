# Blue Iris Multiple Camera


## How it Works

When a camera in Blue Iris gets triggered, it will run the client side of this application. The client application will communicate with the server application through a TCP stream. If both cameras are triggered within a 15 second window, an alert will be made. This double trigger method is used to minimize the amount of false alerts made through Blue Iris.


## Client Side

The client application's only job is to contact the server. It processes the user requests, and will contact the server with a command. This was implemented so multiple clients could send messages to the server simultaneously. Since the application only has a 15 second window in which two triggers can be made, this helped the processing time for each alert.


## Server Side

The server side follows through with a request that was made from the client. Once a request is given to the application to trigger a camera, a trigger bit is set, and the time that the trigger occured is documented. If another trigger is set within 15 seconds of the first trigger, an alert will be made to the user. This application is multi-threaded to enhance the performance and eliminate hang up during the process.



## Java Files

### Communicator.java
### ServerBridgeDriver.java
### ServerBridge.java
### Camera.java
### CameraService.java
### IniReader.java

