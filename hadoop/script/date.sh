#!/bin/bash
#SHELL=/bin/bash
#PATH=/sbin:/bin:/usr/sbin:/usr/bin
#MAILTO=root
#HOME=/

#需求：获取上月年月，本月年月，下月年月
#      2016-06       2016-07   2016-08
#
#坑01
#2016年当month为01的时候，上年月份为2015-12
#2016年当month为12的时候，下年月份为2017-01
#2016年当month为其余月份时候，月份加1或者减一
#
#坑02
# 1、使用date时候，如果系统时间为2016-05-31
#    `date -d "1 month ago" +%Y-%m` 为2016-05
# 2、使用date时候，如果系统时间为2016-05-31
#    `date -d "1 month " +%Y-%m` 为2016-07
#


year=`date -d "0 month ago" +%Y`

month=`date -d "0 month ago" +%m`

nowdate=`date -d "0 month ago" +%Y-%m`

echo "---"
echo year
echo month
echo

one='01'
if [ $month == $one ]
then
echo "当month为01的时候，上年月份为2015-12"
LastYear=$[$year-1]
Lastdate=$LastYear-12
Nextdate=$year-0$[$month+1]
echo $Lastdate
echo $nowdate
echo $Nextdate
fi

twelve='12'
if [ $month == $twelve ]
then
echo "当month为12的时候，下年月份为2017-01"
NextYear=$[$year+1]
Lastdate=$year-$[$month-1]
Nextdate=$NextYear-01
echo $Lastdate
echo $nowdate
echo $Nextdate
fi


if [ $month -ne $twelve ]
then
  if [ $month -ne $one ]
  then
      ten=10
      if [ $[$month-1] -lt $ten ]
      then
      monthmin=0$[$month-1]
      else
      monthmin=$[$month-1 ]
      fi

      if [ $[$month+1] -lt $ten ]
      then
      monthadd=0$[$month+1]
      else
      monthadd=$[$month+1 ]
      fi

      echo $year-$monthmin
      echo `date -d "0 month ago" +%Y-%m`
      echo $year-$monthadd
  fi
fi