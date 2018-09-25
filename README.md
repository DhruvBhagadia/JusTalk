# SocketProgramming
A basic chat application using implemented using Socket programming

## Introduction

To connect to other machine we need a socket connection. A socket connection means the two machines have information about each other’s network location (IP Address) and TCP port.
- Here, various clients can connect to the server and then chat with each other following "message@recipient" format.
- A Client can send another CLient a "Hello" image using image@recipient
- The clients can also view the users who are currently logged-in
- The clients can quit whenever they wish to

## Screenshots

### Initially, three clients connect to the server.

#### Server
![screenshot from 2018-09-24 16-25-06](https://user-images.githubusercontent.com/32220881/45948872-55bc8880-c017-11e8-8c81-627a49dd7757.png)
--------------------------------------------------------------------------------------------------------------
#### Client0
![screenshot from 2018-09-24 16-25-38](https://user-images.githubusercontent.com/32220881/45948882-61a84a80-c017-11e8-8283-486756373d9a.png)

### To check who all are logged-in
#### Client0
![screenshot from 2018-09-24 16-51-21](https://user-images.githubusercontent.com/32220881/45949639-29eed200-c01a-11e8-8b44-3cd358d26295.png)


### After chat between two clients.

#### Client0
![screenshot from 2018-09-24 16-28-05](https://user-images.githubusercontent.com/32220881/45948895-6ec53980-c017-11e8-85a1-e640af5caca6.png)
-----------------------------------------------------------------------------------------------------------------------
#### Client1
![screenshot from 2018-09-24 16-28-21](https://user-images.githubusercontent.com/32220881/45948897-7258c080-c017-11e8-897a-805b4852120c.png)
-----------------------------------------------------------------------------------------------------------------------
#### Server
![screenshot from 2018-09-24 16-27-48](https://user-images.githubusercontent.com/32220881/45948889-6a008580-c017-11e8-913c-deba5a1b238d.png)

### Once Client1 logs out.

#### Client1
![screenshot from 2018-09-24 16-29-20](https://user-images.githubusercontent.com/32220881/45948903-7684de00-c017-11e8-92fe-8c650f9f1826.png)
------------------------------------------------------------------------------------------------------------------
#### Client0
![screenshot from 2018-09-24 16-29-45](https://user-images.githubusercontent.com/32220881/45948909-78e73800-c017-11e8-89e0-7eedc90e7e72.png)

### When client gives a wrong message format
![screenshot from 2018-09-24 16-56-25](https://user-images.githubusercontent.com/32220881/45949823-e6489800-c01a-11e8-8cf0-0f036c5aa74e.png)
--------------------------------------------------------------------------------------------------------------------

### To send a "Hello" image
#### Client who wants to send image
![screenshot from 2018-09-25 08-39-12](https://user-images.githubusercontent.com/32220881/45991736-dfb23300-c0a3-11e8-871f-3e0f0ae6b1bf.png)
------------------------------------------------------------------------------------------------------------------------
#### Recipient
![screenshot from 2018-09-25 08-38-50](https://user-images.githubusercontent.com/32220881/45991733-de810600-c0a3-11e8-9559-5c67e356bcb9.png)
-------------------------------------------------------------------------------------------------------------------------
#### Server
![screenshot from 2018-09-25 08-39-35](https://user-images.githubusercontent.com/32220881/45991737-e0e36000-c0a3-11e8-8a23-fbde094d1d3d.png)
--------------------------------------------------------------------------------------------------------------------------
#### Before sending the image
![screenshot from 2018-09-25 09-12-18](https://user-images.githubusercontent.com/32220881/45991745-e345ba00-c0a3-11e8-9e6b-1beec4d6188f.png)
--------------------------------------------------------------------------------------------------------------------------
#### After sending the image

One copy stored on Server and one on Recipient's machine

![screenshot from 2018-09-25 09-13-04](https://user-images.githubusercontent.com/32220881/45991747-e476e700-c0a3-11e8-9b57-aee9d9ceb611.png)
--------------------------------------------------------------------------------------------------------------------------
### On sending image to a recipient who is not logged-in
![screenshot from 2018-09-25 09-10-26](https://user-images.githubusercontent.com/32220881/45991743-e17bf680-c0a3-11e8-8447-f830388d4366.png)
--------------------------------------------------------------------------------------------------------------------------


## Acknowledgment
- Inspiration for the project came from [geeksforgeeks](https://www.geeksforgeeks.org/multi-threaded-chat-application-set-1/)
