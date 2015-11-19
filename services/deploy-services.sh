HOST=copyroute.com

#read -p "Penguins Password?" password

echo "Compiling War File"
rm -rf target/ && mvn clean compile war:war

echo "Uploading War File"
scp target/router.war root@$HOST:/opt/router.war

echo "Stopping Servers"
ssh root@$HOST 'service jetty stop; service rabbitmq-server stop; mv /opt/router.war /var/lib/jetty8/webapps/router.war;  service rabbitmq-server start; service jetty start; chown jetty:adm /var/lib/jetty8/webapps/router.war;'

echo "Deploy Complete"
