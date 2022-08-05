#!/bin/bash

# 1、vi命令 查找
:<<!
  /pattern<Enter> ：向下查找pattern匹配字符串
  ?pattern<Enter> ：向上查找pattern匹配字符串

  使用了查找命令之后，使用如下两个键快速查找：
  n：按照同一方向继续查找
  N：按照反方向查找

  pattern是需要匹配的字符串，例如：
  /name<Enter>      #查找name
  /name<Enter>    #查找name单词（注意前后的空格）
  除此之外，pattern还可以使用一些特殊字符，包括（/、^、$、*、.），其中前三个这两个是vi与vim通用的，“/”为转义字符。
  /^name<Enter>    #查找以name开始的行
  /name$<Enter>    #查找以name结束的行
  //^name<Enter>    #查找^name字符串
!

# 2、vi下的替换
:<<!
  :s/sunkuan/king         #替换当前行第一个 sunkuan 为 king
  :s/sunkuan/king/g       #替换当前行所有 sunkuan 为 king
  :n,$s/sunkuan/king/     #替换第 n 行开始到最后一行中每一行的第一个 sunkuan 替换为king
  :n,$s/ sunkuan/king/g   #替换第 n 行开始到最后一行中每一行所有 name 为 title
  # (n为数字，若n为.,表示从当前行到最后一行)

  :%s/name/title/        #（等同于 :g/name/s//title/） 替换每一行的第一个 name 为 title
  :%s/name/title/g       #（等同于 :g/name/s//title/g） 替换每一行中所有 name 为 title
                         #可以使用 #或+ 作为分隔符，此时中间出现的 / 不会作为分隔符
  :s#name/#title/#         替换当前行第一个 name/ 为 title/
  :%s+/oradata/apras/+/user01/apras1+ （
  使用+ 来 替换 / ）： /oradata/apras/替换成/user01/apras1/
!

# 3、sed和grep配合
:<<!
  # shellcheck disable=SC2006
  # shellcheck disable=SC2006

  命令：sed -i s/sunkuan/king/g $(grep sunkuan -rl --include="*.txt" ./)

  作用：将当前目录(包括子目录)中所有的txt文件中的 sunkuan 字符串替换为king字符串。其中，
  -i 表示操作的是文件，$()括起来的grep命令，表示将grep命令的结果作为操作文件。
  s/sunkuan/king/表示查找sunkuan替换为king,后面跟g表示一行中有多个sunkuan的时候，都替换而不是仅仅只替换第一个

  另外，如果不需要查找子目录，仅需要在当前目录替换，用sed命令就可以了，sed -i s/sunkuan/king/g ./*.txt

!

# 4、find命令查找和替换
:<<!
  命令格式： find -name '要查找的文件名'|xargs perl -pi -e 's|被替换的字符串|替换后的字符串|g'

  # 查找替换当前目录下包含字符串进行替换
  find -name '*.txt'|xargs perl -pi -e 's|sunkuan|king|g'
  # 递归查找替换
  find . -type f -name '*.html'|xargs perl -pi -e 's|sunkuan|king|g'
!

:<<!
  工具
  https://www.cnblogs.com/iuskye/p/shell-tools.html
!
