package list;

public class LockDList extends DList {
    // public LockDList() {
    //     super(DList);
    // }
    // protected LockDListNode newNode(Boolean isLocked) {
    //     return new LockDListNode(isLocked);
    //   }
    // public void lockNode(DListNode node) { 
    //     if (isLocked) {
    //         return;
    //     }
    //     super.remove();
    // }
    // setter - set isLocked to true
    public void lockNode(DListNode node) {
        /**
         * It saves them the nuisance of having to cast every node they want to lock. 
         * Instead, it's your job to tak care of that cast (from DListNode to LockDListNode).
         */
        ((LockDListNode)node).isLocked = true;
    }
    // override newNode function - 和父类signiture保持一样 - 返回LockDListNode
    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, prev, next);
    }
    // override remove - 和父类参数保持一样
    public void remove(DListNode node) {
        if (node==null) return;
        if (((LockDListNode)node).isLocked) return;
        super.remove(node);
    }
}

