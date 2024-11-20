Pruebas de carga con Galting


Intalar dependecias
mvn clean install -DskipTests=true

Correr el repo 

mvn gatling:test -Dgatling.simulationClass=simulations.Boletia
