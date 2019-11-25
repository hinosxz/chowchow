# Chowchow
ChowChow, your daily showEntity manager

## Running the app locally

### Prerequisites
To run this project you need to have the following programs available:
- docker
- docker-compose

### Setting up environment variables

#### Back-end
Add a `.env` file at the root of the app with a valid config.
`.env.default` contains all variables that are necessary to run the app with default params.
Then run the following:
```
cp ./.env.default ./.env
```
Then you have to modify the variable `TMDB_API_KEY`: it needs to be a valid API key that you'll get on the TMDB website.

#### Front-end
There's another `.env.default` file at `src/main/javascript`. 
You need to add a `.env` file in the same directory with the same variables:
```
cp ./src/main/javascript/.env.default ./src/main/javascript/.env
```
Once again, if you haven't changed the default params, you shouldn't have to change anything.

### Packaging the Spring app
First of all you have to package the java app running the following command:
```
./mvnw clean package
```

### Setting up the containers
Then everything is handled by Docker and you can build and run the containers using `docker-compose`
```
docker-compose up --build
```
or if you don't want to display all the logs run:
```
docker-compose up -d --build
```
If you have an error because a port is already in use, be sure that the ports `3000`, `8080` and `5432` are not in use.
Otherwise you'll have to free them.

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
```
mvn javadoc:javadoc
```
The javadoc can the be accessed at `/target/site/apidocs/index.html
`
### API documentation
When ChowChow API is running, you can access the API documentation at `/swagger-ui.html`.