package algorithm._03链表;

public class MyLinkList {
    private ListNode head;
    private int size=0;

    public static void main(String[] args) {
        MyLinkList myLinkList = new MyLinkList();
        for(int i=0;i<5;i++){
            myLinkList.inserNth(i,i);
        }
        myLinkList.inserNth(6,5);
        myLinkList.print();
        //测试删除尾部
        myLinkList.deleteNth(4);
        System.out.println("-------");
        myLinkList.print();
    }

    public void insertHead(int value){
        ListNode node = new ListNode(value);
        node.next=head;
        head=node;
        size++;
    }

    public  void deleteHead(){
        if(head.next!=null){
            head=head.next;
            size--;
        }
    }

    public  void inserNth(int value,int position){
        if(position==0){
            insertHead(value);
        }else {
            ListNode cur=head;
            //遍历到要插入位置的前一位
            for(int i=1;i<position;i++){
                cur=cur.next;
            }
            ListNode nowNode = new ListNode(value);
            nowNode.next=cur.next; //先指向后一个节点
            cur.next=nowNode;  //在断开之前连接，执行新节点
            size++;
        }
    }
    public  void deleteNth(int position){
        if(position==0){
            deleteHead();
        }else {
            ListNode cur=head;
            for(int i=1;i<position;i++){
                cur=cur.next;
            }
            cur.next=cur.next.next;
            size--;
        }
    }

    public void print(){
        ListNode cur=head;
        for(int i=0;i<size;i++){
            System.out.println(cur.value);
            cur=cur.next;
        }
    }
}

class  ListNode{
    int value;
    ListNode next;
    public ListNode(int value){
        this.value=value;
    }

}
