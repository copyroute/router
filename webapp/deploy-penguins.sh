HOST=copyroute.com
#read -p "Penguins Password?" password

echo "Compiling War File"
rm -rf target/ && mvn clean compile war:war

echo "Uploading War File"
scp target/news.war root@$HOST:/opt/root.war

echo "Stopping Servers"
ssh root@$HOST 'service jetty stop; service rabbitmq-server stop; mv /opt/root.war /opt/jetty/webapps/root.war;  service rabbitmq-server start; service jetty start; chown jetty:jetty /opt/jetty/webapps/root.war'

echo "Deploy Complete"
