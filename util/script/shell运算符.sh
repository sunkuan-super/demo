#!/bin/bash
:<<!
hell 和其他编程语言一样，支持多种运算符，包括：

    算数运算符
    关系运算符
    布尔运算符
    字符串运算符
    文件测试运算符

原生bash不支持简单的数学运算，但是可以通过其他命令来实现，例如 awk 和 expr，expr 最常用。

expr 是一款表达式计算工具，使用它能完成表达式的求值操作。 
!

# 表达式和运算符之间要有空格，例如 2+2 是不对的，必须写成 2 + 2，这与我们熟悉的大多数编程语言不一样。
# 完整的表达式要被 ` ` 包含，注意这个字符不是常用的单引号，在 Esc 键下边

echo -e "\n***********************  算数运算符  ****************************\n"

val=`expr 2 + 3`
echo "2+3的和是： $val"

a=10
b=20
echo -e "a=$a\n b=$b"
echo "a+b= `expr $a + $b`"
echo "a-b= `expr $a - $b`"
echo "a*b= `expr $a \* $b`"
echo "b/a= `expr $b / $a`"
echo "b%a= `expr $b % $a`"

if [ $a == $b ]
then
 echo "a等于b"
fi

if [ $a != $b ]
then
 echo "a不等于b"
fi

# 关系运算符
:<<!
-eq 	检测两个数是否相等，相等返回 true
-ne 	检测两个数是否不相等，不相等返回 true
-gt 	检测左边的数是否大于右边的，如果是，则返回 true
-lt 	检测左边的数是否小于右边的，如果是，则返回 true
-ge 	检测左边的数是否大于等于右边的，如果是，则返回 true
-le 	检测左边的数是否小于等于右边的，如果是，则返回 true
!

echo -e "\n********************   系运算符   ********************************\n"
a=10
b=20
echo "a=$a"
echo "b=$b"

if [ $a -eq $b ]
then
 echo "$a -eq $b: a等于b"
else
 echo  "$a -eq $b: a不等于b"
fi

if [ $a -ne $b ]
then
 echo "$a -ne $b: a不等于b"
else
 echo "$a -ne $b: a等于b"
fi

if [ $a -gt $b ]
then
 echo "$a -gt $b: a大于b"
else
 echo "$a -gt $b: a不大于b"
fi

if [ $a -lt $b ]
then
 echo "$a -lt $b: a小于b"
else
 echo "$a -lt $b: a不小于b"
fi

if [ $a -ge $b ]
then
 echo "$a -ge $b: a大于或等于b"
else
 echo "$a -ge $b: a小于b"
fi

if [ $a -le $b ]
then
 echo "$a -le $b: a小于或等于b"
else
 echo "$a -le $b: a大于b"
fi


# 布尔运算符
echo -e "\n*******************   布尔运算符    ***************************\n"

a=10
b=20
echo "a=$a"
echo "b=$b"

# 非
if [ $a != $b ]
then 
 echo "$a != $b : a 不等于 b"
else
 echo "$a == $b: a 等于 b"
fi

# 与
if [ $a -lt 100 -a $b -gt 10 ]
then
 echo "$a 小于 100 且 $b 大于 15 : 返回 true"
else 
 echo "$a 小于 100 且 $b 大于 15 : 返回 false"
fi

# 或
if [ $a -lt 100 -o $b -gt 100 ]
then
   echo "$a 小于 100 或 $b 大于 100 : 返回 true"
else
   echo "$a 小于 100 或 $b 大于 100 : 返回 false"
fi


# 逻辑运算符
echo "a=$a"
echo "b=$b"

if [[ $a -lt 100 && $b -lt 100 ]]
then
 echo "返回true"
else
 echo "返回false"
fi

if [[ $a -lt 100 || $b -lt 100 ]]
then
 echo "返回true"
else
 echo "返回false"
fi

if [[ $a == $b ]]
then
 echo "=="
else
 echo "!="
fi

# 字符串运算符
:<<!
= 	检测两个字符串是否相等，相等返回 true
!= 	检测两个字符串是否不相等，不相等返回 true
-z 	检测字符串长度是否为0，为0返回 true
-n 	检测字符串长度是否不为 0，不为 0 返回 true
$ 	检测字符串是否为空，不为空返回 true
!

echo -e "=       检测两个字符串是否相等，相等返回 true\n"
echo -e "!=      检测两个字符串是否不相等，不相等返回 true\n"
echo -e "-z      检测字符串长度是否为0，为0返回 true\n"
echo -e "-n      检测字符串长度是否不为 0，不为 0 返回 true\n"
echo -e "$       检测字符串是否为空，不为空返回 true\n"


echo "******************   字符串运算符    *************************"
a="abc"
b="efg"
echo "a=$a"
echo "b=$b"

if [ $a = $b ]
then
 echo "判断$a = $b : a 等于 b"
else
 echo "判断$a = $b: a 不等于 b"
fi

if [ $a != $b ]
then
   echo "判断$a != $b : a 不等于 b"
else
   echo "判断$a != $b: a 等于 b"
fi

if [ -z $a ]
then 
 echo "判断 -z $a : 字符串长度为 0"
else
 echo "判断 -z $a : 字符串长度不为 0"
fi

if [ -n $a ]
then
 echo "判断 -n $a : 字符串长度不为 0"
else
 echo "判断 -n $a : 字符串长度为 0"
fi

if [ $a ]
then
 echo "$a : 字符串不为空"
else
 echo "$a : 字符串为空"
fi

# shell中对变量的值添加单引号，双引号和不添加的区别：对类型来说是无关的，即不是添加了引号就变成了字符串类型
# 单引号不对相关量进行替换，如不对$符号解释成变量引用，从而用对应变量的值替代，双引号则会进行替代
A="$1"
B="$2"
echo "原始值：A=$A,B=$B"

# 判断字符串是否相等
if [ "$A" = "$B" ]
then
 echo "[ = ]"
fi

if [ "$A" == "$B" ]
then
 echo "[ == ]"
fi




# 文件测试运算符
echo -e "********************  文件测试运算符  *******************************\n"
:<<!
-b file 	检测文件是否是块设备文件，如果是，则返回 true。 	[ -b $file ] 返回 false。
-c file 	检测文件是否是字符设备文件，如果是，则返回 true。 	[ -c $file ] 返回 false。
-d file 	检测文件是否是目录，如果是，则返回 true。 	[ -d $file ] 返回 false。
-f file 	检测文件是否是普通文件（既不是目录，也不是设备文件），如果是，则返回 true。 	[ -f $file ] 返回 true。
-g file 	检测文件是否设置了 SGID 位，如果是，则返回 true。 	[ -g $file ] 返回 false。
-k file 	检测文件是否设置了粘着位(Sticky Bit)，如果是，则返回 true。 	[ -k $file ] 返回 false。
-p file 	检测文件是否是有名管道，如果是，则返回 true。 	[ -p $file ] 返回 false。
-u file 	检测文件是否设置了 SUID 位，如果是，则返回 true。 	[ -u $file ] 返回 false。
-r file 	检测文件是否可读，如果是，则返回 true。 	[ -r $file ] 返回 true。
-w file 	检测文件是否可写，如果是，则返回 true。 	[ -w $file ] 返回 true。
-x file 	检测文件是否可执行，如果是，则返回 true。 	[ -x $file ] 返回 true。
-s file 	检测文件是否为空（文件大小是否大于0），不为空返回 true。 	[ -s $file ] 返回 true。
-e file 	检测文件（包括目录）是否存在，如果是，则返回 true。 	[ -e $file ] 返回 true。
!

echo -e "-b file 	检测文件是否是块设备文件，如果是，则返回 true。 	[ -b $file ] 返回 false。"
echo -e "-c file 	检测文件是否是字符设备文件，如果是，则返回 true。 	[ -c $file ] 返回 false。"
echo -e "-d file 	检测文件是否是目录，如果是，则返回 true。 	[ -d $file ] 返回 false。"
echo -e "-f file 	检测文件是否是普通文件（既不是目录，也不是设备文件），如果是，则返回 true。 	[ -f $file ] 返回 true。"
echo -e "-g file 	检测文件是否设置了 SGID 位，如果是，则返回 true。 	[ -g $file ] 返回 false。"
echo -e "-k file 	检测文件是否设置了粘着位(Sticky Bit)，如果是，则返回 true。 	[ -k $file ] 返回 false。"
echo -e "-p file 	检测文件是否是有名管道，如果是，则返回 true。 	[ -p $file ] 返回 false。"
echo -e "-u file 	检测文件是否设置了 SUID 位，如果是，则返回 true。 	[ -u $file ] 返回 false。"
echo -e "-r file 	检测文件是否可读，如果是，则返回 true。 	[ -r $file ] 返回 true。"
echo -e "-w file 	检测文件是否可写，如果是，则返回 true。 	[ -w $file ] 返回 true。"
echo -e "-x file 	检测文件是否可执行，如果是，则返回 true。 	[ -x $file ] 返回 true。"
echo -e "-s file 	检测文件是否为空（文件大小是否大于0），不为空返回 true。 	[ -s $file ] 返回 true。"
echo -e "-e file 	检测文件（包括目录）是否存在，如果是，则返回 true。 	[ -e $file ] 返回 true。"

file=$1

echo "$file"

# 判读是否为可读文件
if [ -r $file ]
then 
 echo "文件可读"
else
   echo "文件不可读"
fi

# 判读是否为可写文件
if [ -w $file ]
then
 echo "文件可写"
else
 echo "文件不可写"
fi


# 判断是否为可执行文件
if [ -x $file ]
then
 echo "文件可执行"
else
 echo "文件不可以执行"
fi

# 判断是否为文件
if [ -f $file ]
then
 echo "文件为普通文件"
else
 echo "文件为特殊文件"
fi

# 判断是否为目录
if [ -d $file ]
then
   echo "文件是个目录"
else
   echo "文件不是个目录"
fi

# 判断文件是否为空
if [ -s $file ]
then
   echo "文件不为空"
else
   echo "文件为空"
fi

# 判断文件是否存在
if [ -e $file ]
then
   echo "文件存在"
else
   echo "文件不存在"
fi

