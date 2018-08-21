#!/bin/bash
#CF_APP_NEW=$CF_APP-$ENV-new;
#echo CF_APP_NEW

#if cf a $CF_APP_NEW; then
#cf delete $CF_APP_NEW -f;
#fi

export JAVA_HOME=$JAVA8_HOME;
mvn -f pom.xml -DskipTests -B package;
cf push subscription-rules-service -f manifest.yml; 

