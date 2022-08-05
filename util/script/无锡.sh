#!/bin/bash

# 城市列表
city_arr=(
110000
610100
320200
)

#日期列表
date_arr=(
202101
202102
202103
)



n=$(expr ${#city_arr[@]} - 1)
for (( i = 0; i < n; i++ )); do
    echo "i=$i"
done

if [ ${#date_arr[@]} == 3 ]; then
    echo "dui"
elif [ ${#date_arr[@]} == 2 ]; then
    echo "可能"
else
  echo "错"
fi

ftp_host=localhost
ftp_port=123456
ftp_user=tencent
ftp_pwd=jd
ftp_base_path='rtic/c=110000'
echo ${ftp_base_path}

download(){
  wget wget -c -m -t 5 --ftp-user=${ftp_user}  --ftp-password=${ftp_pwd} ftp://${ftp_user}:${ftp_pwd}@${ftp_host}:${ftp_port}/${ftp_user}/${ftp_base_path}
}
