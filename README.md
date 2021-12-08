# Hidden Museum

An advanced search tool for searching collections at the **Bristol Museum & Art Gallery**.

## Table of Contents

1. [Overview](#overview)
2. [Setup](#setup)
3. [License](#license)
4. [Ethics](#ethics)


## Overview

The goal of this project is to create an advanced search tool that interacts with the existing API given at (https://help.opendatasoft.com/apis/ods-search-v1/#search-api-v1) in order to browse, filter and search a database of 2 million objects. This will also include images in the search results.

The plan for implementing this is to make a web application using a JavaScript framework and start by building a search tool that can use features of the objects to browse, filter and search them. We will also take into consideration accessibility for web tools. Then to expand on this we will implement something similar for images.


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


## Ethics

No personal data is collected as part of this project.

[Read more...](/docs/ETHICS.md)