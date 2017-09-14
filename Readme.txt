Projekt je vytvorený vo OS Windows 7/10 v IDE IntelliJ IDEA Community Edition 

Preto aby bolo mozne z buildovat prokjekt
je nutne mat nainstalovany MAVEN a JAVA SE 8
napriklad podla
https://maven.apache.org/install.html

Program je z buildovatelny prikazom mvn clean install v hlavnom projektovom adresari interierdesign.
Po spusteni príkazu 

mvn clean install
sa 
Jar subor vygeneruje v podadresari
\interierdesign\furnituredeploy\target\ s nazvom room.placement.furniture-1.0-jar-with-dependencies.jar

Pomocou skriptu
prepare.bat

sa pripravi balicek jar-ko:
Prepare vyzera:
@echo off
del room.placement.furniture-1.0-jar-with-dependencies.jar  
build.bat && copy furnituredeploy\target\room.placement.furniture-1.0-jar-with-dependencies.jar .

Pricom 
build.bat vyzera takto:
@echo off
mvn clean install 

JAVA_HOME nech ukazuje na JDK 8:
Tak potom program sa sputi:
java -jar room.placement.furniture-1.0-jar-with-dependencies.jar
vid run.cmd

Konfiguracny súbor, kde sa definuju cesty k suborom je  je priamo v jar room.placement.furniture-1.0-jar-with-dependencies.jar.zip\iohandle-conf.xml
Ak sa nic nezmeni, tak program
ocakava tieto subory v adresari v ktorom ste spustili jar-ko
req_partroom.txt  - definicia izby
req_partfurniture.txt -definicia nabytku

vystup sa ulozi do suboru
response.txt



