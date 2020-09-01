cd ~
mkdir -p plank-db/{mysql,scripts}

RUTA=$HOME/plank-db/mysql

### AQUI HAY QUE AJUSTAR LA VARIABLE "SCRIPTS" PARA QUE APUNTE
### AL DIRECTORIO QUE CONTIENE EL ARCHIVO: dump.sql
# SCRIPTS=/Users/garellano/development/plank/src/main/resources/db
SCRIPTS=$HOME/plank-db/scripts

docker stop plank
docker rm plank

docker run -d \
--name plank \
--restart unless-stopped \
-p 3306:3306 \
-v $RUTA:/var/lib/mysql \
-v $SCRIPTS:/scripts \
-e MYSQL_ROOT_PASSWORD=UrbiEtOrbi1 \
mariadb

#mariadb --default-time-zone=America/Mexico_City
# docker logs -f plank

echo "Corriendo el script de inicialización de la base de datos..."
sleep 10
docker exec -it -w /scripts plank bash ./init.sh
echo "Base de datos inicializada !!!"







