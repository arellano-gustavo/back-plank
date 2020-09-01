mvn -Dmaven.test.skip clean package

java \
-Dmaven.test.skip=true \
-Dspring.profiles.active=dev \
-jar target/plank-0.0.1-SNAPSHOT.jar


#-Dspring.config.location="file:/configuration/" \
#-Dspring-boot.run.profiles=dev \
