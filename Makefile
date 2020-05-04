JAR ?= ./target/clines-api-0.0.7-SNAPSHOT.jar
ENV ?= local
HEROKU_APP_NAME=caelum-airlines-api

package:
	$(info Packaging application)
	@ ./mvnw clean package -DskipTests

docker/build: package $(JAR)
	$(info Building docker image)
	@ docker image build --build-arg JAR=$(JAR) -t caelum/clines-api:latest .

deploy: _build-docker-image _login-with-registry
	@ docker tag clines-api  registry.heroku.com/$(HEROKU_APP_NAME)/web:$$TRAVIS_BUILD_ID
	@ docker image push registry.heroku.com/$(HEROKU_APP_NAME)/web:$$TRAVIS_BUILD_ID
	@ make _deploy-on-heroku IMAGE_ID=$$(docker image inspect registry.heroku.com/$(HEROKU_APP_NAME)/web:$$TRAVIS_BUILD_ID -f {{.Id}} )


docker/ls:
	@ make _docker-compose stage=local command='ps'

run: docker/build
	$(info Starting containers)
	@ make _docker-compose stage=local command='up -d'
stop:
	$(info Stoping containers)
	@ make _docker-compose stage=local command='down -v'

test:
	@ make _docker-compose stage=test command='up -d'
	@ sleep 3
	@ ./mvnw clean test &&  make _docker-compose stage=test command='down -v' || make _docker-compose stage=test command='down -v'

psql:
	@ make _docker-compose stage=local command='exec database psql -U postgres'

install:
	$(info Installing contracts)
	@ ./mvnw versions:set -DnewVersion=$$TRAVIS_BUILD_ID
	@ ./mvnw install -DskipTests

_docker-compose:
ifeq ($(ENV), local)
	@ docker-compose -f docker-compose.yml -f docker-compose/compose-$(stage).yml $(command)
endif

_build-docker-image: package
	@ docker image build --build-arg JAR=$(JAR) -t clines-api .

_login-with-registry:
	@ docker login --username=_ --password=$$DOCKER_REGISTRY_TOKEN registry.heroku.com

_deploy-on-heroku:
	@ curl -X PATCH \
			-H "Authorization: Bearer $$DOCKER_REGISTRY_TOKEN" \
			-H "Content-Type: application/json" \
			-H "Accept:application/vnd.heroku+json; version=3.docker-releases" \
			-d '{ "updates": [{"type": "web",  "docker_image": "$(IMAGE_ID)"}] }' \
			https://api.heroku.com/apps/$(HEROKU_APP_NAME)/formation