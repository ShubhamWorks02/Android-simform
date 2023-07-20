package com.example.firstjavaclass;

import java.util.BitSet;

public class Dog {

    String name;
    int age;

    public Dog(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return "dog is having name: " + this.getName() +
                " and age : " + this.getAge();
    }

    public static void main(String[] args) {

        // Java program to demonstrate working of values(),
// ordinal() and valueOf()

        enum Color {
            RED("shubham") ,
            GREEN,
            BLUE;

           private Color(String shubham) {
                
            }
        }

        Color arr[] = Color.values();

        // enum with loop
        for (Color col : arr) {
            // Calling ordinal() to find index
            // of color.
            System.out.println(col + " at index "
                    + col.ordinal());
        }

        // Using valueOf(). Returns an object of
        // Color with given constant.
        // Uncommenting second line causes exception
        // IllegalArgumentException
        System.out.println(Color.valueOf("RED"));
        // System.out.println(Color.valueOf("WHITE"));

        Dog dog = new Dog("rocky", 4);
        System.out.println(dog.age + " " + dog.name);
        System.out.println(dog.toString());
        breakit:
        while (5>4){
            if (4>3)break;
        }

        try {
            throw new NullPointerException("Null occurred");
        } catch (Exception e) {
            System.out.println("catch block"+ " "+e.toString());
        }

        String value = "C";
//        boolean hello = switch(value){
//            case "C" ->
//            {
//                System.out.println("case binded");
//                yield true;
//            }
//
//            case "D" ->
//            {
//                System.out.println("for D");
//            }
//
//        };
//        Labrador lb = new Labrador("hel",5,5);
//        Animal a1,a2,a3;
//        a1=new Animal();
//        a2=new Dog1();
//        a3=new BabyDog();
//        a1.eat();
//        a2.eat();
//        a3.eat();
//        HasImplementation imp = new HasImplementation();
//        imp.chat();


//        switch (value)
//        {
//
//            case "A":
//                System.out.println("Value is A");
//                break;
//            case "B":
//                System.out.println("Value is B");    //This case is executed
//
//            default:
//                System.out.println("Value is neither A nor B");
//
//        }


    }

    class Labrador extends Dog {
        int a;

        public Labrador(String name, int age, int a) {

            super(name, age);

            this.a = a;

        }
    }

    //class Bike{
//    void run(){System.out.println("running");}
//}
//class Splendor extends Bike{
//
//}
//
//    void run(){System.out.println("running safely with 60km");}
//
//    public static void main(String args[]){
//        Bike b = new Splendor();//upcasting
//        b.run();
//    }
//}
    class Animal {
        void eat() {
            System.out.println("eating");
        }


    }

    class Dog1 extends Animal {
        void eat() {
            System.out.println("eating fruits");
        }
    }

    class BabyDog extends Dog1 {
        void eat() {
            System.out.println("drinking milk");
        }

    }

    class HasImplementation extends Implement {
        void chat() {
            System.out.println("chat is implemented here");
        }
    }

    abstract class Implement {
        abstract void chat();

        void greet() {
            System.out.println("not a abstract method");
        }
    }
}