#!/bin/bash

source /etc/profile

# shellcheck disable=SC2002
MEM_TOTAL=$(cat /proc/meminfo | grep 'MemTotal' | awk '{print $2}')
MEM_FOR_JAVA=$(expr $MEM_TOTAL \* 7 / 10 / 100)
MEM_JVM="-Xms${MEM_FOR_JAVA}m -Xmx${MEM_FOR_JAVA}m"


Operation="$1"
APP_NAME="$2"
ENV="$3"
CONFIG_URL="localhost:8080"


# shellcheck disable=SC2006
HOST_NAME=`/bin/hostname`
DETECTOR_OPTS="-javaagent:/opt/pmo/detector-agent/detector-bootstrap.jar -Ddetector.agentId=$HOST_NAME -Ddetector.applicationName=${ENV_MATCH}_${APP_NAME}"


get_pid(){
  # shellcheck disable=SC2006
  # shellcheck disable=SC2009
  # shellcheck disable=SC2034
  PIDS=`ps -ef | grep java | grep "/$APP_NAME" | grep -v grep | awk '{print $2}'`
}

start_app(){
  get_pid
  if [ -n $PIDS ]; then
    echo "tip: $APP_NAME has been started! pid: $PIDS"
  else
    echo "starting ${APP_NAME} ..."
    nohup java "$MEM_JVM" "$DETECTOR_OPTS" -Dfile.encoding=UTF-8 -Dspring.profiles.active="$ENV" -Dpmo.apollo.urls=$CONFIG_URL -jar /opt/moudles/"$APP_NAME".jar >/dev/null 2>&1 &
  fi
}

stop_app(){
  get_pid
  if [ -z "$PIDS" ]; then
      echo "tip: $APP_NAME is not started!"
  else
      echo "stopping ${APP_NAME} ..."
      kill -9 $PIDS
  fi
}

case ${Operation} in
start)
  start_app
  ;;
stop)
  stop_app
  ;;
restart)
  stop_app
  sleep 3
  start_app
  ;;
*)
  echo "usage--$1(start|stop|restart)--$2(app_name)--$3(env)"
  ;;
esac
