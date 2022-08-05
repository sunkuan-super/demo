#!/bin/bash

# grep可以接收标准输入
# shellcheck disable=SC2002
cat /etc/hosts | grep hosts

echo "hello" | echo
# echo不能接收标准输入
echo "hello" | xargs echo

echo "a b c" | xargs mkdir

# 4、-d参数与分隔符
# 默认情况下，xargs将换行符和空格作为分隔符,把标准输入分解成一个个命令行参数
echo "a b c"| xargs mkdir
# 这个会创建三个目录，因为xargs将a b c分解成三个命令行参数，执行mkdir a b c。
# -d 参数可以更改分隔符

echo -e -n "a\tb\tc" | xargs -d "\t" mkdir
# 上边的命令指定制表符 \t 作为分隔符,所以 a\tb\tc 就转换成了三个命令行参数。 echo命令的 -e 参数表示解释转义字符 -n意思是echo打印出来的东西没有换行符

# 5、 -p 参数， -t 参数
# 使用xargs命令以后,由于存在转换参数过程，有时需要确认一下到底执行的是什么命令 -p 参数打印出要执行的命令,询问用户是否要执行
echo 'one two three' | xargs -p touch

# 上面的命令执行以后,会打印出要最终要执行的命令，让用户确认。用户输入y以后(大小写都可以),才会真正执行。

# -t 参数则是打印出最终要执行的命令，然后直接执行，不需要用户确认
echo 'one two three' | xargs -t rm

# 6、-o 参数与 find 命令
# 由于xargs默认将空格作为分隔符,所以不太适合适合处理没文件名，因为文件名可能包含空格

# find 命令有个特别的参数 -print0,指定输出的文件列表以null分隔。然后，xargs命令的-0参数表示用null当作分隔符。

# 7、-L 参数
# 如果标准输入包含多行，-L 参数指定多少行作为一个命令行参数

xargs find -name
"*.txt"
"*.md"
find: paths must precede expression: *.csv
Usage: find [-H] [-L] [-P] [-Olevel] [-D help|tree|search|stat|rates|opt|exec] [path...] [expression]

# 使用 -L 参数,指定每行作为一个命令行参数，就不会报错

[spark@slave subtmp]$ xargs -L 1 find -name
"*.csv"
./1.csv
./2.csv
"*.csv"
./1.csv
./2.csv

[spark@slave subtmp]$ echo -e "a\nb\nc"|xargs -L 1 echo
a
b
c

# -n 参数
echo "a b c d"|xargs -n 3 echo

[spark@slave subtmp]$ echo "a b c d"|xargs -n 3 echo
a b c
d


echo {0..9}|xargs -n 2 echo
[spark@slave subtmp]$ echo {0..9}|xargs -n 2 echo
0 1
2 3
4 5
6 7
8 9

# -I参数，如果 xargs 要将命令行参数传给多个命令，可以使用 -I 参数, -I 指定每一项命令行参数的替代字符串。
[spark@slave subtmp]$ more vv.txt
a
b
c

[spark@slave subtmp]$ cat vv.txt |xargs -I BB sh -c "echo BB;mkdir BB"
a
b
c

# 上边的代码中，vv.txt是一个三行的文本文件。我们希望对每一项命令行参数,执行两个命令(echo和mkdir)，使用 -I BB表示BB是命令行参数的替代字符串.
# 执行命令时，具体的参数会替代掉echo BB;mkdir BB里面的两个BB

# 10、--max-procs参数
# xargs默认只用一个进程执行命令。如果命令要执行多次，必须等上一次执行完，才能执行下一次
# --max-procs参数指定同事用多少个进城并行执行命令。--max-procs 2 表示同时最多使用两个进城，--max-procs 0 表示不限制进程数。
docker ps -q | xargs -n 1 --max-procs 0 docker kill

# 上边的命令表示，同时关闭尽可能多的Docker容器，这样运行速度会快很多。























