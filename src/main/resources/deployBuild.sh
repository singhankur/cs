 #!/bin/bash


cd ~/apache-tomcat-8.0.36/bin
./shutdown.sh

cd ../webapps/

rm -rf cs*

cd ~/codebase/cs/

git pull --rebase origin master
mvn clean install
cp target/cs.war  /home/ubuntu/apache-tomcat-8.0.36/webapps/

cd ~/apache-tomcat-8.0.36/bin
./startup.sh

echo "ALL Done"
