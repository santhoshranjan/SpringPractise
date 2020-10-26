### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues



##The Below Changes was performed to this Project

1) Implemented Caching  by Caffeine Caching.
    Cache Time out Settings : 60s and maximumSize=500
2) Add Spring bean Validation
3) Added Custom Validation for Employee  Departments by ConstraintValidator
      We can create only Employees with the Departments ["IT", "COE", "ADMIN", "SALES"] , by folowing Constraint validation
4) Added Custom Validation for  Employee Salary by ConstraintValidator
    We can create only Employees with the Salary in Range -8000000 and  100000000
5) Protected End Rest Controller Endpoints by implementing WebSecurityConfigurerAdapter
     username : wsadmin
     password : wsadmin
6)For Controller Save method, Parameters has been changed, @Valid is added for Bean Validation.

7)Added logging by using slf4j packages

8)Added Integration Test cases by using MOCKMVC.

