# Chowchow
ChowChow, your daily showEntity manager

## Running into Docker container

### Setting up environment variables
Add a `.env` file at the root of the app with a valid `TMDB_API_KEY`.

### Packaging the app
First of all you have to package the app:
`./mvnw -X clean package`

### Setting up the containers
Then you need to build the chowchow-api image by running:
`docker build -f dev.Dockerfile ./ -t chowchow-api`

You can now run chowchow using docker-compose by running:
`docker-compose up`

### Setting up the database
Finally you can set up the database by running the query in `./setup_postgres_db.sql`:
`docker exec -it chowchow-db psql -U postgres -d chowchow -c "$(cat setup_postgres_db.sql)"`

### Setting-up the front-end
Run the following commands
```
cd src/main/javascript
yarn start
```
You'll be able to test the UI on `localhost:3000`

## API Documentation
Available when the server is running at `/swagger-ui.html`