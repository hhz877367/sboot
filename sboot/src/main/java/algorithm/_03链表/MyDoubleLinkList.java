package algorithm._03链表;

public class MyDoubleLinkList{
    private DoubleNode head;
    private  DoubleNode tail;
    private int size=0;

    public static void main(String[] args) {
        MyDoubleLinkList myDoubleLinkList = new MyDoubleLinkList();
     /*
       测试头插法
     myDoubleLinkList.insertHead(0);
        myDoubleLinkList.insertHead(1);
        myDoubleLinkList.insertHead(2);
        myDoubleLinkList.print();
        myDoubleLinkList.deleteHead();
        System.out.println("-----");
        myDoubleLinkList.print();
        myDoubleLinkList.deleteHead();
        System.out.println("-----");
        myDoubleLinkList.print();
        myDoubleLinkList.deleteHead();
        System.out.println("-----");
        myDoubleLinkList.print();*/
/*
       测试增删末尾
       myDoubleLinkList.insertTail(0);
        myDoubleLinkList.insertTail(1);
        myDoubleLinkList.insertTail(2);
        myDoubleLinkList.print();
        myDoubleLinkList.deleteTail();
        System.out.println("-----");
        myDoubleLinkList.print();
        myDoubleLinkList.deleteTail();
        System.out.println("-----");
        myDoubleLinkList.print();
        myDoubleLinkList.deleteTail();
        System.out.println("-----");
        myDoubleLinkList.print();*/
        myDoubleLinkList.inserNth(0,0);
        myDoubleLinkList.inserNth(1,1);
        myDoubleLinkList.inserNth(2,2);
        myDoubleLinkList.inserNth(3,3);
        myDoubleLinkList.inserNth(4,2);
        myDoubleLinkList.inserNth(4,2);
        myDoubleLinkList.print();
        myDoubleLinkList.deleteNth(0);
        System.out.println("------");
        myDoubleLinkList.print();

        myDoubleLinkList.deleteNth(3);
        System.out.println("------");
        myDoubleLinkList.print();

    }

    public void insertHead(int value){
        DoubleNode newNode = new DoubleNode(value);
        if(head==null){
            head=newNode;
            tail=newNode;
            size++;
        }else {
            head.pre=newNode;
            newNode.next=head;
            size++;
        }
        head = newNode;
    }

    public  void deleteHead(){
        if(size!=0){
            if(head.next==null){
                tail=null;
                head=null;
                size--;
                return;
            }
            head=head.next;
            head.pre=null;
            size--;
        }
    }

    public void insertTail(int value){
        DoubleNode newNode = new DoubleNode(value);
        if(tail==null){
            head=newNode;
            tail=newNode;
            size++;
        }else {
            newNode.pre=tail;
            tail.next=newNode;
            size++;
        }
        tail=newNode;
    }

    public  void deleteTail(){
        if(size!=0){
            if(tail.pre==null){
                head=null;
                size--;
                return;
            }
            tail=tail.pre;
            tail.next=null;
            size--;
        }
    }



    public  void inserNth(int value,int position){
        if(position==0){
            insertHead(value);
            return;
        }
        if(position==size){
            insertTail(value);
            return;
        }
        if(position>size){
            System.out.println("插入失败");
            return;
        }
        DoubleNode node = node(position);
        DoubleNode newNode = new DoubleNode(value);
        DoubleNode prev = node.pre;
        //先处理与后节点的关系
        newNode.next=node;
        node.pre=newNode;


        //在处理与前节点的关系
        prev.next=newNode;
        node.pre=prev;

        size++;
    }

    public  void deleteNth(int position){
        if(position>=size || position<0){
            System.out.println("没有对应下标");
        }
        if(position==0){
            deleteHead();
            return;
        }
        if(position==size-1){
            deleteTail();
            return;
        }
        DoubleNode node = node(position);
        node.pre.next=node.next;
        node.next.pre=node.pre;
        size--;
    }
    private DoubleNode node(int index) {
        // 不用从头开始，可以从头也可以从尾
        // 1.如果 index 在链表前半部分
        if (index < (size/2)) {  // size >> 1 一半
            // 从前向后走
            DoubleNode node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {   // 2.如果 index 在链表后半部分
            // 从后向前走
            DoubleNode node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.pre;
            }
            return node;
        }
    }
    public void print(){
        if(size==0){
            System.out.println("空");
            return;
        }
        DoubleNode cur=head;
        for(int i=0;i<size;i++){
            System.out.println(cur.value);
            if(cur!=null){
                cur=cur.next;
            }
        }
    }






}

class  DoubleNode{
    int value;
    DoubleNode next;
    DoubleNode pre;
    public DoubleNode(int value){
        this.value=value;
    }

}
