package leetcode147;

public class Solution {
    /**
     * 再创建一个ListNode dummyhead, 从head 链表中取元素，
     * 插入dummyhead, 从头遍历dummyhead的链表，找到比当前元素更大的元素，
     * 插入到这个元素的前面。如果遍历到最后dummyhead中没有元素大于当前元素，
     * 则将该元素接在链表后面。
     * */
    public ListNode insertionSortList(ListNode head) {
        if(head== null || head.next== null)
            return head;
        ListNode dummyhead = new ListNode(0);
        ListNode p1 =  head;
        ListNode p2 =  dummyhead;
        while(p1!=null){
            Boolean flag = false;
            while(p2.next!=null){
                if(p1.val<p2.next.val){
                    ListNode temp = new ListNode(p1.val);
                    temp.next = p2.next;
                    p2.next =temp;
                    flag =true;
                    break;
                }
                p2=p2.next;
            }
            if(flag == false){
                p2.next =new ListNode(p1.val);
            }
            p2 = dummyhead;
            p1= p1.next;
        }
        return dummyhead.next;
    }
}
