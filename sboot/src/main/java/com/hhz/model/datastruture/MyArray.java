package com.hhz.model.datastruture;


public class MyArray {
  private int index;
  private int size;
  private int data[];

  public static void main(String[] args) {

    int[][] arr=new int[2][3];
    MyArray list = new MyArray(10);
    list.add(1,2);
    list.add(2,3);
    list.add(3,3);
    list.add(4,3);
    for(int i=0;i<list.size;i++){
      System.out.println(list.get(i));
    }



  }


  public MyArray(int size){
    this.data= new int[size];
    this.size=size;
    index=0;
  }
  private  void add(int loc,int n){
    if(index++<size){
      for(int i=size-1;i<loc;i--){
        data[i]=data[i-1];
      }
      data[loc]=n;
    }
  }
  private void delete(int loc){
      for(int i=loc;i<size;i--){
          if(i!=size-1){
            data[i]=data[i-1];
          }else {
            data[i]=-1;
          }
      }
      index--;
  }



  private  boolean update(int index,int value){
    if(index>0 && index <size){
      this.data[index]=value;
      return true;
    }else {
      return false;
    }
  }
  private int get(int index){
    return data[index];
  }


}
