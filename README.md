# remind-me-rest-sandbox
The project is based on remind-me-rest-sandbox.I have updated the spring version and test. 
The aim of the project are following
- implement user crud 
- implement external api call, on this case github 

    
## Tech
- java 1.8
- maven 3.6
- h2 db

## Build and run 
Build
```
$ mvn clean install

```

Run locally
```
$ mvn spring-boot:run
```
Run from the command line:
```
$ java -jar target/app.jar
```

 
## How to test
 As there is no critical implementation of logic, I skip unit test and all the tests are integration test here.
 ```
$ mvn test
```
 
## Hit the api
swagger is the very good choice for api documentation. For the sake of simplicity of this project, I skip swagger.
 
Create user
```
POST http://localhost:12000/users
Content-Type: application/json

{
    "id": "60953a28-df70-4ec8-9f6d-8effd8c50cca",

    "firstName" : "Jonathon",

    "surname" : "John",

    "position" : "Software Engineer",

    "gitHubProfileUrl" : "maruf571"
}
```


find list
```
GET http://localhost:12000/users
```

find by id
```
GET http://localhost:12000/users/60953a28-df70-4ec8-9f6d-8effd8c50cca
```
delete by id
```
DELETE http://localhost:12000/users/60953a28-df70-4ec8-9f6d-8effd8c50cca
```

external api call
``````
GET http://localhost:12000/users/60953a28-df70-4ec8-9f6d-8effd8c50cca/repositories
```




