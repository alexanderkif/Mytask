To run in Idea:
run SpringBoot project
and
GWT with parameters " -noserver -port 8080 -war C:\Users\...\src\main\resources\static" together


or:
mvn clean spring-boot:run
and then in a different window
mvn gwt:devmode -Pgwt-dev