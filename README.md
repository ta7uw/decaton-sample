# decaton-sample  [![Build Status](https://cloud.drone.io/api/badges/ta7uw/decaton-sample/status.svg)](https://cloud.drone.io/ta7uw/decaton-sample)

This is sample spring application to understand how decaton works.
A client application produces message to kafka and a processor application consumes messages from kafka
by using decaton module. These application is not web application.

## Usage

1. Start a kafka  
    `$ docker-compose up -d`
    
2. Start a client application  
    `$ ./gradlew client:bootRun`

3. Start a processor application  
    `$ ./gradlew processor:bootRun`
