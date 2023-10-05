#!/bin/bash

VERSION=$1
DOCKER_LOGIN=$2
DOCKER_PASSWORD=$3

sudo systemctl enable docker
docker login -u $DOCKER_LOGIN -p $DOCKER_PASSWORD
docker rm -f $(docker ps -aq --filter="name=u-vocab-api")
docker pull uvocab/u-vocab-api:$VERSION
docker run --restart=always -d \
  --name u-vocab-api \
  -p 8080:8080 \
  uvocab/u-vocab-api:$VERSION

