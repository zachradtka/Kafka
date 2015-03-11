#!/bin/bash

# Set the base dir of the project
basedir=$(dirname $0)/..

# Make sure KAFKA_HOME is defined
if [ -z "${KAFKA_HOME}" ]; then
   echo "KAFKA_HOME is undefined"
   exit
fi

# Get the correct version of java
if [ -z "$JAVA_HOME" ]; then
    JAVA="java"
else
    JAVA="${JAVA_HOME}/bin/java"
fi

# Add the jars from kafka to the classpath
for file in ${KAFKA_HOME}/libs/*.jar;
do
    CLASSPATH=${CLASSPATH}:${file}
done

# Get the jar from target
for file in ${basedir}/target/*.jar;
do
    CLASSPATH=${CLASSPATH}:${file}
done

# Uncomment the following line to see the command being run
#echo "${JAVA} -cp ${CLASSPATH} com.zachradtka.App"

${JAVA} -cp ${CLASSPATH} com.zachradtka.App $1