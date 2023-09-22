VERSION := $(shell git rev-parse --short HEAD)

build/image:
	@echo " "
	@echo "Building image u-vocab-api:${VERSION}"
	@echo " "
	./gradlew dockerBuildImage --parallel -Pversion=${VERSION}
	docker tag u-vocab-api:${VERSION} uvocab/u-vocab-api:${VERSION}

push/image:
	@echo " "
	@echo "Pushing image u-vocab-api:${VERSION}"
	@echo " "
	docker login -u="${DOCKER_LOGIN}" -p="${DOCKER_PASSWORD}"
	docker push uvocab/u-vocab-api:${VERSION}