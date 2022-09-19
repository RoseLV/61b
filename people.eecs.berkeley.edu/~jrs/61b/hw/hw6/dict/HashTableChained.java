/* HashTableChained.java */

package dict;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  protected int size; // n
  protected int bucketSize; // N
  protected DList[] buckets;

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // size = Math.celling(sizeEstimate / 0.75);
    size = 0;
    bucketSize = searchNearestPrime(sizeEstimate * 2);
    buckets = new DList[bucketSize];
  }

  public int searchNearestPrime(int number) {
    boolean[] isPrime = new boolean[number + 1];
    for (int i = 0; i <= number; ++i) {
        isPrime[i] = true;
    }

    for (int i = 2; i * i <= number; ++i) {
        if (isPrime[i]) {
            for (int j = 2 * i; j <= number; j += i) {
                isPrime[j] = false;
            }
        }
    }

    int res = 2;
    for (int i = number; i > 2; --i) {
        if (isPrime[i]) {
            res = i;
            break;
        }
    }
    return res;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    size = 0;
    bucketSize = 103;
    buckets = new DList[bucketSize];
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // package protection??
    // if bucketSize < size要不要取最大值？？
    System.out.println("code" + code);
    return code % this.bucketSize;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    return size==0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // System.out.println(key.toString() + value.toString());

    // Entry entry = find(key);
    // if (entry != null) return entry;

    int index = compFunction(key.hashCode());
    Entry entry = new Entry();
    entry.key = key;
    entry.value = value;
    // System.out.println(index); //全是99

    DListNode node = findNode(key);

    // DList list = buckets[index];
    // list = list == null ? new DList() : list;
    if (buckets[index] == null) buckets[index] = new DList();

    // 如果有的话改
    if (node != null) {
      ((Entry)node.item).value = value;
    }
    // 没有的话插入
    else {
        size++;
        buckets[index].insertBack(entry);
        // System.out.println("Here else" + buckets[index]);
    }
    return entry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/
  public Entry find(Object key) {
    DListNode node = findNode(key);
    if (node == null) return null;
    return (Entry)node.item;
  }

  /**
   * 
   * @param key
   * @return DListNode
   */
  public DListNode findNode(Object key) {
    // System.out.println("### TESTING1  ###");

    int index = compFunction(key.hashCode());
    DListNode head;
    DList list = buckets[index];
    // System.out.println("### TESTING1  ###" + list);

    // list为空
    if (list == null) return null;

    try {
      // 找链表里那个节点
      head = list.front();
      while (head != null) {
        if (((Entry)head.item).key().equals(key)) {
          // System.out.println("### TESTING  ");
          return head;
        }
        head = list.next(head);
      }
      // 找不到那个节点
    }  catch (Exception e) {
      System.err.println(e);
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    int index = compFunction(key.hashCode());
    // 如果有的话改
    if (buckets[index] == null) return null;
    // Entry entry = find(key);
    DListNode node = findNode(key);
    buckets[index].remove(node);
    size--;
    return (Entry)node.item;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    for (int i = 0; i < bucketSize; i++) {
      buckets[i] = null; 
    }
    // buckets = new DList[bucketSize];
    size = 0;
  }
  public void printHist() {
    int collisions = 0;
    String str = "{";
    for (int i = 0; i < buckets.length; i++) {
      // System.out.println("buckets"+ buckets[i]);
        if (buckets[i] == null) {
            str += "0";
        } else {
            str += buckets[i].length();
            if (buckets[i].length() > 1) {
                collisions = collisions + buckets[i].length() - 1;
            }
        }
        str += ",";
    }

    System.out.println(str + "} buckets: " + buckets.length + " collisions: " 
            + collisions);
  }
}
