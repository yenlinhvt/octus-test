# octus-test

#mongodb
database: mongo-octus
colelction: octus

#build command
mvn clean install

#tomcat start
mvn tomcat:run

#tomcat stop
mvn tomcat:shutdown

#GET - list all strings
http://localhost:8080/OctusTest/octus

#POST - insert new string
http://localhost:8080/OctusTest/octus
{
	"test": "Testing String"
}

#PUT - update string by id
http://localhost:8080/OctusTest/octus/update/{id}
{
	"test": "Update Testing String"
}

#DELETE - delete string by id
http://localhost:8080/OctusTest/octus/delete/{id}
