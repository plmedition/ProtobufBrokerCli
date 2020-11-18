# ProtobufBrokerCli

ProtobufBrokerCli is an application which serves as CLI that receives a JSon file and it is able to send it
to a RabbitMq message broker. This application is also able to get that content back from the message broker. 

##Prerequisites

To build this application you must have installed :

Apache Maven 3.6.3 [https://maven.apache.org/download.cgi]

Java 8 [https://openjdk.java.net/install/]

Docker [https://docs.docker.com/get-docker/]


## Preparation

In order to run this application and all its tests, you must have an instance of rabbitMq running on your machine.

For that purpose, you must execute the following command:

docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management.

Once this is done you can access to the admin console of rabbitMq through http://localhost:15672 with user "guest" 
and pass "guest". There will you see the "protobuf_queue" once the spring boot application is up and running.


## CLI Usage

*Important The usage of this CLI is still under construction due to issues of the picocli framework and spring boot. 
But the rest of the functionality can be tested through all service tests provided.

The content of this CLI to made of two commands:

[ecosystem send test.json]

This command send the content of the test.json file to the message broker serialized as protobuf.

[ecosystem get]

This command is taking the last message stored in the message broker and print is in console.


## Contributing
Contributions are not still opened.


