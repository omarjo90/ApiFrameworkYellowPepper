# ApiFrameworkYellowPepper & PetstorePerformanceTesting

ApiFrameworkYellowPepper is a Java-based testing framework designed to facilitate automated API testing. It utilizes tools like Gradle for build management and Rest Assured for making HTTP requests and validating responses. This framework is highly customizable and scalable, making it ideal for comprehensive API testing across various services.

PetstorePerformanceTesting focuses on performance evaluation for the Swagger Petstore API. This project implements various performance testing scenarios to assess the API's responsiveness, stability, and scalability under different loads. By leveraging Gradle, it ensures easy integration and execution of test cases.

K6 is an open-source load testing tool that provides an easy way to create and execute performance tests using JavaScript. It allows developers to simulate a large number of users and generate performance metrics, helping identify bottlenecks in API services. K6 integrates seamlessly with CI/CD pipelines, making it an excellent choice for continuous performance testing.

This document includes instructions for running the Swagger Petstore API, executing test cases with Gradle, and performing K6 performance tests.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Setting Up Swagger Petstore with Docker](#setting-up-swagger-petstore-with-docker)
- [Running Test Cases for Pet API with Gradle](#running-test-cases-for-pet-api-with-gradle)
  - [ApiFrameworkYellowPepper](#apiframeworkyellowpepper)
  - [PetstorePerformanceTesting](#petstoreperformancetesting)
    
## Prerequisites

Before you begin, ensure you have the following installed:

- **Java JDK** (version 11 or higher)
- **Gradle** (version 7.0 or higher)
- **Docker** (installed and running)
- **K6** (installed on your machine)

## Setting Up Swagger Petstore with Docker

To run the Swagger Petstore API locally using Docker, follow these steps:

1. **Clone the Swagger Petstore Repository:**

   Open a terminal and run:

   ```bash
   git clone https://github.com/swagger-api/swagger-petstore.git
   cd swagger-petstore
   mvn 

2. **Build and Run the Docker Image:**

   Use the following command to build and run the Docker image:

   ```bash
   docker build -t swaggerapi/petstore3:unstable .
   docker pull swaggerapi/petstore3:unstable
   docker run  --name swaggerapi-petstore3 -d -p 8080:8080 swaggerapi/petstore3:unstable

  The Petstore API will be accessible at http://localhost:8080
  
## Running Test Cases for Pet API with Gradle
### ApiFrameworkYellowPepper
1. Navigate to the Project Directory:
   Open a terminal and navigate to the ApiFrameworkYellowPepper directory:
   ```bash
   cd path/to/ApiFrameworkYellowPepper
2. Run the Test Cases Using Gradle:
   Execute the following command to run the test cases defined in the project:
   ```bash
   ./gradlew clean build
   
## PetstorePerformanceTesting
1. Navigate to the Performance Testing Directory:

   Open a terminal and navigate to the PetstorePerformanceTesting/src directory:
   ```bash
    cd path/to/PetstorePerformanceTesting/src
2. Run the K6 Test:

   To execute the K6 performance test for the Petstore API, run the following command:
   ```bash
    k6 run petFindByStatusTest.js