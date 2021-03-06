HOST=localhost
#read -p "Penguins Password?" password

echo "Compiling War File"
rm -rf target/ && mvn clean compile war:war

echo "Uploading War File"
cp target/news.war root@$HOST:/opt/root.war

echo "Stopping Servers"
ssh root@$HOST 'service jetty stop; service rabbitmq-server stop; mv /opt/root.war /opt/jetty/webapps/root.war;  service rabbitmq-server start; service jetty start;'
#ssh root@$HOST 'service jetty stop; service rabbitmq-server stop; service mongodb stop; mv /opt/root.war /opt/jetty/webapps/root.war; service mongodb start; service rabbitmq-server start; service jetty start;'

echo "Deploy Complete"
