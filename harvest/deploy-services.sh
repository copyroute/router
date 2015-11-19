HOST=copyroute.com
WAR=harvest.war

#read -p "Penguins Password?" password

echo "Compiling War File"
rm -rf target/ && mvn clean compile war:war

echo "Uploading War File"
scp target/$WAR root@$HOST:/opt/$WAR

echo "Stopping Servers"
ssh root@$HOST 'service jetty stop; service rabbitmq-server stop; mv /opt/harvest.war /opt/jetty/webapps/harvest.war;  service rabbitmq-server start; service jetty start; chown jetty:jetty /opt/jetty/webapps/harvest.war'

echo "Deploy Complete"
