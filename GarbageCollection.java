
import java.util.Scanner;

//An object is eliible for garbage collection if its ref variable is lost from the program
//during execution.
//1.Object created inside a method
//2.Reassigning the reference variable
//3.Nullifying the reference variable 
//4.Anonymous object
public class GarbageCollection {
    String obj_name;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);   
        int type=0;
        while(type!=5){
            type= sc.nextInt();
            switch(type){
                case 1: System.out.println("Object created inside a method");
                        method1();
                        System.gc();
                        break;
                case 2: System.out.println("Reassigning the reference variable");
                        GarbageCollection g1 = new GarbageCollection("g1");
                        GarbageCollection g2 = new GarbageCollection("g2");
                        g1 = g2; //the previous g1 obj can be garbage collected
                        System.gc();
                        break;
                case 3: System.out.println("Nullifying the reference variable");
                        GarbageCollection g3 = new GarbageCollection("g3");
                        g3 = null;
                        System.gc();
                        break;
                case 4: System.out.println("Anonymous object");
                        new GarbageCollection("g4");
                        System.gc();
                        break;
            }
        }
    }
    public GarbageCollection(String obj_name){
        this.obj_name=obj_name;
    }
    
    static void method1(){
        GarbageCollection t1 = new GarbageCollection("g1");
        method2();
    }
    
    static void method2(){
        GarbageCollection t2=new GarbageCollection("g2");
    } 
    
    protected void finalize() throws Throwable{
        System.out.println(this.obj_name+" successfully garbage collected");
    }
}
