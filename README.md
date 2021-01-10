<h1 align="center">Gmoney Store Server</h1>

![CI](https://github.com/roharon/gmoney-store-server/workflows/Gradle/badge.svg)  ![Docker compose CI & CD](https://github.com/roharon/gmoney-store-server/workflows/Docker%20compose%20CI%20&%20CD/badge.svg?branch=master)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)
![Github contributors](https://img.shields.io/github/contributors/roharon/gmoney-store-server.svg) 

[![Run on Ainize](https://ainize.ai/images/run_on_ainize_button.svg)](https://ainize.web.app/redirect?git_repo=https://github.com/roharon/gmoney-store-web) - just client (api server not work on this demo)

<br/>

<p align="center" >
<img src="https://github.com/roharon/gmoney-store-web/blob/master/public/demo.gif" alt="image" height="480" />
</p>

<br/>

## About The Project

Store finding service that support local-money in Gyeonggi-do, Republic of Korea.
Need GPS permissions allow to find store nearby.


This repository is the API server using REST architecture

* All of the Gmoney Store Repository

  - [Gmoney-Store-Server](https://github.com/roharon/gmoney-store-server) (this repo.)
  - [Gmoney-Store-Web](https://github.com/roharon/gmoney-store-web)


### Tech Stack

* Java 8
* Spring Boot 2
  * Spring data JPA
  * Gradle
* PostgreSQL 12
* Nginx
* Docker, Docker-compose



### API document

[Click to view Docs on Swagger Hub](https://app.swaggerhub.com/apis-docs/roharon/Gmoney-store-server/1.0#)



## Getting Started - with Docker

### Prerequisites

* Docker
* Docker-compose 3.4

### Installation & Run

1. Run docker-compose file

	```bash
	$ sudo docker-compose -f docker-compose-dev.yml up --build
	```
2. Execute alembic to DB migration

   ```bash
   $ alembic upgrade head
   ```
   
3. Get [local-money store current situation](https://www.data.go.kr/data/15058640/openapi.do) data-set and import to postgres.




## Getting Started - without Docker (NOT RECOMMEND)

### Prerequisites

* OpenJDK
* PostgreSQL database server

### Installation

* JDK

  ```bash
  sudo add-apt-repository ppa:openjdk-r/ppa
  sudo apt install openjdk-11-jdk
  ```

1. Turn on your postgres server

2. Modify `/src/main/resources/application-develop.yml` configuration file.

    ```yml
   spring:
     datasource:
       username: <YOUR PSQL USERNAME>
       password: <YOUR PSQL PASSWORD>
       url: jdbc:postgresql://<YOUR PSQL IP>:<YOUR PSQL PORT>/<YOUR PSQL DB>
   ```

3. Modify `/alembic.ini` (DB migration tool configuration file based on python)

    ```diff
    - sqlalchemy.url = postgresql+psycopg2://roharon:password@localhost/gmoney-test
    + sqlalchemy.url = postgresql+psycopg2://<YOUR PSQL USERNAME>:<YOUR PSQL PASSWORD>@>YOUR PSQL IP>/<YOUR PSQL DB>
    ```

4. Get [local-money store current situation](https://www.data.go.kr/data/15058640/openapi.do) data-set and import to postgres.



## License

Distributed under the MIT License. See `LICENSE` for more information.

