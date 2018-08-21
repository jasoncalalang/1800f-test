#!/bin/bash
export JAVA_HOME=$JAVA8_HOME;
mvn -f pom.xml -B test -Djavax.net.ssl.trustStore=mongo-trust.jks -Djavax.net.ssl.trustStorePassword=keypass -DskipTests -B package

cp deploy/pipeline/pipeline-java-build-uat.sh ${ARCHIVE_DIR}
cp deploy/pipeline/pipeline-deploy-kube-uat.sh ${ARCHIVE_DIR}
cp Dockerfile ${ARCHIVE_DIR}
cp deployment.yml ${ARCHIVE_DIR}