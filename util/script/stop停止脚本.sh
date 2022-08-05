#!/bin/bash

# shellcheck disable=SC2009
pid=$(ps -ef | grep aaa.jar | grep -v grep | awk '{print $2}')
kill -9 "${pid}"
echo "$pid is stopped"