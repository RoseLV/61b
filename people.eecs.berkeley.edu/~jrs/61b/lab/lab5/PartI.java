// Let Y be a subclass of X, 
class X {
    // public void print() {  
    //     System.out.println("XXX");  
    // } 
}
class Y extends X {
    public void print() {  
        System.out.println("YYY");  
    }
}

class PartI {
    // 父类引用指向子类对象：Parent p = new Child();
    public static void main(String[] args) {
        // Let y and x be variables of classes Y and X respectively.
        X x = new X();
        // ((Y)x).print();
        
        X y = new Y();
        ((Y)y).print();
        // Y y1 = new X(); // Type mismatch: cannot convert from X to Y
        X x1 = new Y();
        // x1.print();

        // Y y2 = x; // Type mismatch: cannot convert from X to Y

        // *** 下面这行因为x的动态类型是X，所以cast成Y编译没报错，但是运行报错 ***
        // *** 编译器只管静态类型；动态类型，运行的时候才会检查 ***
        // Y y2 = (Y)x; // Exception in thread "main" java.lang.ClassCastException: class X cannot be cast to class Y (X and Y are in unnamed module of loader 'app')
        // y2.print();
        Y y1 = new Y();
        y1.print();

        System.out.println(x instanceof X);
        System.out.println(y instanceof Y);
        System.out.println(x1 instanceof Y);
        System.out.println(y1 instanceof Y);
        // x = y;
        // y = (Y)x;

        // X[] xa = {new X(), new X()};
        X[] xa = {new X(), new X()};
        Y[] ya = {new Y(), new Y()};
        // ya = xa; // compile error 
        // ya = (Y[])xa; // runtime error

        // ya[0] = xa[0];
        xa = ya;
        xa[0] = ya[0];
    }

}