#!/bin/bash
:<<!
多行注释
RUNOOB
LD_LIBRARY_PATH
_var
var2
!

for file in `ls /home/postgre`
do
echo $file
done

echo "*****************************************"

for file in $(ls /home/postgre)
do
echo $file
done

# 定义一个变量
echo "************ 定义一个变量 美元符加上变量名 大括号可以加也可以不加 ****************"
your_name="sunkuan"
echo $your_name
echo ${your_name}

# 使用 readonly 命令可以将变量定义为只读变量，只读变量的值不能被改变
myUrl="www.baidu.com"
readonly myUrl
# myUrl="www.google.com"

# shell字符串

# 单引号
:<<!
单引号里的任何字符都会原样输出，单引号字符串中的变量是无效的
单引号字串中不能出现单独一个的单引号（对单引号使用转义符后也不行），但可成对出现，作为字符串拼接使用
!
str='this is a string'

# 双引号
:<<!
双引号里可以有变量
双引号里可以出现转义字符
!
a_str="baidu"
str="Hello I love you \"$a_str\"! \n"
echo -e $str

# 拼接字符串
you="baidu"
# 使用双引号拼接
greeting="hello, "$you" !"
greeting_1="hello, ${you} !"
echo $greeting 
echo -e "\n"
echo $greeting_1

# 使用单引号拼接
greeting_2='hello, '$you' !'
greeting_3='hello, ${you} !'
echo $greeting_2
echo $greeting_3

# 获取字符串长度
string="abcde"
echo "string="${string}
echo "string的长度为: ${#string}"

# 提取子字符串
string="hadoop is a big data tool"
echo "提取string的0到10的字符串： ${string:0:10}"

# 查找子字符串
:<<!
查找字符 i 或 o 的位置(哪个字母先出现就计算哪个)：
!
echo `expr index "$string" sd`


