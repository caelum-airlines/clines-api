JAR ?= ./target/clines-api-0.0.1-SNAPSHOT.jar

package:
	$(info Packaging application)
	@ ./mvnw clean package

docker/build: package $(JAR)
	$(info Building docker image)
	@ docker image build --build-arg JAR=$(JAR) -t caelum/clines-api:latest .

run: docker/build
	$(info Starting containers)
	@ docker-compose up -d