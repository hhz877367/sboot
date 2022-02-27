package com.hhz.model.datastruture;

public class DoubleLinkedList {

  public static void main(String[] args) {
    DoubleLinkedList list = new DoubleLinkedList();
    list.addFirst(1);
    list.addFirst(2);
    list.addFirst(3);
    list.addFirst(4);
    list.traverseForward();

  }


  //声明为public，方便存取
  //头指针
  public Node first;
  //尾指针
  public Node last;
  //链表中实际存储的数据的数目
  public int size;

  //初始化
  public DoubleLinkedList() {
    this.first = null;
    this.last = null;
    this.size = 0;
  }

  //得到链表容量
  public int size() {
    return size;
  }

  //判断链表是否为空
  public boolean isEmpty() {
    return size == 0;
  }

  //添加头节点
  public void addFirst(int data) {
    //创建新节点
    Node newNode = new Node(data);
    //判断链表是否为空
    if(isEmpty()) {
      last = newNode;
    }else {
      newNode.next = first;
      first.previous = newNode;

    }
    first = newNode;
    size ++;
  }

  //添加尾节点
  public void addLast(int data) {
    //创建新节点
    Node newNode = new Node(data);
    //判断链表是否为空
    if(isEmpty()) {
      first = newNode;
    }else {
      last.next = newNode;
      newNode.previous = last;
    }
    last = newNode;
    size ++;
  }

  //删除头结点,并返回头结点
  public Node deleteFirst() {
    if(isEmpty()) {
      System.out.println("链表为空!");
      return null;
    }
    Node temp = first;
    if(size == 1) {
      //如果链表中只有一个元素
      last = null;
    }else {
      first.next.previous = null;
    }
    first = first.next;
    size --;
    return temp;
  }

  //删除尾节点，并返回尾节点
  public Node deleteLast() {
    if(isEmpty()) {
      System.out.println("链表为空！");
      return null;
    }
    Node temp = last;
    if(size == 1) {
      //如果只有一个元素
      first = null;
    }else {
      last.previous.next = null;
    }
    last = last.previous;
    size --;
    return temp;
  }

  //将节点插入到指定值为key的节点后面
  public void insert(int key,int value) {
    //创建要插入的新节点
    Node newNode = new Node(value);
    //创建要插入节点位置上原来的节点
    Node current = first;
    if(isEmpty()) {
      System.out.println("没有值为" + key + "的值！");
      return;
    }
    while(current.data != key) {
      if(current == null) {
        System.out.println("没有值为" + key + "的值！");
        return;
      }
      //往下遍历
      current = current.next;
    }
    current.next.previous = newNode;
    newNode.next = current.next;
    current.next = newNode;
    newNode.previous = current;
    size ++;
  }

  //删除特定的节点,并返回该节点
  public Node deleteNode(int value) {
    if(isEmpty()) {
      System.out.println("没有值为" + value + "的值！");
    }
    //创建要删除节点
    Node current = first;
    while(current.data != value) {
      if(current == null) {
        System.out.println("没有值为" + value + "的值！");
      }
      //继续向下遍历
      current = current.next;
    }
    //如果要删除的节点为首节点
    if(current == first) {
      deleteFirst();
    }else if(current == last) {
      //如果要删除的节点为尾节点
      deleteLast();
    }else {
      current.previous.next = current.next;
      current.next.previous = current.previous;
    }
    size --;
    return current;
  }


  //正向遍历链表
  public void traverseForward() {
    Node current = first;
    while(current != null) {
      System.out.println(current.data);
      current = current.next;
    }
  }
  //反向遍历链表
  public void traverseBackwrad() {
    Node current = last;
    while(current != null) {
      System.out.println(current.data);
      current = current.previous;
    }
  }
}
class Node{
  //声明为public，方便存取
  //指向前一个节点
  public Node previous;
  //指向后一个节点
  public Node next;
  //数据域
  public int data;

  public Node(int data) {
    this.data = data;
  }
}
