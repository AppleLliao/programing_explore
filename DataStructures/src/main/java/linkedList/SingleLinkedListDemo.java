package main.java.linkedList;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args){
        //创建节点
        HeroNode heroNode1=new HeroNode(1,"宋江","及时雨");
        HeroNode heroNode2=new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode heroNode3=new HeroNode(3,"吴用","智多星");
        HeroNode heroNode4=new HeroNode(4,"林冲","豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);
        /*singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode2);*/


        /*System.out.println("测试逆序打印单链表，没有改变链表的结构~~~");
        reversePrint(singleLinkedList.getHead());*/

        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.list();
    }

    //利用栈这个数据结构，将各个节点压入栈中，实现逆序打印
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return; //空链表，不能打印
        }

        Stack<HeroNode> stack= new Stack<HeroNode>();
        HeroNode cur = head.next;
        while(cur!=null){
            stack.push(cur);
            cur = cur.next; //cur后移，这样就可以压入下一个节点
        }

        //将栈中的节点进行打印，pop出栈
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }



}

class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int no,String name,String nickName){
        this.no=no;
        this.name=name;
        this.nickName= nickName;
    }
    //为了显示方法，我们重新toString


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", next="  +
                '}';
    }
}

//定义singleListLinkedList 管理我们的英雄
class SingleLinkedList{
    //先初始化一个节点，头节点不要动，不存放具体的数据
    private HeroNode head= new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead(){
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next 指向新的节点
    public void add(HeroNode heroNode){

        //因为head节点不能动，因此要一个辅助变量temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true){
            //找到链表的最后
            if(temp.next==null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //将最后这个节点指向新节点
        temp.next=heroNode;
    }

    //显示链表[遍历]
    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
        }

        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp= head.next;
        while(true){
            //判断是否到链表最后
            if(temp == null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp 后移
            temp = temp.next;
        }
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //如果有这个排名，则添加失败，并给出提示
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此我们任然通过一个辅助指针来帮助找到添加的位置
        //因为单链表，因为我们找的temp 是位于添加位置的前一个结点，否则插入不了
        HeroNode temp= head;
        boolean  flag= false; //flag标志添加的编号是否存在，默认为false
        while(true){
            if(temp.next==null){
                break;//说明temp已经在链表最后
            }
            if(temp.next.no>heroNode.no){
                //位置找到，就在temp的后面插入
                break;
            }else if(temp.next.no == heroNode.no){
                //说明要添加的节点已经存在
                flag = true;
                break;
            }
            temp=temp.next; //节点后移，遍历当前链表
        }

        //判断flag的值
        if(flag){//不能添加说明编号已经存在
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n",heroNode.no);
        }else{
            //插入到链表中，temp的后面
            heroNode.next  =temp.next;
            temp.next=heroNode;
        }
    }

    //修改节点信息，根据no编号来修改，即no编号不能改
    //说明
    //1.根据new HeroNode 的no来修改即可
    public void update(HeroNode newHeroNode){
        //判断是否空
        if(head.next==null){
            System.out.println("链表为空");
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp=head.next;
        boolean flag=false; //表示是否找到该节点
        while(true){
            if(temp==null){
                break;//已经遍历完链表
            }

            if(temp.no == newHeroNode.no){
                //找到
                flag=true;
                break;
            }
            temp=temp.next;
        }

        //根据flag判断是否找到要修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.no=newHeroNode.no;

        }else{
            System.out.printf("没有找到编号%d的节点，不能修改\n",newHeroNode.no);
        }
    }

}
