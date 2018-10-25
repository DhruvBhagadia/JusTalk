# SocketProgramming
A basic chat application implemented using Socket programming.

My motivation for this project was due to my interest in Computer Networks and CyberSecurity.

## Introduction

To connect to other machine we need a socket connection. A socket connection means the two machines have information about each other’s network location (IP Address) and TCP port.
- Here, various clients can connect to the server and then chat with each other following "message@recipient" format.
- A Client can send another Client any image using image@recipient. Here, a script runs on the background first downloading the image and sends that downloaded image to the recipient.
- The clients can also view the users who are currently logged-in
- The clients can quit whenever they wish to

## Screenshots

### - Initially, three clients connect to the server.

#### Server
![screenshot from 2018-09-24 16-25-06](https://user-images.githubusercontent.com/32220881/45948872-55bc8880-c017-11e8-8c81-627a49dd7757.png)
--------------------------------------------------------------------------------------------------------------
#### Client0
![screenshot from 2018-09-24 16-25-38](https://user-images.githubusercontent.com/32220881/45948882-61a84a80-c017-11e8-8283-486756373d9a.png)

### - To check who all are logged-in
#### Client0
![screenshot from 2018-09-24 16-51-21](https://user-images.githubusercontent.com/32220881/45949639-29eed200-c01a-11e8-8b44-3cd358d26295.png)


### - After chat between two clients.

#### Client0
![screenshot from 2018-09-24 16-28-05](https://user-images.githubusercontent.com/32220881/45948895-6ec53980-c017-11e8-85a1-e640af5caca6.png)
-----------------------------------------------------------------------------------------------------------------------
#### Client1
![screenshot from 2018-09-24 16-28-21](https://user-images.githubusercontent.com/32220881/45948897-7258c080-c017-11e8-897a-805b4852120c.png)
-----------------------------------------------------------------------------------------------------------------------
#### Server
![screenshot from 2018-09-24 16-27-48](https://user-images.githubusercontent.com/32220881/45948889-6a008580-c017-11e8-913c-deba5a1b238d.png)

### - Once Client1 logs out.

#### Client1
![screenshot from 2018-09-24 16-29-20](https://user-images.githubusercontent.com/32220881/45948903-7684de00-c017-11e8-92fe-8c650f9f1826.png)
------------------------------------------------------------------------------------------------------------------
#### Client0
![screenshot from 2018-09-24 16-29-45](https://user-images.githubusercontent.com/32220881/45948909-78e73800-c017-11e8-89e0-7eedc90e7e72.png)

### - When client gives a wrong message format
![screenshot from 2018-09-24 16-56-25](https://user-images.githubusercontent.com/32220881/45949823-e6489800-c01a-11e8-8cf0-0f036c5aa74e.png)
--------------------------------------------------------------------------------------------------------------------

### - To send an image
#### Client who wants to send image
![screenshot from 2018-09-29 17-15-53](https://user-images.githubusercontent.com/32220881/46245464-98150980-c40b-11e8-8da5-ac8a753291aa.png)
------------------------------------------------------------------------------------------------------------------------
#### Recipient
![screenshot from 2018-09-29 17-16-31](https://user-images.githubusercontent.com/32220881/46245468-9a776380-c40b-11e8-9382-e3f117885018.png)
-------------------------------------------------------------------------------------------------------------------------
#### Server
![screenshot from 2018-09-29 17-16-15](https://user-images.githubusercontent.com/32220881/46245466-99463680-c40b-11e8-9873-b8df41005c19.png)
--------------------------------------------------------------------------------------------------------------------------
#### The Client that wants to send image
![screenshot from 2018-09-29 17-14-35](https://user-images.githubusercontent.com/32220881/46245461-964b4600-c40b-11e8-8090-8d35c0fda12d.png)
--------------------------------------------------------------------------------------------------------------------------
#### Recipent receives the image

One copy stored on Server and one on Recipient's machine

![screenshot from 2018-09-29 17-14-58](https://user-images.githubusercontent.com/32220881/46245462-977c7300-c40b-11e8-87f4-84126f4cacc0.png)
--------------------------------------------------------------------------------------------------------------------------
### On sending image to a recipient who is not logged-in
![screenshot from 2018-09-25 09-10-26](https://user-images.githubusercontent.com/32220881/45991743-e17bf680-c0a3-11e8-8447-f830388d4366.png)
--------------------------------------------------------------------------------------------------------------------------


## Acknowledgment
- Inspiration for the project came from [geeksforgeeks](https://www.geeksforgeeks.org/multi-threaded-chat-application-set-1/)
- Script for downloading online image : [github repository](https://github.com/hardikvasa/google-images-download)
