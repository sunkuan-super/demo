package com.practice.sk.datastruct;

/**
 * @Title: Node
 * @Package: com.aspirecn.sk.datastruct
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/8/24 - 12:47
 */
public class Node {
    private String data;

    private Node next;

    Node(String data){
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
