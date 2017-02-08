# Health Application

[Heroku]() | [GitHub](https://github.com/unitn-introsde2017-final-project)


## API Calls

### /healthapp

Return basic information about the application.

```json
{
  "Application name": "Health App",
  "Copyright": "Sándor Tibor Nagy",
  "Matricola": "188895",
  "Project": "Introduction to Service Design and Engineering",
  "Documentation": "https://github.com/unitn-introsde2017-final-project"
}
```

### /healthapp/getPersonProfile

Shows all the relevant information related to a profile:
* Profile and personal informations like
  * Name / City / Birthday / Step goal 
* Step informations (steps per day history)
  * Motivation text if step goal not reach for the day
  * Number of days in the last week when the person reached the stepgoal
* Weather information to the set City in the profile
  * Text detailing weather conditions wheter to go outside or not

```json
...
```

### /healthapp/addUpdateStepCount

Add or update step information for a person.

You need the person's ID, the date for the step count and the number of steps.  
If there is no entry for the given day it will be created (for this id is not required)  
If there is an entry already you need to send the request with it to update the number  

Example for create:

```json
{
  "date": "2017-01-31",
  "number": 6021,
  "personId": 2
}
```

Example for update:

```json
{
  "id": 10,
  "number": 6021
}
```

### /healthapp/updateProfile

You can change the person private details and step goal here.

Example request:

```json
...
```

Not yet implemented.

## Architecture

### Service connections

diagram about the service connections

### Process Centric Service

[Heroku]() | [GitHub](https://github.com/unitn-introsde2017-final-project/final-ProcessCentricService)

### Business Logic Service

[Heroku]() | [GitHub](https://github.com/unitn-introsde2017-final-project/final-BusinessLogicService)

### Storage Service

[Heroku](https://final-storage-service.herokuapp.com/StorageService/UserProfile?userId=1) | [GitHub](https://github.com/unitn-introsde2017-final-project/final-StorageService)

### Database Service

[Heroku](https://final-local-database.herokuapp.com/sdelab/person) | [GitHub](https://github.com/unitn-introsde2017-final-project/final-LocalDatabase)

### Weather Adapter Service

[Heroku](https://final-weather-adapter.herokuapp.com/ws/weather?wsdl) | [GitHub](https://github.com/unitn-introsde2017-final-project/final-WeatherAdapter)

## Database

I used MySQL as my database since SQLite is not good for Heroku as with every application reset the database would be gone.  
Since the default solution offered for the free version of Heroku did not sit well with me, I installed and set-up a MySQL server on my home server with the following details for this project:

Host: salaander.hopto.org  
User: 'introsde'@'%'  
Password: introSDE123!  
Database: introsde  

The database model looks like this:

![Image of the DB model](http://salaander.hu/sde/sde_db.png)
Problems to solve:

JPA caches the results, so if there is a data update outside the Database Service process it won't be reflected for a time.

## Heroku deployment

For Heroku deployment I used this buildpack: [heroku-buildpack-ant](https://github.com/IntroSDE/heroku-buildpack-ant)

Created the applications with this command:  
`heroku apps:create final-storage-service --region eu --stack cedar --buildpack https://github.com/IntroSDE/heroku-buildpack-ant.git`  
This way the program had a name I wanted, was created in the EU region which makes response time better and uses the cedar stack with our ant buildpack.

I published my services by using the Heroku GIT way: `git push heroku master`


## Copyright

This service was made by Sándor Tibor Nagy as the final project for Introduction to Service Design and Engineering class at UNITN. 2016/2017