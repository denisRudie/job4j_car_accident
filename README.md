### Car accidents

[![Build Status](https://travis-ci.com/denisRudie/job4j_car_accident.svg?branch=main)](https://travis-ci.com/denisRudie/job4j_car_accident)

### О проекте
Площадка для фиксации автомобильных правонарушений. Построено на jsp+bootstrap, Spring
. Позволяет пользователям фиксировать ДТП. Правонарушения сохраняются в БД.
### Technologies
* Java 14
* Java EE Servlets
* Apache Tomcat
* Spring (MVC, Data, Security)
* Postgres
* jsp, Bootstrap (front)
* Maven as a build system
* Travis CI
### Возможности
* Авторизация/регистрация. Доступ только у авторизованных пользователей (реализовано через Spring
 Security).
* Добавление записей. Параметры выбираются из справочников.
* Редактирование записей.
### Demo
Working demo
![Watch the video](images/carAccidentDemo.gif)

Login page
![ScreenShot](images/loginPage.jpg)

Sign Up page
![ScreenShot](images/registerPage.jpg)

Accidents list
![ScreenShot](images/mainPage.jpg)

Creating page
![ScreenShot](images/creatingPage.jpg)

Data base schema
![ScreenShot](images/dbSchema.jpg)