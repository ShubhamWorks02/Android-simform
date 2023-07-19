package com.example.classesandoops

// file should be ignored, purpose: experiment

open class A {
    open fun run() {

    }
}

class B: A() {
    override fun run() {
        super.run()
        println()
    }
}

open class R1 {

}

abstract class ZZ: R1() {}
sealed class Z: R1() {
}
sealed interface SealedScope
@JvmInline
value class Aa (val a: Int)
data class KTPractice(val a: Int)

fun main() {
    val aa = Aa(5)
    val kt = KTPractice(5)
    if (aa is Aa){
        println(aa)
    }
    val emptConstructInDataCls = EmptyConstructor()
}

open class MyClass {
    // const val X = 0 // cant use in class except in obj or companion
    companion object {
        const val MY_CONSTANT = "Hello World!"
    }
    // constructor()
    constructor(name: String)
}

class Person22(val firstName: String, val lastName: String) {
    // Primary constructor
    init {
        println("Person created with name: $firstName")
    }
    // Parameterized constructor
    constructor(fullName: String) : this(fullName.split(" ")[0], fullName.split(" ")[1])
}

class Person23 {
    var firstName: String = ""
    var lastName: String = ""

    // Empty constructor
    constructor() {}
    // Parameterized constructor
    constructor(firstName: String, lastName: String) {
        this.firstName = firstName
        this.lastName = lastName
    }
}

//
//sealed class Fruit {
//    class Apple : Fruit()
//    object Orange : Fruit()
//    object Banana : Fruit()
//}
//
//const val APPLE: Fruit = Fruit.Apple()
//const val ORANGE: Fruit = Fruit.Orange
//const val BANANA: Fruit = Fruit.Banana

data class EmptyConstructor(var hasValues: Boolean) {
    constructor() : this(false) // Empty constructor
    constructor(value: Boolean, numberOfValues: Int): this(value)
}
