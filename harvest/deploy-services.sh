HOST=copyroute.com

#read -p "Penguins Password?" password

echo "Compiling War File"
rm -rf target/ && mvn clean compile war:war

echo "Uploading War File"
scp target/router.war root@$HOST:/opt/router.war

echo "Stopping Servers"
ssh root@$HOST 'service jetty stop; service rabbitmq-server stop; mv /opt/router.war /opt/jetty/webapps/router.war;  service rabbitmq-server start; service jetty start;'
#ssh root@$HOST 'service jetty stop; service rabbitmq-server stop; service mongodb stop; mv /opt/router.war /opt/jetty/webapps/router.war; service mongodb start; service rabbitmq-server start; service jetty start;'

echo "Deploy Complete"
