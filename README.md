# Survey app coding test
Hello :) 

I coded this app using:

- MVVM with unidirectional data flow architecture, supported by clean architecture (check the module core/architecture to know more)
- composite build + convention plugins to have clean gradle files (written in kotlin), and version catalog for the dependencies
- Jetpack Compose for the UI, with Jetpack navigation and Material3
- Coroutines and Flow (hot and cold) for asyncronous programming
- Hilt for the dependency injection

I splitted the app into multiple modules, divided by core and features modules. Core modules are supposed to be shared across multiple modules, 
feature modules are about the features of the app. Inside the features directory I can have multiple modules for the same feature, according to
the clean architecture principles (like survey, survey-data and survey-domain), when no suffix the module is a presentation one.

I used an [annotation processor](https://medium.com/@simone.cascino1984/jetpack-compose-navigation-autogenerate-sealed-classes-for-your-destinations-dfd53ce0bf8d) made by me some time ago on my free time to handle the navigation offered by Jetpack Navigation with less boilerplate. 

I wrote unit tests for repository, viewmode, usecases and 2 mapper typealiases.

Regarding the repository, I choose a simpler approach, leaving the state logic in the viewmodel (check the comment in the SurveyRepositoryImpl class). I could instead store all the api results in a stateflow, but one point of the requirement made me think it was not a good idea.
