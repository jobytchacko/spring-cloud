# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.ms.config-server' is invalid and this project uses 'com.ms.configserver' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/maven-plugin/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Centralized Configuration](https://spring.io/guides/gs/centralized-configuration/)

# Details about Config server
* Keeps all the configuration for different micro services for different profiles
* Dependencies are - Config Server
* To enable the micro service as config server, provide this annotation in the starter class @EnableConfigServer
* We will be keeping the configurations in a git repository
* The repository will have the configurations for different micro services and their profile
* The config server identifies the corresponding config file for a service with its application name/artifcatID. So, it is important to follow the naming convention
* Property file name = limits-service.properties
* Application name is = limits-service
* Property file name for different profiles = limits-service-dev.proprties or limits-service-qa.properties
* These files are kept is git local repository. So, initialise the git in the repo folder and connect the config server app to the git repo
* To link to the git repo : Right click project, select source tab and click on link source and then provide source of the git.
* Provide this git repo folder path in the config server application property file. Example shown below 
   *  spring.cloud.config.server.git.uri=file:///${user.home}/eclipse-spring-wp/spring-config-repo
	


