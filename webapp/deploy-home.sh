HOST=192.168.0.5
USER=engility

#read -p "Penguins Password?" password

echo "Compiling War File"
rm -rf target/ && mvn clean compile war:war

echo "Uploading War File"
scp target/news.war $USER@$HOST:/opt/root.war

echo "Stopping Servers"
ssh $USER@$HOST 'sudo service jetty8 stop;'
ssh $USER@$HOST 'sudo service rabbitmq-server stop;'
ssh $USER@$HOST 'mv /opt/root.war /var/lib/jetty8/webapps/root.war;'
ssh $USER@$HOST 'sudo service rabbitmq-server start;'
ssh $USER@$HOST 'sudo service jetty8 start;'

echo "Deploy Complete"
