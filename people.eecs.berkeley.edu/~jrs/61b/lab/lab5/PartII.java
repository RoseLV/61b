/**
 * Suppose a subclass inherits a method implementation from a superclass, and
implements a Java interface (that's the "interface" keyword) that contains
a method with the same name and prototype.

(a)  Will Java compile the result?
A: yes
(b)  What if the method declaration in the interface has a different return
     type?
A: need to change Subclass return type SAME as the INTERFACE
(c)  What if the method declaration in the interface has the same return type,
     but a signature with a different parameter type?
A: need to change Subclass return type  SAME as the INTERFACE
(d)  What if the method declaration in the interface has the same return type,
     and the same number of parameters and parameter types, but those
     parameters have different names?
A: I think this works fine
 */

interface A {
    int doNothing(int a);
}
class Superclass {
    public int doNothing(String b) {return 1;};
}
class Subclass extends Superclass implements A {
    public int doNothing(int b) {
        return b;
    }
}


public class PartII {
    public static void main(String[] args) {
        // int c = A.x; // 1
        // int d = B.x; // 2
        // System.out.println("ccccc" + c);
    }
}