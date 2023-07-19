package com.example.firstjavaclass;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("hello world");
    }

    private class First {
        public int x  = 5;
    }
    public static class Ex {

        static String name;
        static int age;

        static void set(String nam, int ag){
            name = nam;
            age = ag;

        }
        static void get(){
            System.out.println("name having :" + name);

        }
        public static void main(String args[])
        {
            Ex.set("Visual studio", 5);
            Ex.get();
        }
    }

}

