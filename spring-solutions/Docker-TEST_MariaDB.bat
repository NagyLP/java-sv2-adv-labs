cmd
docker run -d -e MYSQL_DATABASE=locations -e MYSQL_USER=*** -e MYSQL_PASSWORD=*** -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -p 3307:3306 --name locations-test-mariadb mariadb