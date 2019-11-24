# Chowchow
ChowChow, your daily showEntity manager

## Running the app locally

### Prerequisites
- Docker

### Setting up environment variables

#### Back-end
Add a `.env` file at the root of the app with a valid config.

`.env.default` contains all variables that are necessary to run the app with default params.

`TMDB_API_KEY` needs to be a valid API key that you'll get on the TMDB website.

#### Front-end
There's another `.env.default` file at `src/main/javascript`. 
You need to add a `.env` file in the same directory with the same variables.
Once again, if you haven't changed the default params, you shouldn't have to change anything.


### Packaging the Spring app
First of all you have to package the java app running the following command:
`./mvnw clean package`

### Setting up the containers
Then everything is handled by Docker and you can build and run the containers using `docker-compose`
```
docker-compose build
docker-compose up
```

### Database
The database schema is automatically created by Flyway migrations 
and validated by Hibernate.

---

Everything should by now be up and running. 
Default ports are 8080 for the API and 3000 for the UI.
So going to `http://localhost:3000` should take you to the app.
Use `http://localhost:3000` if you want to make API calls to ChowChow

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