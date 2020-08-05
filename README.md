# HVAC Partners Account API

Account Management API

## Java App
In order to run application locally , we need to follow these steps

## STS(Spring Tool Site)
> STS installation (Version 3)
 
## JAVA 
> JAVA Developement Kit - 1.8 (Installation)

## Spring Boot
> Spring boot 2.2.6.REALESE

## JBOSS
> JBOSS Installation : WildFly ( 18.0.0.Final) - http://wildfly.org/downloads/ 
  > After installing JBOSS , add server to STS
  
 ## MAVEN
> Import project as Maven project
> run maven commands
  > maven clean
  > maven install
  
### Activate Maven Build Profile
```
> mvn clean install -P {profile}
	... e.g - mvn clean install -P dev
```
  
## RUN PROJECT  
> Project -> Run as -> spring boot app  

### Account API Postman Collection 
Added Account API postman collection JSON file in code base.

### Postman collection Scripts running instructions
```
> Download and install Node.js
> npm install -g newman ---- install newman 
> newman run {postman collection name} -e {POstman environment name} --- EX : newman run myCollection.json -e DevEnvironment.json
`````

### API endpoints - Local Environment
> Username lookup - http://localhost:8080/account/api/user/{username}
> Company Lookup  :
                   1) By companyID -  http://localhost:8080/account/api//company?companyId=1
				   2) By Zipcode -  http://localhost:8080/account/api//company?zipcode=560029