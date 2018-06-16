FROM ubuntu:16.04

RUN apt-get update -y && \
    apt-get install -y software-properties-common && \
    add-apt-repository ppa:webupd8team/java && \
    apt-get install -y software-properties-common apt-transport-https python-software-properties debconf-utils && \
    apt-get update -y && \
    echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get install -y maven pwgen nginx

WORKDIR /app
ADD . /app
