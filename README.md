# chowchow
ChowChow, your daily showEntity manager

## Running into Docker container

### Packaging the app
First of all you have to package the app:
`/mvnw -X clean package`

### Setting up the containers
You first need to build the chowchow-api image by running:
`docker build ./ -t chowchow-api`

You can now run chowchow using docker-compose by running:
`docker-compose up`

### Setting up the database
Finally you can set up the database by running the query in `./setup_postgres_db.sql`:
`docker exec -it chowchow-db psql -U postgres -d chowchow -c "$(cat setup_postgres_db.sql)"`