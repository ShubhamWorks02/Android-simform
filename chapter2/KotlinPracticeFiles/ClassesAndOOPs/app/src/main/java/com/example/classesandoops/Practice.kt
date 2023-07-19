package com.example.classesandoops

class Host(val hostName: String) {
    fun getHostName() = println(hostName)
}

class Connection(val host: Host, val port: Int) {
    fun Host.connectionString() {
        getHostName()
        getPortNumber()
    }
    private fun getPortNumber() = println(port)
    fun connect() = host.connectionString()
}

fun main() {
    Connection(Host(("Simform")), 786).connect()
    val resultantSum = sum { Pair((0..Int.MAX_VALUE).random(), (0..Int.MAX_VALUE).random()) }
    println(resultantSum)
    // inlinedFunc( { println("one called")
    // return
    // },
    // { println("two called") } )
        var elephant = Animal()
    // var num = Override(5)
    // println(num.number) // prints
}
inline fun sum(noinline numberGetter: () -> Pair<Int,Int>): Long {
    val (num1, num2) = numberGetter()
    return num1.toLong()+num2.toLong()
}
// inline fun inlinedFunc( lmbd1: () -> Unit, noinline lmbd2: () -> Unit) {
//    lmbd1()
//    lmbd2()
// }

// 17/04

private class Animal {
   public var weight: Int = 0
}

object Calculator {
    fun getNumbers(): Pair<Int,Int> {
        return Pair(1,2)
    }
}

// sealed class Parent(val name: String) {
//    init {
//        println(name)
//    }
//    constructor(nam : Int): this (nam.toString())
//    class Child: Parent() {
//        constructor(age: Int): super(name = "age") // aama error aave che // Primary constructor call expected
//        constructor(name: String): this()
//    }
//    companion object Details {
//
//    }
// }

// interface IsOverridable {
//    var number: Int
// }
//
// class Override(override var number: Int): IsOverridable

sealed class Parent(val name: String) {
    companion object Details {}
    init {
    println(name)
    }

    constructor(nam: Int) : this(nam.toString())
    class Child : Parent {
        constructor(age: Int) : super(nam = age)
       // constructor(name: String) : super("Father")
    }
}
