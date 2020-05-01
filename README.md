# Latest news feed and Analysis
A Spring boot application to feed new and analyse it.

## Table of Content
- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Technologies used](#technologies-used)
- [Run application locally](#run-application-locally)
- [How to test](#how-to-test)

## Introduction
A Spring boot test application which carries below operations.
   1. Fetch latest news every 10 minutes and feed them into local csv file.
   2. Analyse and get total number of news for each Category.

## Prerequisites
- [JDK 1.8](https://www.azul.com/downloads/zulu-community/?architecture=x86-64-bit&package=jdk)
- [Maven 3](https://maven.apache.org/download.cgi)
   
## Technologies used
   - Spring boot (Web, RestTemplate) 
   - JUnit5
   - Apache Commons CSV
   
## Run application locally
There are several ways to run this spring boot application.
   1. [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)
        ```shell
        mvn spring-boot:run
        ```
   2. Using IDEs,
      Clone application from github and import it into any Java IDE
   3. Execute `main` method in `com.horizonx.currentnews.CurrentNewsApplication.java` directly from command prompt using `Java` command.
   
## How to test
- There are two Rest service.
   1. `/latest-news/feed` - To Feed latest news in to csv file
   2. `/latest-news/count-categorywise` - Analyse and get total number of news for each news category
   Note: Find [Postman Collection](./HorizonX Test Exercise.postman_collection.json) here.
- Test Scheduler
  1. Build application with Jar packaging and run it standalone locally.
      
      `java -jar jarfilename. jar`
  2. Build application with War packaging and deploy it to Web server like tomcat
  3. Deploy it to any cloud solution (AWS, Google, PCF)
  Note: You can find log of scheduler when it runs as it is configured in application.
   
   `2020-05-02 01:20:00.358  INFO 3276 --- [   scheduling-1] c.h.currentnews.service.NewsServiceImpl  : Feeding latest news to csv file`
`2020-05-02 01:20:00.360  INFO 3276 --- [   scheduling-1] c.h.currentnews.service.NewsServiceImpl  : Feeding latest news to csv file is over.`
