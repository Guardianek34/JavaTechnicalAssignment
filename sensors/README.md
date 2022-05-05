
    COMPILATION

---------------------------------------------

Application has been compiled using Maven
(and to be precise - command below):

mvn clean package

---------------------------------------------

    HOW TO RUN SERVER

---------------------------------------------

In order to run the program you are required to have installed Java JDK 11.

You can use for example Git Bash to run the .sh script with a command
(when in the same directory).

./projektsh.sh {param}

In the place of {param} you should enter path to the
file inside GitHub's Repository with "--config" annotation. 

For example:

./projektsh.sh --config={GitHub URL}}


There is another possibility to run .bat file (projekt.bat) by double clicking on it.
In order to change the {param}, you have to edit the part starting with --config=.

java -jar app.jar --config={GitHub URL}


Third way is to compile it using java in command line.
Simply head to the same directory as the file and enter command.

java -jar app.jar --config={GitHub URL}

---------------------------------------------

    Unit tests

---------------------------------------------

In case of unit tests, I was having some difficulties compiling them to .jar file.
Nevertheless, they're all passing, and you can compile and see their
content them using any newer IDE.

---------------------------------------------

    Additional tools

---------------------------------------------

After running the system you have access to the H2 database at the URl:

http://localhost:8080/h2/

JDBC URL: jdbc:h2:mem:memDb
User Name: sa
Password: 1234

I've also included OpenAPI specification that you can use to create requests
to API and also find information about endpoints. URL:

http://localhost:8080/swagger-ui.html