Note : If you need more info, contact jobytchacko@gmail.com .
# Spring cloud Implementation Details
### Services in this repository and used ports
* Spring Config Server | 8888
* Eureka Naming Server | 8671
* Zuul API Gateway | 8765
* Micro Service 1 - Currency Exchange Service | 9003 | 9004
* Micro Service 2 - Currency Conversion Service | 9006

### Following techniques are implmented
* Centralized Microservice Configuration with Spring Cloud Config Server
* Client Side load balancing using Ribbon
* Dynamic Scaling using Eureka Naming Server
* API Gateway using Netflix Zuul
* Distributed tracing using Cloud Sleuth and Zipkin - Implemnetation steps will be available
* Fault Tolerance for microservices with Zipkin
* Spring Cloud Bus to exchange messages about Configuration updates
* Communication with other Microservices using Feign REST Client

### Versions used
* Java 8
* Spring Boot 2.0|2.1|2.2
* Spring Cloud Finchley.M8|Greenwich.SR5|Hoxton.SR3

## Details about each services

### Spring Config Server 
- Keeps all the configuration for different micro services for different profiles
- Dependencies are - Config Server
- To enable the micro service as config server, provide this annotation in the starter class @EnableConfigServer
- We will be keeping the configurations in a git repository
- The repository will have the configurations for different micro services and their profile
- The config server identifies the corresponding config file for a service with its application name/artifcatID. So, it is important to follow the naming convention
- Property file name = limits-service.properties
- Application name is = limits-service
- Property file name for different profiles = limits-service-dev.proprties or limits-service-qa.properties
- These files are kept is git local repository. So, initialise the git in the repo folder and connect the config server app to the git repo
- To link to the git repo : Right click project, select source tab and click on link source and then provide source of the git.
- Provide this git repo folder path in the config server application property file. Example shown below 
   -  spring.cloud.config.server.git.uri=file://${user.home}/spring-config-repo
   
### Connecting a Micro service to config server
- Change the application property name to bootstrap.properties
- Provode the name and config server url. Example follows 
   - spring.application.name = limits-service
   - spring.cloud.config.uri = http://localhost:8888
   - spring.profiles.active = dev
   - Our config server was running on 8888 port by default.  
   - To use this configuration as part of the application requirement, we can read normally. One example follows 
   - Create a class and annotate with ConfigurationProperties with name of the application inside the annotation and provide the properties to be fetched inside the class
    - If some properties are not found in one profile of the application properties, then, it will pick from the default properties
     
### Currency Exchange Service 
- It is a micro service that provides the exchange rate for the conversion of different currency
- There is one API exposure and that can be consumed by other services

### Currency Conversion Service 
- This micro service accepts currency conversion details and the quantity to be converted
- This service wanted to unitise the previously listed exchange service to get the exchange rate for different currencies
- It can connect to micro service using rest template. But, it can use the feign from spring cloud which simplifies the calls between micro services. It needs the sever details and name of the micro service to be consumed. Feign simplifies those calls
- To use the Feign client, you need to enable it along with springboot start in the main class using annotaion
- Suppose if you wanted to distribute the calls to multiple instances of the exchange service, we need to utilise the ribbon. Just Enable Ribbon Client and provide the server details in the application.properties
- So, the ribbon will distribute the calls to the multiple instances.

### Eurekan Naming Server
- As you have seen earlier, the calls are made using http request only. you have to specify the URL in the application or service to make another call. This will not help if you want to add multiple instances once application is deployed. Here, you need to add the URI each time you deploy anther instance of the microservice
- We user Eureka Naming Server. This will register all the instances when it is deployed if EurekaDiscovery is enabled in the microservices
- Configuration for Naming Server 
- Dependencies - Eureka Server 
- Add @EurekaServer annotation in the main class. This will help to act as Eureka naming server
- Configuration for Microservices 
- Add the dependency Eureka Discovery Client
- Add the Annotation @EnableDiscoveryClient in the main class
- Add the URL of the Eureka server in the configuration file as follows. This format may change as per the versions eureka.client.serviceUrl.defaultZone=http://localhost:8671/eureka
- This will make the micro service to register with Eureka and could be discovered through the application name of the service

### API Gateway - Netflix Zuul
- This will help us to implement the common functionalities for different Micro services like Authorisation, Logging, Service Aggregation etc.
- All the requests will be forwarded to the gateway and then it will go to the corresponding service or application
- We can implement a filter and do the things needed to do before sending request to the corresponding service
- We can use the Zuul API gateway from Netflix to implement the API gateway
- Add the dependencies Zuul and Eureka Discovery Client
- Add the annotation @EnableZuulGatwayServer main class
- If you wanted to implement any filer extend the class ZuulFilter 
Changes to be made in Microservice to redirect through API gateway
- Localhost:XXXX/{AppName}/{URI}
- App Name should be the name of the service
- Use the Port where Zuul is deployed

### Distributed Tracing with Zipkin or Centralized logging
- There will have a lot micro services running. It will make difficult to trace the log from multiple services running on different locations
- So, we need an application that can trace all these logs and keep it one place trace back the issues.
- The first step we need to do for that is implementing sleuth to provide identity for each request log

 Step1. Implementing Sleuth 
- Sleuth is a cloud project which provide an ID for each request log. The Id will same for the logs generated in one request.
- By adding the corresponding spring cloud deopendency will make the services to provide a unique Id for all the requests log
- Do a bean configuration in the main class to trace the logs and append unique ID

Step 2. Installing and running RabbitMQ
- Rabbit MQ is used to trace the logs by Zipkin
- So, install RabbitMQ and start the rabbit MQ
- https://www.rabbitmq.com/rabbitmqctl.8.html
- To Start RabbitMq /usr/local/sbin/rabbitmq-server
- To Stop /usr/local/sbin/rabbitmqctl shutdown
- Also, add the rabbit mq spring cloud dependency in they POM.xml of the micro service

Step 3. Installing and running Zipkin 
- Zipking is a seperate Java application. So, download the JAR file and run it with Rabbit MQ configuration
- RABBIT amp://localhost Java -jar Zipkin.jar
- This will run the zipkin in the default port

Step 4. Adding dependency
- Add the spring cloud dependency in the POM file. This will make the Zipkin to trace the logs
- The Microservice put the logs in the rabbit mq server and the Zipking collect it from the Queue 


### Spring Cloud bus implementation

- Suppose a lot of Microservices are running in your spring cloud and you wanted to make a small change in configuration service. In this situation, it is difficult to refresh or restart all the services
- Spring bus implemention helps to make the configuration changes in all running micro services
- Just add the amq bus in the dependency and add the actuator dependency 
- Make changes in the configuration server for the property file
- Do a post call to http://localhost:8095/actuator/bus-refresh Url may vary according to different versions
- The changes will refelect in all instances of the Microservice

### Hysterix  Fault Tolerance

  - All the micro services will consuming another micro service We need a solution to pass a default value or details incase something wrong happened to a specific method.
  - Spring cloud hysteria framework helps to do it.
  - Add the dependency for the hysteria
  - Enable the hystrix with annotation 
  - Provide the fallbackMethod using @Hysterix mapping incase of the failure of a method
