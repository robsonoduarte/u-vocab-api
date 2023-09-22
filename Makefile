REPOSITORY := com
DOCKER_IMAGE_NAME := u-vocab-api
COMMIT_HASH := $(shell git rev-parse HEAD)

CURRENT_DIR := $(shell pwd)

build:
	./gradlew build

clean:
	./gradlew clean

image:
	./gradlew dockerBuildImage

tag:
	docker build -t $(REPOSITORY)/$(DOCKER_IMAGE_NAME):$(COMMIT_HASH)	.



.PHONY: build tag