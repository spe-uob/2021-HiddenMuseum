# Hidden Museum 

An advanced search tool for searching collections at the **Bristol Museum & Art Gallery**.

[![docker-publish](https://github.com/spe-uob/2021-HiddenMuseum/actions/workflows/docker-publish.yml/badge.svg)](https://github.com/spe-uob/2021-HiddenMuseum/actions/workflows/docker-publish.yml)
[![pages-build-deployment](https://github.com/spe-uob/2021-HiddenMuseum/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/spe-uob/2021-HiddenMuseum/actions/workflows/pages/pages-build-deployment)
![GitHub tag (latest SemVer)](https://img.shields.io/github/v/tag/spe-uob/2021-HiddenMuseum)

![GitHub issues](https://img.shields.io/github/issues/spe-uob/2021-HiddenMuseum)
![GitHub closed issues](https://img.shields.io/github/issues-closed/spe-uob/2021-HiddenMuseum)
![GitHub pull requests](https://img.shields.io/github/issues-pr/spe-uob/2021-HiddenMuseum)
![GitHub closed pull requests](https://img.shields.io/github/issues-pr-closed/spe-uob/2021-HiddenMuseum)


## Table of Contents

1. [Overview](#overview)
2. [Quickstart](#quickstart)
3. [Manual Setup](#manual-setup)
4. [Documentation](#documentation)
5. [License](#license)
6. [Contributing](#contributing)
7. [Design](#design)
8. [Ethics](#ethics)


## Overview

Our goal is to produce a web app that allows for intuitive searching of datasets using the OpenData API.

This project is specifically done at the request of, and with the involvement of, the Bristol&nbsp;Museum & Arts&nbsp;Gallery.

**Hidden Museum** is developed in Java using the Spring Boot framework. The datasets we use are hosted by Bristol City Council, and are accessed with the OpenData API. The frontend is largely handled by Thymeleaf templates. *We are yet to decide on a CSS framework to use (and may not use one) depending on our needs.*


## Quickstart

Follow this section to get up and running quickly.

For a guide on how you would deploy this web app for production, see our [Example Deployment with Nginx on a Linux Server](/example-deployment.md) guide.

### Requirements

- [Docker](https://www.docker.com/)

### Running

Do the following commands to start an instance of the web app:
```bash
$ docker pull ghcr.io/spe-uob/2021-hiddenmuseum
$ docker run -p 8080:8080 ghcr.io/spe-uob/2021-hiddenmuseum . --server.port=8080
```

This pulls the Docker image, and runs it in a Docker container on port 8080. To change ports, change the range given on the `-p` flag, and the port given with the `--server.port` flag.

Access the web app in the following ways:
+ If you are running it locally, go to [localhost:8080](https://localhost:8080), where you should see it running.
+ If you are running on a cloud instance, e.g. on an AWS EC2 instance, type the public IPv4 address of the instance into your browser, and use port 8080 (unless if you changed it).


## Manual Setup

### Requirements

- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- the Java 11 JDK
- [Apache Maven](https://maven.apache.org/)

### Building

Clone the repository and change directories into it
```bash
$ git clone https://github.com/spe-uob/2021-HiddenMuseum.git
$ cd 2021-HiddenMuseum
```

Build the project with Maven (if `./mvnw` fails, use `mvn` instead)
```bash
$ ./mvnw clean package
```

### Running

Run Spring-Boot using Maven
```bash
$ ./mvnw spring-boot:run
```

Alternatively, run the application using Java directly (change the port to whatever you want)
```bash
$ java -jar ./target/hiddenmuseum.jar --server.port=8080
```

Go to [localhost:8080](http://localhost:8080), where you should see it running.


## Documentation

Find the javadocs [here](https://spe-uob.github.io/2021-HiddenMuseum/).


## License

This project uses the MIT License. It may be redistributed under different terms and without source code.

[Read more...](/LICENSE.md)


## Contributing

Contribution guidelines are currently work in progress, check back soon.

However, [we have a simple guide](/GITGUIDE.md) on how to use `git` with this project, [also available in Chinese](/GITGUIDE_zh.md).


## Design

The project is designed in Java using Spring Boot. We use Thymeleaf for templating and use CSS to style the pages. We use a controller for each page to perform any logic and give variables to Thymeleaf to template using the information gleaned from the requests to the OpenData API. The only exception to this is the infographics page. While this does have a controller that sends information to the template page the majority of the logic is handled using JavaScript and Charts.js. A UML Diagram of the search functionality is available [here](https://github.com/spe-uob/2021-HiddenMuseum/blob/main/Hidden%20Museum.png)

## Ethics

No personal data is collected as part of this project.

[Read more...](/docs/ETHICS.md)
