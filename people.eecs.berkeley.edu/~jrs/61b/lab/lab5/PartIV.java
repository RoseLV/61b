/**
 * Consider a subclass that has a method that overrides a method with the same
prototype in its superclass.

(a)  Define a variable whose static type is the subclass and which references
     an object of the subclass.  If we cast the variable to the superclass type
     before calling the overridden method

        ((Superclass) subclassvariable).method();

     does Java call the superclass method or the subclass method?
     A: Call the subclass method

(b)  Define a variable whose static type is the superclass and which references
     an object of the superclass (but not the subclass).  If we cast the
     variable to the subclass type before calling the method, does Java call
     the superclass method or the subclass method?
(c)  Suppose you have an object whose class is the subclass.  
Can you figure out a way to call the superclass method on that object without having to
     go through the subclass method of the same name?
 */


class ParentClass {
     public void print() {
          System.out.println("Superclass");
     }
     public void superprint() {
          System.out.println("Superprint");
     }
 }
class ChildClass extends ParentClass implements B{
     public void print() {
          System.out.println("Subclass");
     }
     public void subprint() {
          System.out.println("subprint");
     }
 }
public class PartIV {
     public static void main(String[] args) {
         ChildClass sub = new ChildClass();
         ParentClass sup = new ParentClass();
         ParentClass supsub = new ChildClass();
         ((ParentClass)sub).print(); // Subclass
     //     ((ChildClass)sup).print(); // runtime error
          ((ChildClass)supsub).print(); // Superprint
          supsub.print(); // Superprint
          ParentClass supsub2 = sub;
          // supsub2.print(); // Subclass
          supsub2.superprint(); // Superprint
     }
 }
