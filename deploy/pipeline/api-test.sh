#!/bin/bash
export JAVA_HOME=$JAVA8_HOME;
mvn -f pom.xml -B test;
