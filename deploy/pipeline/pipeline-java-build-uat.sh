#!/bin/bash
export JAVA_HOME=$JAVA8_HOME;
mvn -f pom.xml -B -Djavax.net.ssl.trustStore=mongo-trust.jks -Djavax.net.ssl.trustStorePassword=keypass -DskipTests -B package

cp deploy/pipeline/pipeline-docker-build-uat.sh ${ARCHIVE_DIR}
cp deploy/pipeline/pipeline-validate-uat.sh ${ARCHIVE_DIR}
cp deploy/pipeline/pipeline-deploy-kube-uat.sh ${ARCHIVE_DIR}

cp Dockerfile ${ARCHIVE_DIR}
cp deployment.yml ${ARCHIVE_DIR}
cp cacerts ${ARCHIVE_DIR}
cp mongo-trust.jks ${ARCHIVE_DIR}
cp mongo-trust-dev.jks ${ARCHIVE_DIR}
cp mongo-trust-prod.jks ${ARCHIVE_DIR}