# How to Run

This is a step-by-step guide how to run the example:

## Prereqs

* Java - JDK 
   See https://www.java.com/en/download/help/download_options.xml .

* Docker Community Edition
   See https://www.docker.com/community-edition/

* Configure Docker to use 4 GB of RAM in the Docker application under
   Preferences/Advanced.

## Build Source

Go to the directory `wfm-master/wfm` and run `./mvnw clean
package` or `mvnw.cmd clean package` (Windows). This will take a while:

If this does not work:

* Ensure that `settings.xml` in the directory `.m2` in your home
directory contains no configuration for a specific Maven repo. If in
doubt: delete the file.

* The tests use some ports on the local machine. Make sure that no
server runs in the background.

* Skip the tests: `./mvnw clean package Dmaven.test.skip=true` or
  `mvnw.cmd clean package -Dmaven.test.skip=true` (Windows).

* In rare cases dependencies might not be downloaded correctly. In
  that case: Remove the directory `repository` in the directory `.m2`
  in your home directory. Note that this means all dependencies will
  be downloaded again.

## Build Docker Images

Go to the directory `docker` and run `docker-compose build`. This will 
download some base images, install source artifacts into Docker images.

Afterwards the Docker images should have been created. They have the prefix
`wfm`:

```
[~/wfm/docker]docker images 
REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
wfm_audit             latest              0b798e7d955c        3 seconds ago       213MB
wfm_employee          latest              b8c643a28a4a        6 seconds ago       214MB
wfm_postgres          latest              44dd87e766a5        21 minutes ago      269MB
ubuntu                16.04               f975c5035748        5 weeks ago         112MB
postgres              9.6.3               33b13ed6b80a        8 months ago        269MB
ewolff/docker-java    latest              54ad137c4841        8 months ago        170MB
```

## Start Docker Containers
Now you can start the containers using `docker-compose up -d`. The
`-d` option means that the containers will be started in the
background and won't output their stdout to the command line:

During the first start the Docker images for Zookeeper and Kafka will be downloaded during this step.
```
λ docker-compose up -d
Creating network "wfm_default" with the default driver
Pulling zookeeper (wurstmeister/zookeeper:3.4.6)...
3.4.6: Pulling from wurstmeister/zookeeper
a3ed95caeb02: Pull complete
ef38b711a50f: Pull complete
e057c74597c7: Pull complete
666c214f6385: Pull complete
c3d6a96f1ffc: Pull complete
3fe26a83e0ca: Pull complete
3d3a7dd3a3b1: Pull complete
f8cc938abe5f: Pull complete
9978b75f7a58: Pull complete
4d4dbcc8f8cc: Pull complete
ccf399e5f3b7: Pull complete
a80f54931267: Pull complete
551a52c71f3f: Pull complete
d77a729a2184: Pull complete
Digest: sha256:294d69bb580a614ed3128969b95b5355c480e84704d826cdf73e790b5a6e63fc
Status: Downloaded newer image for wurstmeister/zookeeper:3.4.6
Pulling kafka (wurstmeister/kafka:1.0.0)...
1.0.0: Pulling from wurstmeister/kafka
90f4dba627d6: Pull complete
11dbde1d93a0: Pull complete
b08a221100dc: Pull complete
8a1ea32227be: Pull complete
594f88f1d9ba: Pull complete
1f10284cbcb9: Pull complete
2fce4c7fddcf: Pull complete
51e64f39a714: Pull complete
517706dd675b: Pull complete
Digest: sha256:a46e42c8e4c872ca2f7064e8bf4187dc875f8c7028b82803f42d00429f58c5e9
Status: Downloaded newer image for wurstmeister/kafka:1.0.0
Creating wfm_zookeeper_1 ... done
Creating wfm_postgres_1  ... done
Creating wfm_kafka_1     ... done
Creating wfm_employee_1  ... done
Creating wfm_audit_1     ... done
```

Check whether all containers are running using `docker ps`:


```
[~/wfm/docker]docker ps
CONTAINER ID        IMAGE                          COMMAND                  CREATED             STATUS              PORTS                                                NAMES
44051d7353e8        wfm_audit                      "/bin/sh -c '/usr/bi…"   36 minutes ago      Up 36 minutes       0.0.0.0:5006->5006/tcp, 0.0.0.0:9090->9090/tcp       wfm_audit_1
988a91c30209        wfm_employee                   "/bin/sh -c '/usr/bi…"   36 minutes ago      Up 36 minutes       0.0.0.0:5005->5005/tcp, 0.0.0.0:8080->8080/tcp       wfm_employee_1
ed6c76a7d092        wurstmeister/kafka:1.0.0       "start-kafka.sh"         30 hours ago        Up 36 minutes                                                            wfm_kafka_1
d4e05ceb60ab        wurstmeister/zookeeper:3.4.6   "/bin/sh -c '/usr/sb…"   30 hours ago        Up 30 hours         22/tcp, 2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp   wfm_zookeeper_1
871c7f6afc84        wfm_postgres                   "docker-entrypoint.s…"   30 hours ago        Up 30 hours         5432/tcp                                             wfm_postgres_1
```

## Using the Application

The application is currently under construction :)


