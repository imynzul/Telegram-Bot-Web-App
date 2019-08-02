Telegram Bot app&Api Web-service 
=============================

Telegram Bot gives user all the main information about most popular european capitals.
It reads the name of the city from the user's message and tells some general info about the city.
Works with database where info is being kept.
With api web-service you are able to add, edit and remove cities and info from the database.


INSTALLATION
------------

Download zip archive with the files. Please make sure the release file is unpacked under a Web-accessible
directory. You shall see the following files and directories:

      .mvn               maven wrapper
      api                api web-service
      datasource         database 
      tgbot              telegram-bot app
      .gitignore         list-ignore source
      mnvw               maven wrapper doc
      mvnw.cmd           maven wrapper executable file
      pom.xml            config file
      README.md          this file


REQUIREMENTS
------------

The minimum requirements - next should be installed on your PC:
- Google Chrome
- MySQL
- JDK 1.8+


QUICK START
-----------

To start up the telegram bot you need - 
On command line, you need to type in the following commands from the project root:

       1. mvnw clean
       2. mvnw install
       3. java -jar tgbot\target\tgbot-0.1-jar-with-dependencies.jar

Telegram Bot name: @CityInfoTestBot
=============================
Telegram Bot token: 639836817:AAFGrwA9j0nT8B6kv0sUPAOzErQoUmF32hI
=============================


To start up the API service you need:

- If you have already started up the telegram bot below, all you need.
On command line, you need to type in the following commands from the project root: 

       1. java -jar api\target\api-exec.jar

- If you haven't started up the telegram bot, you need to do next. 
On command line, you need to type in the following commands from the project root:       
       
       1. mvnw clean
       2. mvnw install  
       3. java -jar api\target\api-exec.jar

WHAT's NEXT
-----------

To test the bot - log into telegram app, find bot by it's name and press start-button to join the conversation
------------
Api Methods:
------------
/api/getall - return list of all the cities and their info
------------
/api/save - save new city and info to the DB
------------
/api/update - update info about the existing city in the database
------------
/api/delete - delete city and it's info from the database
------------
