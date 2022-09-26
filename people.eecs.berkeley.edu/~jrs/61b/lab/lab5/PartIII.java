/***
 * Suppose a subclass inherits a "public static final" constant from a superclass,
and implements a Java interface that contains a "public static final" constant
with the same name.

(a)  Will Java compile the result?  Does it make any difference whether the
     constant in the superclass and the constant in the interface have the
     same value?
(b)  Write a main() method in the subclass that accesses the constant using the
     same name used in the superclass and the Java interface.  Will Java
     compile the result?  Does it make any difference whether the constant in
     the superclass and the constant in the interface have the same value?
(c)  Figure out how to modify your main() method so that it accesses and prints
     one of the two conflicting values.  (Look to the Lecture 9 notes for
     clues.)  Make sure your code compiles and runs without errors.
 */

class Superclass1 {
    public static final int a = 1;
}
/**
 * B
 */
interface B {
    public static final int a = 1;
}
class Subclass1 extends Superclass1 implements B{
    public static int a = 3;
}
public class PartIII {
    public static void main(String[] args) {
        Subclass1 sub = new Subclass1();
        System.out.println(sub.a);
        System.out.println(B.a);
        System.out.println(Superclass1.a);
        // System.out.println(((Superclass1)sub).a);
        // System.out.println(((B)sub).a);
    }
}