# Chowchow
ChowChow, your daily showEntity manager

## Running the app locally

### Setting up environment variables
Add a `.env` file at the root of the app with a valid config

`.env.default` contains all variables that are necessary to run the app with default params

`TMDB_API_KEY` needs to be a valid API key that you'll get on the TMDB website.

### Packaging the app
First of all you have to package the app:
`./mvnw -X clean package`

### Setting up the containers
Then you can build and run the containers using `docker-compose`
```
docker-compose build
docker-compose up
```

### Setting up the database
The database schema is automatically created by Flyway migrations 
and validated by Hibernate.

### Setting-up the front-end
Run the following commands to start the development server.
```$xslt
cd src/main/javascript
yarn start
```
You'll be able to test the UI on `localhost:3000`

## Project Documentation

### Javadoc
To generate the Javadoc run:
```$xslt
mvn javadoc:javadoc
```
The javadoc can the be accessed at `/target/site/apidocs/index.html
`
### API documentation
When ChowChow API is running, you can access the API documentation at `/swagger-ui.html`.