Location API
===========================================

Returns people who are listed as either living in London, or whose current coordinates are within 50 miles (ca. 80 km) of London

What I used in this project
--------------------------
* Spring Boot
* Spring Framework
* @RequestMapping
* @Configuration
  * Classes to help development to be clear (AppConfig, SwaggerConfig)
* Return the correct HTTP Status code
  * find on CustomizedExceptionHandler java or other method that return ResponseEntity
* Exception Handler
  * exception package
* Generic Exception Handler
  * find Handler on exception package
* Lombok
  * turn dev life easier :)
  * lombok.config at root folder
* Util class
  * To be used for any validation on application
* Content Negotiation - Support for Response Content Type as JSON and XML
  * find the dependency on pom.xml - jackson-dataformat-xml 
* HERE REST APIs
  * Api to help work with Localization and more
  * https://developer.here.com/develop/rest-apis
* JUnit Tests
* JaCoCo
  * Can be found at "target/site/jacoco/index.html"
  * skipping Lombok methods and SpringBootApplication (LocationApplication.java)
* HTTP Requests
  * find requests under "test/httpRequests"
* Swagger
  * http://localhost:8080/swagger-ui.html#/
* Dockerfile
  * Run manually or can use an Intellij plugin
    * Build the image
    * Run container
  
Harvesine Formula
--------------------------
The calculation of the distance between 2 points on the sphere is based on the so called Haversine formula.
This is shown in a number of sites on the internet with math details in the Wikipedia page: https://en.wikipedia.org/wiki/Haversine_formula
I made use of it, without going deeper on math details.
In addition, many sites also show the code for the distance of two points in a number of languages such as C, C++, Java, and so on.
The implementation I took was from https://rosettacode.org/wiki/Haversine_formula.

Findings
--------------------------
<p>Analyzing the data from the given API (https://bpdts-test-app.herokuapp.com/city/{city}/users) I could see that they are inconsistent as the latitude and longitude used to list the users by city are not from London but even this way this API returns people from London what I believe is based on the "city" param that can be found when calling /users/{id} API.</p>
<p>I used the approach to return the users from this API when the call is to "/city/{city}/users", so then the API created goes to the BPDTS API and hits the "/city/{city}/users" endpoint bringing the list with six people on it.</p>
<p>Suppose the client calls "/city/{city}/distance/{distance}/users" API, I am using the distance number to calculate the people by latitude and longitude what returns the list with nine people on it, six that came from the API that uses the "city" param plus the users that I found by latitude and longitude using the "haversine formula."</p>
<p>Queries I had about the implementation, consistent data and, longitude and latitude calculation I believe that would be quick/easier to clarify with team communication (face2face).</p> 
<p>Another point that I would like to raise is that the core of the solution is the calculation of the distance between two points passing latitude and longitude on a sphere. The "haversine formula" is the mathematical solution. I need to do some research and dig around on the internet to find the correct way to do.</p>
<p>Thanks for the opportunity and hope to see you soon.</p>
