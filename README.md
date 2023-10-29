# Understanding the Systems with Complex Connections by Zaur Khudiev
## Table of Contents
* [About the Project](#about-the-project)
* [Built With](#built-with)
* [Prerequisites](#prerequisites)
* [Getting Started](#getting-started)
* [Usage](#usage)
* [Authors](#authors)


## About the Project
This project involves creating two software components, a sender and a reader, that enable asynchronous messaging using a database. The goal is to allow users to send and receive messages through a multi-threaded system while ensuring message integrity and proper synchronization.

   
## Built With
The project is built with Java programming language.


## Prerequisites
First of all, you need to download the JDK (Java Development Kit) to run the Java Application. 
You can use the link to download:[ https://www.oracle.com/java/technologies/downloads/#jdk21-windows](https://www.oracle.com/java/technologies/downloads/#jdk21-windows)

After installing the JDK, check on cmd if it is active by typing "javac -version", and if it does not show the version number, make sure that you have set it up correctly.


## Getting Started

First of all clone the project to your computer and then change the directory to the "\src\main\java\org\example" folder.

Alternatively, you will need to download the zip file (Code > Local > Download Zip) on GitHub, after downloading the file, you need to extract the zip file and you will see the folder which is called “processes-and-asynchronous-messaging-zaurkhudiev” 

For the next process check below:

1.	Copy the path of the folder Reader.java and Sender.java that are located in. On my computer, it looks like “C:\Users\user\Downloads\processes-and-asynchronous-messaging-zaurkhudiev-main\src\main\java\org\example”

2.	Open cmd

3.	Type cd and paste path 

 4. Write "javac Sender.java" then "java Sender.java"

4.1 Enter IP addresses + port numbers and database names, use: <database_ip:port_number/database_name> if you have finished writing the inputs please type: done. IT WANTS YOU TO ENTER NOT ONLY THE IP ADDRESS but also PORT NUMBER AND DATABASE NAME because THERE CAN BE DIFFERENT PORT NUMBERS OR DATABASE NAMES. You need to write for example like

For first database: 127.0.0.1:5432/postgres

for second database: 127.0.0.2:5433/root

and so on


4.2 Write your name

4.3 Write your message

   
5. Write "javac Reader.java" then "java Reader.java"

5.1 Enter IP addresses + port numbers and database names, use: <database_ip:port_number/database_name>

5.2 Write your name



Note: You can use "exit" keyword to exit the program






## Authors
Zaur Khudiev


