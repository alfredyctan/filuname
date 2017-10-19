WHOAMI=`whoami`
PID=`ps -ef | grep $WHOAMI | grep java | cut -b 12-16`

if [ -z $PID ]; then
	echo "application ${project.artifactId} is down"
else
    kill $PID
	echo "application ${project.artifactId} is stopped with pid [$PID]"
fi
