# Ecommerce-Retailer-Application

This file explains the setup of the application for effective build of the project
Project build info
This project is built on jdk version 8
If not present download the jdk from the following link:
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
Note : The project must be run in jdk8 build environment. To change the environment for example in Eclipse IDE, in the project build path configuration dialog, under the libraries tab, change the alternate environment to jdk 8
The project was built using eclipse IDE, but it will run on any IDE.
If there any issues running the project on other IDE, you can download the eclipse IDE from the below link
https://www.eclipse.org/downloads/download.php?file=/oomph/epp/oxygen/R2/eclipse-inst-win64.exe
The Database was created using MySQL Workbench 8.0
✓ Unzip Minions.zip folder from the main folder.
✓ Import the project to the IDE.
✓ To setup Database run the dumpfilesql.sql from the folder in MySQL Environment.
Note: The dumpfilesql.sql contains insert queries which populates the database with required data. You don’t have to import any data.
✓ Change the database properties file in the path /Minions/src/Database.properties accordingly to match
your machine setup for the project as follows:
❖ DB_HOST = jdbc:mysql://localhost:3306
Give the appropriate address of the Database in place of "localhost:3306"
❖ DB_NAME = minions
The database schema name being used will remain the same according to the script provided.
❖ DB_USER = wbadmin
Username of the database needs to be given here in place of "wbadmin"
❖ DB_PWD =aongpg1
Password of the database being used must be given here in place of "aongpg1"
Once the above setup is done:
*Compile and build the project. If there are compilation errors, place /Minions/Reference/mysql-connector-java-8.0.11.jar file in your local \Java\jre1.8.0_161\lib\ext folder.
*Run FirstScreen.java to start the application.
*Follow the README_APPLICATION GUIDE for the application related information
