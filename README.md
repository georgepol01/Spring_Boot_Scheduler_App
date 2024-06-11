# Spring_Boot_Scheduler_App

This application, built with Java 21 and Spring Boot 3.2.5, updates a table in an H2 database via a scheduled task. The scheduler processes a CSV file from a URL containing a zip archive, adding or updating records based on specific checks. Additionally, the application includes sample API integration tests using JUnit and Mockito.

The application provides two endpoints:

One to fetch all the table data.
One to fetch City data by ID.
The application leverages both default and custom mappers, as well as JPA Hibernate for data persistence.

The City table is populated with data from a CSV file. The total record count remains unchanged because the batch job, which runs every 15 seconds, finds no new or updated records to add.
![image](https://github.com/georgepol01/Spring_boot_scheduler/assets/115163100/f690215c-598d-4a62-a28a-7853ed7382fd)
![image](https://github.com/georgepol01/Spring_boot_scheduler/assets/115163100/1108d7f0-32fd-405a-9f80-75e8e9cb41d0)
Another log photo confirms the batch job is runnning.
![image](https://github.com/georgepol01/Spring_boot_scheduler/assets/115163100/b5f23e59-3264-4bc2-9485-2be5e821c947)
Testing the getCityById and findAll endpoints using Postman.
![image](https://github.com/georgepol01/Spring_boot_scheduler/assets/115163100/16cd2678-032d-436a-b3b9-6c1fb5fcfd60)
![image](https://github.com/georgepol01/Spring_boot_scheduler/assets/115163100/618f9e99-09f9-4911-a898-c4ba1e138939)


