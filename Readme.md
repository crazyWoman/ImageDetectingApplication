Detect images using GCP
======================

How to run this project
-----------------------

Make sure you have maven and Java installed. Download the project or clone the project

Edit the application.properties file by adding "spring.cloud.gcp.credentials.location=file:" 
and appending the path and file name to where you saved your service account key. 

do a mvn clean compile

Run the application by running Main application.java

you can go to swagger or postman

if you are using swagger then go to http://localhost:8080/swagger-ui/index.html

1. Save endpoint

   http://localhost:8080/images 
   This is a POST method. It accepts a json with imageURL, imageLabel and objectProcessing which is default to true
   I have images uploaded in resources/images directory. In case any one want a sample json to test
   e.g
   {
   "imageURL": "classPath:images/livingroom.png",
   "imageLabel": "living",
   "objectProcessing": true
   }

3. Get all image metadata  endpoint

   http://localhost:8080/images
   This is a GET method.

4. Get all image metadata for specified objects endpoint
    http://localhost:8080/images?objects=dog,cat
    This is a GET method.

5. Get all image metadata for a specific image id
   http://localhost:8080/images/1

 
