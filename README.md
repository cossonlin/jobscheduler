# Project Title

Job Scheduler

## Getting Started

### Prerequisites

* [JDK 8](http://www.oracle.com/technetwork/pt/java/javase/overview/index.html)

Ensure JAVA_HOME environment variable is set and points to your JDK installation

* [Maven](https://maven.apache.org/) - Dependency Management
Download from https://maven.apache.org/

```
unzip apache-maven-3.5.4-bin.zip
```

or
```
tar xzvf apache-maven-3.5.4-bin.tar.gz
```
Add the bin directory of the created directory apache-maven-3.5.4 to the PATH environment variable
Confirm with mvn -v in a new shell.

### Installing & Running the tests

Run below command to install the dependencies, compile and run the tests
```
$ mvn clean install
```

### And coding style tests

Test cases cover all the service methods defined in main class

```
@Test
public void testXXXX() {

}
```

## Deployment

JAR package will be created under target/JobScheduler-{versionNo}.jar after packaging
then you can run below command to bring up the application
```
java -jar target/JobScheduler-{versionNo}.jar
```


## Built With

* [Maven](https://maven.apache.org/)

## Versioning

We use GIT for versioning.

## Usage

The application is running on 8081 port which is setting in application.yml

Load all schedule tasks from browser:  
http://localhost:8081/taskschedule/loadAllSchedules

CURL command for NONE GET method
```
curl -X POST 'http://localhost:8081/taskschedule/addSchedule1?cronExpression=%2A/10%20%2A%20%2A%20%2A%20%2A%20%2A'
```
```
curl -X DELETE 'http://localhost:8081/taskschedule/deleteSchedule/1'
```

## Authors

* **Lin Lin**