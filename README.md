Telegram Bot app&Api Web-service 
=============================

Telegram Bot gives user all the main information about most popular european capitals.
It reads the name of the city from the user's message and tells some general info and list of the best places to visit.
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
- Java


QUICK START
-----------

To start up the telegram bot you need - 
On command line, type in the following commands:

       1. using command 'cd' get into the root directory of the downloaded project 
       2. mvnw clean
       3. mvnw install
       4. java -jar tgbot\target\tgbot-0.1-jar-with-dependencies.jar

Telegram Bot name: @CityInfoTestBot
=============================
To start up the api service you need - 
On command line, type in the following commands:       
       
       1. using command 'cd' get into the root directory of the downloaded project 
       2. mvnw clean
       3. mvnw install  
       4. java -jar api\target\api-exec.jar

WHAT's NEXT
-----------

To test the bot log - into telegram app, find bot by it's name and press start-button to join the conversation.
------------
To use api methods - open Google Chrome and type into the address line - http://localhost:8080/api/getall