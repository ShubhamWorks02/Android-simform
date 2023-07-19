package com.example.classesandoops

 // file should be ignored, purpose: experiment

@JvmInline
value class Person5(val name: String) {
    // Primary constructor is required for value class
    // Value class primary constructor must only have final read-only (val) property parameters // why
    /*
    To solve such issues, Kotlin introduces a special kind of class called an inline class.
    Inline classes are a subset of value-based classes. They don't have an identity and can only hold values.
    Inline classes are final and cannot be extended:
    Inline classes cannot be compared using references:
    won’t actually be declaring a new class, but the compiler will act as if you had
    In Inline classes, you are allowed to override toString(), but not equals() and hashCode()as they are currently reserved for future use
    */
    val age: Int
        get() = 8

    init {
        // can create init
    }
}
fun interface G {
    abstract fun yo()
}
fun G.Tds() {

}
open class Car : G{
    override fun yo() {
        println("edfs")
    }
}
class Convertible: Car() // defining the getType() extensions function on Car
private fun Convertible.getType(): String {
    return "Convertible car"
}

// defining the getType() extensions function on Convertible
fun Car.getType() = "Convertible car"

fun main() {
    val car = Convertible()
    println(car.getType())
    val userId = UserId(1)
    // setSalary(userId, 1_000)
    val per = Person5("s")
    val per2 = Person5("s")
    if (per == per2) {
        println("true")
    }


}

@JvmInline
value class UserId(val id: Int)
fun setSalary(salary: Int, userId: UserId) {

}
// class ni body ma inner ni memory
// override


enum class AD {

}

fun interface Check {
    abstract fun checkConsOfSAM()
//   abstract val one: String
//    Fun interfaces cannot have abstract properties
}