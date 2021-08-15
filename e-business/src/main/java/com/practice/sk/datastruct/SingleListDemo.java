package com.practice.sk.datastruct;

/**
 * @Title: SingleListDemo
 * @Package: com.practice.sk.datastruct
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/8/24 - 13:20
 */
public class SingleListDemo {
    public static void main(String[] args) {
        Node nodeA = new Node("a");
        Node nodeB = new Node("b");
        Node nodeC = new Node("c");
        Node nodeD = new Node("d");

        nodeA.setNext(nodeB);
        nodeB.setNext(nodeC);
        nodeC.setNext(nodeD);

        nodeA = reverse(nodeA);


        while (null != nodeA){
            System.out.println(nodeA.getData());
            nodeA = nodeA.getNext();
        }
    }

    public static Node reverse(Node head){
        if(head == null || head.getNext() == null){
            return head;
        }

        System.out.println( "reverse " + head.getData());
        Node reHead = reverse(head.getNext());
        System.out.println("执行完成");
        head.getNext().setNext(head);
        head.setNext(null);

        return reHead;
    }
}
