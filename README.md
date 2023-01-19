![image illustration](https://user-images.githubusercontent.com/57298219/213449413-ea2f7614-932b-4c84-97ae-064ff5ad1192.jpg)
---------------------------------------------------------------------------------------
Example of the development of an enterprise management application
with a microservice architecture :
department management,
employee management,
mission management.
---------------------------------------------------------------------------------------
Spring Cloud,
Spring Boot 3.1 based on Jakarta EE 9, 
Java 17 and Maven 3.8
----------------------------------------------------------------------------------------
to test project you must clone the project using git clone.
then under each service you must run the following commands: mvn clean install.
start each service in the order below with the command mvn spring-boot:run

1: discovery-service (to access the eureka registration interface: http://localhost:8761)
2 : gateway (starts under http://localhost:8181 )
3 : department-service (to access swagger-ui http://localhost:8182/swagger-ui.html )
4 : employee-service (access to swagger-ui http://localhost:8183/swagger-ui.html )
5 : mission-service (access to swagger-ui http://localhost:8184/swagger-ui.html )

example: to display the list of departments by sending a request to gateway 
http://localhost:8181/EMPLOYEE-SERVICE/api/v1/employee/list 
-----------------------------------------------------------------------------------------


