/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
  // protected int size;
  // protected DListNode head;
  List elements;
  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/
  // protected DListNode newNode(Object item, DList list,
  //                             DListNode prev, DListNode next) {
  //   return new DListNode(item, list, prev, next);
  // }
  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
    // size = 0;
    // head = newNode(null, null, null, null);
    // head.next = head;
    // head.prev = head;
    elements = new DList();
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // return size;
    return elements.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    if (elements.isEmpty()) {
      elements.insertFront(c);
      return;
    }
    try {
      DListNode copy = (DListNode)elements.front();
      // 1. 循环list，break条件是循环完一圈
      while (copy.isValidNode()) {
        int res = c.compareTo(copy.item());
        // 2. 等于，去重
        if (res == 0) {
          return;
        }
        // 3. 小于插入前面
        if (res < 0) {
          copy.insertBefore(c);
          return;
        }
        // 4. 大于指针往后面移动，继续循环
        copy = (DListNode)copy.next();
      }
      elements.insertBack(c);
    } catch (InvalidNodeException e) {
      System.err.println(e);
    }
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    try {
      ListNode copy = elements.front();
      ListNode copy2 = s.elements.front();

      while (copy.isValidNode() && copy2.isValidNode()) {
        int res = ((Comparable)copy.item()).compareTo(copy2.item());
        if (res==0) {
          copy = copy.next();
          copy2 = copy2.next();
        }
        else if (res<0) {
          copy = copy.next();
        }
        else if (res>0) {
          copy.insertBefore(copy2.item());
          // copy = copy.next();
          copy2 = copy2.next();
        }
      }

      while (copy2.isValidNode()) {
        elements.insertBack(copy2.item());
        copy2 = copy2.next();
      }
    } catch (InvalidNodeException e) {
      System.err.println(e);
    }

  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s. ==> ONLY modify elements
   *  DO NOT CONSTRUCT ANY NEW NODES. ==> 
   *  DO NOT ATTEMPT TO COPY ELEMENTS. ==> as return void 
   **/
  public void intersect(Set s) {
    try {
      ListNode copy = elements.front();
      ListNode copy2 = s.elements.front();

      while (copy.isValidNode() && copy2.isValidNode()) {
        int res = ((Comparable)copy.item()).compareTo(copy2.item());
        if (res==0) {
          copy = copy.next();
          copy2 = copy2.next();
        }
        else if (res < 0) {
          copy = copy.next();
          copy.prev().remove();
        }
        else if (res>0) {
          copy2 = copy2.next();
        }
      }
      while (copy.isValidNode()) {
        ListNode temp = copy;
        copy = temp.next();
        temp.remove();
      }
    } catch (InvalidNodeException e) {
      System.err.println(e);
    }

  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    String res = "{";
    ListNode node = elements.front();
    while (node.isValidNode()) {
        try {
            res += node.item();
            res += ",";
            node = node.next();
        } catch(InvalidNodeException e) {
            System.err.println(e);
        }
    }
    return res + "}";
  }

  public static void main(String[] argv) {
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    s.insert(new Integer(1));
    s.insert(new Integer(99));
    s.insert(new Integer(6));
    s.insert(new Integer(100));

    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    s3.insert(new Integer(7));
    System.out.println("Set s3 = " + s3);

    // s.union(s2);
    // System.out.println("After s.union(s2), s = " + s);
    // s.union(s3);
    // System.out.println("After s.union(s3), s = " + s);

    s2.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s2);
    // s.intersect(s3);
    // System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
  }
}
