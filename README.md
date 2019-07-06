# Wildlife-Tracker

This is a web application that should help rangers keep track of wildlife.
[View the Deployed App](https://animals-wildlife.herokuapp.com/ )



It is an application where users can:

* Add an Animal
* Add a Ranger
* Add a Park Location
* Add an Animal Sighting
* View all animal sightings.
* View all sightings made by a ranger or in a location.

## Technologies used

* Intellij IDE.
* Gradle (JUnit).
* Postgres - To create a database
* Heroku and maven - To deploy the Application

## Accesing locally
 


* Step 1:

  Visit my GitHub repository; User name vmuchui and clone the following link: https://github.com/vmuchui/Wildlife-Tracker
  You can also download the files into you folder and unzip it to access the files.

* Step 2:
  Navigate into the created folder.
  Type gradle run in order to run the app from your local machine. The port number will be 4567.

* Step 3:

  Install postgres as shown below:  

  ``sudo apt-get update``

  ``sudo apt-get install postgresql postgresql-contrib libpq-dev``    

  Once it is done installing run  

  ``sudo -u postgres createuser --superuser $USER``  
  to create  super user. Enter your desire password when prompted. 
  Navigate to your home directory and enter the following command to create the .psql_history in order to save your history:  

  ``touch .psql_history``    

  Type psql on your terminal to connect to the server:  

  ``psql``  

* Step 4:  

  Restore the database schema (tables and columns) by running the following commands:  

  ``# CREATE DATABASE wildlife_tracker``  

  ``#\c wildlife_tracker``

  ``#CREATE TABLE animals ( id SERIAL PRIMARY KEY,name VARCHAR, age INTEGER,health VARCHAR, endangered BOOLEAN);``

  ``#CREATE TABLE rangers (id SERIAL PRIMARY KEY, name VARCHAR, badge VARCHAR);``

  ``#CREATE TABLE places (id SERIAL PRIMARY KEY, name VARCHAR)``
  
  ``#CREATE TABLE sightings (id SERIAL PRIMARY KEY, animalid INTEGER, rangerid INTEGER, placeid INTEGER)``


## Behaviour Driven Development (BDD)

|Behaviour my program should handle	           |    Input Example	                 |       Output Example         |
|----------------------------------------------|:-----------------------------------:|-----------------------------:|       
|Inputing an Animal                  |    Lion, 12, healthy,true                           |  creates an animal object           |
|Inputing a Ranger                  |    Jane Doe, sg-002                           |  creates a ranger object           |    
|Inputing a Location                  |    Near River Nile                           |  creates a location object           |    
|Key in sighting                     |     Select the animal, ranger, location      |    outputs the ids of the ranger, location and animal.     |


## Authors

* **Victor Muchui** 
* Contact information: Phone Number: 0717304712 Email: kaigongimuchui@gmail.com


### License
MIT License

Copyright (c) 2019 Victor Muchui

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
