# Account-Management-System
Final Project: Achieve a AccountManagementSystem and MasterCardApp, we can register a new account, then the system will generate a new id and a new unique token at the same time, when MasterCardApp send a token to AccountManagementSystem, it will validate the token in the DB and send the status back to the MasterCardApp.

## Connect MySQL DB

- Add MySQL dependency first.
- Configure the application.properties like the following.

```properties
spring.datasource.url=jdbc:mysql://{Hostname}:{port}/{database}
spring.datasource.username={username}
spring.datasource.password={password}
```

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


## Project details

### MasterCardApp
- producer: generate token and send it to AccountManagementSystem
- consumer: get validated result

### AccountManagementSystem
- producer: receive token, store in Post_Status DB
- consumer: get status from Post_Status DB, send to MasterCardApp



## Error Notes

1. Error while fetching metadata with correlation id 1 : {topic=LEADER_NOT_AVAILABLE}
Solution: https://blog.csdn.net/csdn_xpw/article/details/105179901

2. 