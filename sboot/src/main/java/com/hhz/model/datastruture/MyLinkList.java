package com.hhz.model.datastruture;


public class MyLinkList {
    private LikeNode head;
    private int size=0;

  public static void main(String[] args) {
    MyLinkList linkList = new MyLinkList();
    for(int i=0;i<=100;i++){
      linkList.insertNth(i+100,i);
    }
    linkList.priint();

  }
    public void priint(){
      LikeNode cru=head;
      while (cru.next!=null){
        System.out.println(cru.value);
        cru=cru.next;
      }
      System.out.println(cru.value);
    }

    public void insertHead(int value){
      LikeNode newNode = new LikeNode(value);
      // 把新结点的next--指向原始链表的头部
      newNode.next=head;
      //把新结点当作头部
      head=newNode;

    }
    //positon 代表插入的位置
    public void insertNth(int data,int positon){
      if(positon==0){
        insertHead(data);
      }else {
        LikeNode cur=head;
        //找到插入的前一个位置元素
        for(int i=1;i<positon;i++){
            cur=cur.next;
        }
        LikeNode newNode = new LikeNode(data);
        newNode.next=cur.next; //把下一个对象的引用赋值给当前对象的Next
        cur.next=newNode;  // 把当前对象赋值给上一个对象的next
      }
    }
    public void deleteHead(){
        head=head.next;
    }

    public void deleteNth(int positon){
      if(positon==0){
        deleteHead();
      }else {
        LikeNode cur=head;
        //找到插入的前一个位置元素
        for(int i=1;i<positon;i++){
          cur=cur.next;
        }
        cur.next=cur.next.next;  // 把当前对象赋值给上一个对象的next
      }
    }
}




class LikeNode{

  int value;
  LikeNode next;

  LikeNode(int value){
    this.value=value;
    this.next=null;
  }

}
