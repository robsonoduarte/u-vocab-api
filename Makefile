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

deploy/app:
	@echo " "
	@echo "Deploy u-vocab-api:${VERSION}"
	@echo " "
    ssh -i "${SSH_KEY}" "${EC2_USER}"@"${EC2_INSTANCE}" bash -s < deploy.sh ${VERSION} ${DOCKER_LOGIN} ${DOCKER_PASSWORD}





