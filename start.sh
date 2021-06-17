#!/bin/bash
git pull
mvn clean package
java -jar ./target/sponsoredAds-0.0.1-SNAPSHOT.jar --server.port=8080
