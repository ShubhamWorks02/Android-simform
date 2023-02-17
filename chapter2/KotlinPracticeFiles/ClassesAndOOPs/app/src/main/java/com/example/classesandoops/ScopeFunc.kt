package com.example.classesandoops

class Human {
    val name: String? = null
    var age: Int = 9
}

fun main() {
    val human1 = Human()
    with(Human()) {
        println(age)
        age = 0
        println(age)
    }
    println(human1.age)
    val human2 = Human().apply {
        fun Human.walk() {
            println("person is walking")
        }
        walk() // limited to this
    }
    with(human2) {
        println("human having age of $age, performed using with")
    }
    human1.let { outer -> println("in parent let")
        outer.name?.let { // to see the diff apply ? and remove it
            name -> println(name?.length)
            println("outer scope having $outer and inner having $name")
        } }
    var name = human1.let { it.let { it.name } } // intentionally nested
    println(name)
}