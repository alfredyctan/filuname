WHOAMI=`whoami`
PID=`ps -ef | grep $WHOAMI | grep java | cut -b 12-16`

if [ -z $PID ]; then
	echo "application ${project.artifactId} is down"
else
	echo "application ${project.artifactId} is running with pid [$PID]"
fi
