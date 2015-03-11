# Kafka
Kafka sample code
Code to create a sentences topic

    sudo -u kafka bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partitions 3 --topic sentences
