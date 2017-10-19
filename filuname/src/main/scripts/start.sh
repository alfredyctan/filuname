CLASSPATH=`find lib -name *.jar`
CLASSPATH=`echo $CLASSPATH | sed "s/ /;/g"`
CLASSPATH="config;webapp;schema;$CLASSPATH"

echo "running application with classpath : $CLASSPATH"

java -Dapp.name=${project.artifactId} -Dapp.env=dev -cp $CLASSPATH org.alf.filuname.FilunameLauncher 2>&1 > "log/${project.artifactId}.`date +%Y%m%d-%H%M%S`.console.log" &
