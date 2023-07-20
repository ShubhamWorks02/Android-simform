package com.example.classesandoops

import android.view.ScaleGestureDetector

// Lambda
// val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y } // Syntax
fun main() {
    val interest = { principle: Int, returns: Int, duration: Int -> principle*returns*duration }
    val area: (Int,Int,Int) -> Int = { height,width,result -> height*width*result }
    val area1 = { interest:(Int,Int,Int) -> Int, p: Int, r: Int, n: Int -> println(interest(p, r, n))}
    // val area1: (interest: (Int,Int,Int) -> Int,p: Int,r: Int,n: Int) -> Unit =
    // {interest:(Int,Int,Int) -> Int, p: Int, r: Int, n: Int -> println(interest(p, r, n))}
    println(area(8,9,6))
    println(interest(7, 8, 9))
    area1(interest,1,2,3)

    greet(printGreet = ::print, wish = "good morning")  // Trailing Lambda
    // greet("hello",{
    // message -> println(message)
    // })
    greet("Afternoon") {
        println(it.filter {
            val tempChar = it > 'j' // i can use it only but want to check that how i can return labelled
             tempChar  // and use explicit variable
        } )
    } // Implicit single it
    val product = fun (num1: Int, num2: Int): Int = num1*num2 //by anonymous function we can provide explicit return type
    printGreet(fun() {
        println("good afternoon, anonymous func called as func param")
    })
    val add : Int.(Int) -> Int =  {this + it}
    println(3.add(5)) // Function literals with receiver

    val oldname = "shubham"
    val newName = oldname.swapp("b", "d")
    println(newName)

    val spiceJet = Jet()
    println(spiceJet.hasFuel)
    spiceJet.fuelOil()
    fillFuel(Jet())
    println(null.converToString()) // same logic use with custom logic
    println(null.toString()) // same logic use with custom logic
    val personName = null.converToString()
    println(personName is String) // True
    spiceJet.hasFuel = false
    println(spiceJet.fuel)
    println(spiceJet.hasFuel)
    Jet.classInfo(Jet.temp)
}

open class Jet {
    // override fun flying() {
    // println("class flying")
    // }
    var fuel: Boolean = true
    fun fuelOil() {
        println("member func always wins, fueling")
    }
    companion object {
        val temp: Int = 0
    }
}

class FighterJet: Jet()

var Jet.hasFuel: Boolean
    get() = true
    set(value) {
        fuel = value
    }

fun Any?.converToString(): String {
    if (this == null) return "null"
    return this.toString()
}

fun fillFuel(plane: Jet) {
   plane.fuelOil()
}

fun Jet.Companion.classInfo(temp: Int) {
    println(temp)
}

// interface JetAction{
//    fun flying()
// }
// fun JetAction.flying() {
//    println("jet is flying")
// }

fun Jet.fuelOil() {
    println("filling oil in jet")
}

fun FighterJet.fuelOil() {
    println("filling oil in FighterJet")
}

fun String.swapp(oldString: String, newString: String): String {
   return this.replace(oldValue = oldString, newValue = newString)
}

fun greet(wish: String, printGreet: (String) -> Unit) {
    printGreet(wish)
}

fun printGreet (greet: ()-> Unit) {
    greet()
}
