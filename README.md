# Hidden Museum

An advanced search tool for searching collections at the **Bristol Museum & Art Gallery**.

## Table of Contents

1. [Overview](#overview)
2. [Setup](#setup)
3. [Deployment](#Deployment)
4. [License](#license)
5. [Contributing](#contributing)
6. [Ethics](#ethics)

## Overview

Our goal is to produce a web app that allows for intuitive searching of datasets using the OpenData API.

This project is specifically done at the request of, and with the involvement of, the Bristol&nbsp;Museum & Arts&nbsp;Gallery.

**Hidden Museum** is developed in Java using the Spring Boot framework. The datasets we use are hosted by Bristol City Council, and are accessed with the OpenData API. The frontend is largely handled by Thymeleaf templates. *We are yet to decide on a CSS framework to use, and may not use one, depending on our needs.*

## Setup

### Requirements

- Java version 11
- [Docker](https://www.docker.com/)

### Building

Build the project with Maven (if `./mvnw` fails, install Apache Maven and use `mvn` instead)
```bash
$ ./mvnw clean package
```

### Running with Docker

Put the `.jar` into a Docker Image
```bash
$ docker build --build-arg JAR_FILE=path/to/jar/file -t <username>/<image> .
```

Run the Docker Container
```bash
$ docker run -p 8080:8080 <username>/<image>
```

### Running without Docker (e.g. for development)

Run Spring-Boot using Maven
```bash
$ ./mvnw spring-boot:run
```

Go to [localhost:8080](https://localhost:8080/) and you should see it running.

## Deployment

This project is deployed using AWS EC2 t2.micro instances. Here is a guide to set up
the AWS instance ready for deployment.

Access your instance using:
```bash
$ ssh ec2-user@<instance public ipv4 address> -i <path to AWS pem file> 
```

Next install Java 11 on Amazon linux.

```bash
$ sudo amazon-linux-extras install java-openjdk11
```

Check that you have the correct active version of Java.
```bash
$ java -version

openjdk version "11.0.7" 2020-04-14 LTS
OpenJDK Runtime Environment 18.9 (build 11.0.7+10-LTS)
OpenJDK 64-Bit Server VM 18.9 (build 11.0.7+10-LTS, mixed mode, sharing)
```

If you do not receive this output run:

```bash
$ alternatives --config java
```
Then type in the number associated with Java 11.

Next follow this guide to install the correct version of Maven (3.6.3)
[Maven installation guide](https://blog.ruanbekker.com/blog/2021/07/12/install-java-11-and-maven-on-ubuntu-linux/)

Check your maven version installed successfully.
```bash
$ mvn -version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /opt/maven
Java version: 11.0.11, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "5.4.0-1041-aws", arch: "amd64", family: "unix"
```

Clone this repository:

```bash
$ git clone https://github.com/spe-uob/2021-HiddenMuseum.git
```

[Run](#Running with Docker) the project.

It should now be accessible publically at the URL:

```
<Public IPv4 DNS of your AWS instance>:8080
```
## License

This project uses the MIT License. It may be redistributed under different terms and without source code.

[Read more...](/LICENSE.md)


## Contributing

Contribution guidelines are currently work in progress, check back soon.

We have a simple guide on how to use `git` with this project though: [read more...](/GITGUIDE.md)


## Ethics

No personal data is collected as part of this project.

[Read more...](/docs/ETHICS.md)