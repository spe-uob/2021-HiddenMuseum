# Hidden Museum

An advanced search tool for searching collections at the **Bristol Museum & Art Gallery**.

## Table of Contents

1. [Overview](#overview)
2. [Documentation](#documentation)
3. [Setup](#setup)
4. [Deployment](#Deployment)
5. [License](#license)
6. [Contributing](#contributing)
7. [Ethics](#ethics)

## Overview

Our goal is to produce a web app that allows for intuitive searching of datasets using the OpenData API.

This project is specifically done at the request of, and with the involvement of, the Bristol&nbsp;Museum & Arts&nbsp;Gallery.

**Hidden Museum** is developed in Java using the Spring Boot framework. The datasets we use are hosted by Bristol City Council, and are accessed with the OpenData API. The frontend is largely handled by Thymeleaf templates. *We are yet to decide on a CSS framework to use (and may not use one) depending on our needs.*

## Documentation

Find the javadocs [here](https://automatic-couscous-af1e8f89.pages.github.io/).

## Setup

### Requirements

- Java version 11
- [Docker](https://www.docker.com/)

### Building

Build the project with Maven (if `./mvnw` fails, install Apache Maven and use `mvn` instead)
```bash
$ git clone https://github.com/spe-uob/2021-HiddenMuseum.git
...
$ cd 2021-HiddenMuseum
$ ./mvnw clean package
```

### Running with Docker

Put the `.jar` into a Docker Image
```bash
$ docker build --build-arg JAR_FILE=path/to/jar/file -t <image-name> .
```

Run the Docker Container
```bash
$ docker run -p 8080:8080 <image-name>
```

### Running without Docker (e.g. for development)

Run Spring-Boot using Maven
```bash
$ ./mvnw spring-boot:run
```

Go to [localhost:8080](https://localhost:8080/) and you should see it running.

## Deployment

We deploy on AWS EC2 t2.micro instances with the following process.

### Setup

Access the AWS instance
```bash
$ ssh ec2-user@<instance-public-ipv4-address> -i path/to/AWS/pem/file
```

Install Java 11 and check it installed successfully
```bash
$ sudo amazon-linux-extras install java-openjdk11
...
$ java -version
openjdk version "11.0.7" 2020-04-14 LTS
OpenJDK Runtime Environment 18.9 (build 11.0.7+10-LTS)
OpenJDK 64-Bit Server VM 18.9 (build 11.0.7+10-LTS, mixed mode, sharing)
```

<!--- commented out because it is unclear what exactly this is doing
If you do not receive this output run:
```bash
$ alternatives --config java
```

Then type in the number associated with Java 11.
-->

[Install Maven](https://blog.ruanbekker.com/blog/2021/07/12/install-java-11-and-maven-on-ubuntu-linux/) and check it installed successfully
```bash
... install maven
$ mvn -version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /opt/maven
Java version: 11.0.11, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "5.4.0-1041-aws", arch: "amd64", family: "unix"
```

[Install Docker](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/docker-basics.html) and check it installed successfully
```bash
... install docker
$ docker info
... output that isn't an error
```

### Running

[Build and run](#building) the project
```bash
$ git clone https://github.com/spe-uob/2021-HiddenMuseum.git
...
$ cd 2021-HiddenMuseum
$ ./mvnw clean package
...
$ docker build --build-arg JAR_FILE=path/to/jar/file -t <image-name> .
...
$ docker run -p 8080:8080 <image-name>
```

Access your AWS instance's public IP address in a browser and it should be running.

## License

This project uses the MIT License. It may be redistributed under different terms and without source code.

[Read more...](/LICENSE.md)


## Contributing

Contribution guidelines are currently work in progress, check back soon.

We have a simple guide on how to use `git` with this project though: [read more...](/GITGUIDE.md)


## Ethics

No personal data is collected as part of this project.

[Read more...](/docs/ETHICS.md)