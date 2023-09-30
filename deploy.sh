#!/bin/bash

VERSION=$(git rev-parse --short HEAD)
DOCKER_LOGIN=$DOCKER_LOGIN
DOCKER_PASSWORD=$DOCKER_PASSWORD

ssh -i $SSH_KEY $EC2_USER@$EC2_INSTANCE <<'ENDSSH'

  sudo systemctl enable docker
  docker login -u $DOCKER_LOGIN -p $DOCKER_PASSWORD
  if [ $? -eq 0 ]; then
    echo "Login no Docker Hub bem-sucedido."
  else
    echo "Erro ao fazer login no Docker Hub."
  fi
  docker rm -f $(docker ps -aq --filter="name=u-vocab-api")
  docker pull uvocab/u-vocab-api:$VERSION
  docker run --restart=always -d \
  --log-opt max-size=10m --log-opt max-file=1 \
  -e TZ=America/Sao_Paulo \
  --name u-vocab-api \
  -p 8080:8080 -p 1099:1099 \
  -m=950m \
  uvocab/u-vocab-api:$VERSION

ENDSSH

