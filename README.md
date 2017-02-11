# Health Application

[Heroku](https://final-process-service.herokuapp.com/healthapp) | [GitHub](https://github.com/unitn-introsde2017-final-project)


## API Calls

### /healthapp [GET]

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

### /healthapp/getPersonProfile [GET]

Shows all the relevant information related to a profile:
* Profile and personal informations like
  * Name / City / Birthday / Step goal 
* Step informations (steps per day history)
  * Motivation text if step goal not reach for the day
  * Motivation to keep moving if the goal is reached
* Weather information to the set City in the profile
  * Text detailing weather conditions whether to go outside or not

```json
{
  "stepGoal": 6000,
  "weather_info": "The temperature is -4 C",
  "birthdate": "1992-05-06",
  "city": "Trento,it",
  "name": "Nagy Sándor Tibor",
  "id": 1,
  "steps": [
    {
      "date": "2017-01-25",
      "number": 6019,
      "personId": 1,
      "id": 1
    },
    ...
    {
      "date": "2017-02-08",
      "number": 3000,
      "personId": 1,
      "id": 11
    }
  ],
  "steps_today": "You only need to do a little more to reach your goal, you already made 3000 steps today!"
}
```

Possible ways to improve user experience:
* Keep track of the most days in a row when the user managed to reach the daily goal
  * The client has all the step history for a user so to implement this is not a big deal
* Implement some community features so people can share their results and help motivate their friends to push their limits
* Add new measurements

### /healthapp/addUpdateStepCount [PUT]

Add or update step information for a person.

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
  "date": "2017-01-31",
  "number": 6021,
  "personId": 2
}
```

### /healthapp/updateProfile [POST]

You can change the person private details and step goal here.

Example request:

```json
{
  "id": 3,
  "birthdate": "1990-05-19",
  "city": "Debrecen,hu",
  "name": "Teszt Remote",
  "stepGoal": 4517
}
```

Not yet implemented.

## Architecture

### Service connections

![Image of the Service orchestration](http://salaander.hu/sde/sde_final_project.png)

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

## Heroku deployment

For Heroku deployment I used this buildpack: [heroku-buildpack-ant](https://github.com/IntroSDE/heroku-buildpack-ant)

Created the applications with this command:  
```
heroku apps:create ServiceName --region eu --stack cedar --buildpack https://github.com/IntroSDE/heroku-buildpack-ant.git
```

This way the program had the name I wanted not some random generated name, was created in the EU region which makes response times better and uses the cedar stack with our ant buildpack.

I published my services by using the Heroku GIT way: `git push heroku master`

## Copyright

&copy; Sándor Tibor Nagy as the final project for Introduction to Service Design and Engineering course 2016/2017 at [UNITN](http://www.unitn.it/)