解读：

1个生产者，多个消费者
每一个消费者都有自己的一个队列
生产者没有将消息直接发送到队列，而是发送到了交换机 exchange
每个队列都要绑定到交换机
生产者发送的消息，经过交换机，到达队列，实现一个消息被多个消费者获取的目的