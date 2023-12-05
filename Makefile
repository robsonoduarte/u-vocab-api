VERSION := $(shell git rev-parse --short HEAD)

build/image:
	@echo " "
	@echo "Building image u-vocab-api:${VERSION}"
	@echo " "
	./gradlew dockerBuildImage --parallel -Pversion=${VERSION}
	docker login -u ${DOCKER_LOGIN} -p ${DOCKER_PASSWORD}
	docker tag u-vocab-api:${VERSION} uvocab/u-vocab-api:${VERSION}
	docker push uvocab/u-vocab-api:${VERSION}

	
deploy/image:
	@echo " "
	@echo "Deploying u-vocab-api:${VERSION}"
	@echo " "
	ssh -i ${SSH_KEY} ${EC2_USER}@${EC2_INSTANCE} bash -s < deploy.sh ${VERSION} ${DOCKER_LOGIN} ${DOCKER_PASSWORD}
