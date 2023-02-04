# Account-Management-System
Final Project


## Install Kafka

Link: https://www.conduktor.io/kafka/how-to-install-apache-kafka-on-mac

### Add Path Varible in zsh Mac permanantly

```zsh
echo 'export PATH="$PATH:/Users/xiao/kafka_2.13-3.3.2/bin"' >> ~/.zshenv
source ~/.zshenv

// test
echo $PATH
kafka-topics.sh
```

From the root of Apache Kafka, run the following command to start Zookeeper:
```
~/kafka_2.13-3.3.2/bin/zookeeper-server-start.sh ~/kafka_2.13-3.3.2/config/zookeeper.properties
```
Open another Terminal window and run the following command from the root of Apache Kafka to start Apache Kafka.

```
~/kafka_2.13-3.3.2/bin/kafka-server-start.sh ~/kafka_2.13-3.3.2/config/server.properties
```