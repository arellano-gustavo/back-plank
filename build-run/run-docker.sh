RUTA=/Users/garellano/development/plank/build-run
mvn -f ../pom.xml -Dmaven.test.skip clean package
cp ../target/*.jar deploy

docker stop plank-backend
docker rm plank-backend

echo "Ejecutando imagen"
docker run \
-d -p 9997:9998 \
--restart unless-stopped \
--name plank-backend \
-v $RUTA/deploy:/deploy \
-v $RUTA/uploads:/uploads \
-v $RUTA/configuration:/configuration \
-v $RUTA/private-repo:/private-repo \
-v $RUTA/log:/log \
-w /deploy \
openjdk:8 java \
-Dmaven.test.skip=true \
-Dspring.config.location="file:/configuration/" \
-Dspring.profiles.active=dev \
-jar plank-0.0.1-SNAPSHOT.jar

