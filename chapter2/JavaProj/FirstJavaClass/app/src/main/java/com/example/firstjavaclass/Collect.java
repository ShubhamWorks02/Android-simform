package com.example.firstjavaclass;

import java.util.ArrayList;
import java.util.List;

public class Collect {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        Anothe c = new Anothe();

    }
    void fun(){
        System.out.println("in parent");
    }

}
class Anothe extends Collect {

    void child(){
        System.out.println("in child");
    }

}
