JAR ?= ./target/clines-api-0.0.1-SNAPSHOT.jar
ENV ?= local

package:
	$(info Packaging application)
	@ ./mvnw clean package -DskipTests

docker/build: package $(JAR)
	$(info Building docker image)
	@ docker image build --build-arg JAR=$(JAR) -t caelum/clines-api:latest .

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