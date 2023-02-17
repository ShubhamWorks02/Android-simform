package com.example.classesandoops

import Bike

data class Employee(var name: String, var salary: Int) {
    val id: Int = 85965
    // override fun toString(): String {
    // return "$id is employee id and name of Employee is $name"
    // }
    /*
    If there are explicit implementations of equals(), hashCode(), or toString() in the data class body
    or final implementations in a superclass, then these functions are not generated,
    and the existing implementations are used.
     */
}

/*
Inner classes have a hidden reference to an instance of the outer class, from which they were instantiated.
When an inner class is also a data class, this leads to a question, whether that
reference is one of the data class component properties?
And whether that reference affects how the functions equals, hashCode, toString,
componentN of the data class are generated depends on the answer on this question.
 */

/*
An object declaration can contain properties, methods and so on. However, they are not allowed to have constructors
----
Sometimes you need to create an object that is a slight modification of some class, without explicitly
declaring a new subclass for it. Kotlin can handle this with object expressions and object declarations.
 */
// can not give open to standalone object (singleton) like below

// DIFFERENCE OBJECT EXP AND DECLARATION
//Object expressions are executed (and initialized) immediately, where they are used.
//Object declarations are initialized lazily, when accessed for the first time.
//A companion object is initialized when the corresponding class is loaded (resolved) that matches the semantics of a Java static initializer.

object Database: Bike(5,56) {
    private val capacity: Int = 10
    var usedSpace: Int = 0
        get() {
        if (field in capacity-1 .. capacity) {
            println("please clean storage")
            return capacity/2
        } else if (field > capacity)
            return capacity/3
        return 3
        }
    fun capacityInfo() = capacity
}
//singleton object can inherit another class and interface
//anything cant inherit singleton object
//An object declaration inside a class can be marked with the companion keyword

class GigaFactory {
    val action = makeCar() // Class members can access the private members of the corresponding companion object.
    companion object Factory {
         fun makeCar(): String = "making tesla cars in factory"
        // Class members can access the private members of the corresponding companion object.
    }
}

//class AnonymousObj {
//     fun getObject() = object {
//        val objectName: String = "car"
//     }
//
//    fun printX() {
//        // println(getObject().objectName)
//    }
//}
//
