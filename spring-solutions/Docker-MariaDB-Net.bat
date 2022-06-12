cmd
@Echo off
docker network create locations-net
docker run -d -e MYSQL_DATABASE=locations -e MYSQL_USER=root -e MYSQL_PASSWORD=root555 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -p 3308:3306 --network locations-net --name locations-net-mariadb mariadb

docker stop locations
docker rm locations

docker run
      -d \
      -e SPRING_DATASOURCE_URL=jdbc:mariadb://locations-net-mariadb/locations \
      -p 8080:8080 \
      --network locations-net \
      --name locations \
      locations