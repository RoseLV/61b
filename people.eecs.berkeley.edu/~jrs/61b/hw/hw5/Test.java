import list.*;

public class Test {
    public static void main(String[] args) {
        List l = new DList();
        l.insertFront(Integer.valueOf(1));
        l.insertFront(Integer.valueOf(2));
        l.insertFront(Integer.valueOf(3));
        l.insertFront(Integer.valueOf(4));
        l.insertBack(Integer.valueOf(100));

        System.out.println(l);

        ListNode node = l.front();
        ListNode node2 = l.back();
        try {
            node.insertAfter(Integer.valueOf(100));
            node2.insertBefore(Integer.valueOf(-5));
            ListNode node3 = node2.prev();
            node3.insertBefore(Integer.valueOf(-10));
            node3.remove();

        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
        }

        System.out.println(l.toString());
        // System.out.println(node2.toString());
    }
}
