package com.hhz.model.datastruture;

public class DoubleLink {  //双向链表

}

class DoubleNode{

  int value;
  DoubleNode next;
  DoubleNode pre;

  DoubleNode(int value){
    this.value=value;
    this.next=null;
    this.pre=null;
  }

}