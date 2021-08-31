#!/bin/bash

# 定义数组
array_name=(value0 value1 value2 value3)

# 或者
array_name=(
value0
value1
value2
value3
)

# 还可以单独定义数组的各个分量
array_name[0]=value0
array_name[1]=value1
array_name[2]=value2

# 读取数组
echo "第一个元素为： ${array_name[0]}"
echo "第二个元素为:  ${array_name[1]}"
# 使用@可以获取数组的所有元素
echo "数组的元素为：${array_name[@]}"
echo "数组的元素为：${array_name[*]}"

# 获取数组的长度
length=${#array_name[@]}
echo "数组的长度为: $length"
echo "数组的长度为: ${#array_name[*]}"

