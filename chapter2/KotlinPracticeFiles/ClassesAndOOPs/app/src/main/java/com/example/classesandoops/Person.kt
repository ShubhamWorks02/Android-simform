package com.example.classesandoops

import androidx.core.text.isDigitsOnly

typealias Man = Person
typealias nullableStr = String?
@JvmInline
value class Person(val name: nullableStr) {
    // val age: Int = 8
    // init {
    // require(name.isNotEmpty()) {
    // println("person is having proper name")
    // }
    // }
    fun info() {
        println("$name is person's name")
    }
}
    // It is forbidden for inline classes to participate in a class hierarchy.
    // This means that inline classes cannot extend other classes and are always final.
    // cant extend value classes and it also cant extend other classes
    /*
    In generated code, the Kotlin compiler keeps a wrapper for each inline class. Inline class instances
    can be represented at runtime either as wrappers or as the underlying type. This is similar
    to how Int can be represented either as a primitive int or as the wrapper Integer.
    ----
    In other words, inline classes introduce a truly new type, contrary to type aliases which only
    introduce an alternative name (alias) for an existing type
    */

fun nameCheck(person: Person): Boolean = (person.name is String)

fun main() {
    val ramesh = Man("ramesh") // used type alias
    ramesh.info()
    println(nameCheck(ramesh))
    val hasName = ramesh.name ?: ramesh.name?.isDigitsOnly() ?: when(ramesh.name){ //just to check i can put multiple elvis operator
        is String -> "it is String"
        else -> "not a string"
    }
    println(hasName)
    // val isAdult = object : AdultConformation {
    // override fun canVote(age: Byte): Boolean {
    // if (age > 18) return true
    // return false
    // }
    // }
    // println(isAdult.canVote(55))
    // Above expression is very lengthy kotlin can infer method implementation if it is of SAM
    val isAdult = AdultConformation { it>18 }
    println(isAdult.canVote(5))
    // now it is short and concise
}

fun interface AdultConformation {
    fun canVote(age: Byte): Boolean
}

