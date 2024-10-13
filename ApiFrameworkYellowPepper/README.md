
---

# ApiFrameworkYellowPepper

The **`ApiFrameworkYellowPepper`** project is a comprehensive API testing framework that integrates various technologies to ensure robust functionality and performance validation of REST APIs. Built on a Gradle foundation, the project leverages **Cucumber** for Behavior-Driven Development (BDD), allowing teams to define clear and understandable test scenarios in natural language format. The actual testing is accomplished using **Rest Assured**, which provides a powerful and straightforward way to send HTTP requests and validate API responses against expected outcomes, thus enhancing the reliability of the API under test. 



## Table of Contents
- [Prerequisites](#prerequisites)
- [Setting Up Swagger Petstore with Docker](#setting-up-swagger-petstore-with-docker)
- [Running Test Cases for Pet API with Gradle](#running-test-cases-for-pet-api-with-gradle)
- [Executing Test Cases in Docker](#Executing-Test-Cases-in-Docker)
- [Conclusion](#conclusion)

## Prerequisites
Before running the tests, ensure you have the following installed:
- Java 17 or higher
- Gradle
- Docker (for setting up the Swagger Petstore API)

## Setting Up Swagger Petstore with Docker
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
To run the test cases defined in the ApiFrameworkYellowPepper project, follow these execution steps.

## Execution Steps
1. **Navigate to the Project Directory**: Open a terminal and navigate to the ApiFrameworkYellowPepper directory:
   ```bash
   cd path/to/ApiFrameworkYellowPepper
   ```

2. **Run the Test Cases Using Gradle**: Execute the following command to run the test cases defined in the project:
   ```bash
   ./gradlew clean build
   ```
   or
   ```bash
   ./gradlew test
   ```
## Executing Test Cases in Docker
1. **To run the test cases against the Dockerized Petstore API**: ensure your Docker container is running. Then, from the ApiFrameworkYellowPepper project directory, execute the following command to build and run the Docker container:
    ```bash
    docker build -t api-framework .
    docker run --rm -e PETSTORE_API_URL=http://host.docker.internal:8080/api/v3 api-framework
    ```

## Conclusion
The **ApiFrameworkYellowPepper** project provides a robust environment for automated API testing. By following the steps outlined above, you can easily set up the framework and execute test cases to ensure the quality and reliability of your APIs.

---