alias app='cd filuname/target/filuname/*' 

mvn clean install
cd filuname/target
tar -zxvf filuname-*-server.tar.gz
cd filuname/*
./start.sh

echo ""
echo "=============================================="
echo "log path : filuname/target/filuname/1.0.0-SNAPSHOT/log"
echo "if you need proxy to access the exclusion api, update the proxy configuration in filuname/target/filuname/1.0.0-SNAPSHOT/config/exclusion-import.xml"
echo "csv import : filuname/target/filuname/1.0.0-SNAPSHOT/import"
echo "Frontend : http://localhost:10080/index.html"
echo "Login : admin/admin, user1/user1, user2/user2"
echo "update the "
echo "    filuname/target/filuname/1.0.0-SNAPSHOT/schema/hsqldb/Exclusion.populate.sql"
echo "    and the exclusion.api.interval in filuname/target/filuname/1.0.0-SNAPSHOT/config/application.properties"
echo "if you want to skip the exclusion list from api"
echo "the application is started"
