# BOOK ADDICT

In order to develop on the application, you have to get the following:

 1. **maven** - http://mirrors.m247.ro/apache/maven/maven-3/3.5.3/source/apache-maven-3.5.3-src.zip
 2. **flyway** - https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/5.0.7/flyway-commandline-5.0.7-windows-x64.zip
3. **BOTH maven and flyway must be added to the PATH environmental variable**.
4. **MySQL Server** 
5. **MySQL Workbench**
6. Run MySql server on the default port, 3306, with the user **root** and a password at your choice. Inside the aplication, in the **application.properties** file, modify the password to match yours. After that, run MySQL Workbench and create a schema named **book_addict**.
7. Run **BookAddict** launcher from the main package inside API. 