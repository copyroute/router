HOST=copyroute.com
#read -p "Penguins Password?" password

echo "Compiling War Files"

pushd cdm
rm -rf target/ && mvn clean generate-sources compile install
popd

pushd services
rm -rf target/ && mvn clean compile war:war install
./deploy-services.sh
popd

pushd webapp
rm -rf target/ && mvn clean compile war:war install
./deploy-penguins.sh
popd

pushd harvest
rm -rf target/ && mvn clean compile war:war install
./deploy-services.sh
popd

