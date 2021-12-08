# Hidden Museum

An advanced search tool for searching collections at the **Bristol Museum & Art Gallery**.

## Table of Contents

1. [Overview](#overview)
2. [Setup](#setup)
3. [License](#license)
4. [Contributing](#contributing)
5. [Ethics](#ethics)

## Overview

Our goal is to produce a web app that allows for intuitive searching of datasets using the OpenData API.

This project is specifically done at the request of, and with the involvement of, the Bristol&nbsp;Museum & Arts&nbsp;Gallery.

**Hidden Museum** is developed in Java using the Spring Boot framework. The datasets we use are hosted by Bristol City Council, and are accessed with the OpenData API. The frontend is largely handled by Thymeleaf templates. *We are yet to decide on a CSS framework to use, and may not use one depending on our needs.*

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


## License

This project uses the MIT License. It may be redistributed under different terms and without source code.

[Read more...](/LICENSE.md)


## Contributing

Contribution guidelines are currently work in progress, check back soon.

We have a simple guide on how to use `git` with this project though: [read more...](/GITGUIDE.md)


## Ethics

No personal data is collected as part of this project.

[Read more...](/docs/ETHICS.md)