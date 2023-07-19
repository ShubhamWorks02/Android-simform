package com.example.classesandoops

import kotlin.math.absoluteValue
import kotlin.math.max

data class Shubham(
    val isLazy: Boolean = true,
    val isDeveloper: Boolean? = null,
    val vehicle: Vehicle? = null
)

data class Vehicle(val gears: Int? = 5) {
    fun prin(): Int? {
        print("in data class vehicle")
        return 5
    }
}

fun main() {
    val shubham: Shubham? = Shubham()
    val str : String? = ""
    str.orEmpty()
    shubham?.vehicle?.
            prin()
    val gears: Int? = shubham
        ?.vehicle
        ?.gears
    println(gears)
    // below comments intentionally put to remember and experiment
    // .let { println(it) }
    // ?.let { println(it) } // prints null if it is not safely chained
    // .orEmpty()
    // .chars()
    println()
    println(read())
    // reformat(upperCaseFirstLetter = false, wordSeparator = ']', str = "dfdf")
    val numbers = intArrayOf(7,5,3)
    println(sum( number = numbers,initialSum = 9 )) //redundant
    val action = Maths()
    println(action square 3)
    println(action dataType "shubham")
    println(action dataType listOf(8, 9, 6))
    println(factorial1(5))
    println(factorial2(5, 1))
    val greeting = { println("Hello!") }
    greeting()
    println(product is (Int,Int) -> Unit)
    product(7,8)
    println(tailFactorial (::factorial2,5, 6))
    val maxNumber = numbers.maxBy { it.absoluteValue }
    println(maxNumber)
    // val sum1 = { a: Int, b: Int -> a + b }
    // println(sum1(5,6))
    // without type annotation in lambda expression
    // val sum2:(Int,Int)-> Int  = { a , b -> a + b}
}

class Maths {
    infix fun square(num: Int): Int{
        return num*num
    }
    /*
    It must be member function or extension function
    It must accepts a single parameter
    The parameter must not accept variable number of arguments and must have no default value
    It must be marked with infix keyword
    */
    infix fun dataType(x: Any): Any {
        val i = when(x) {
            is String -> "String"
            is Int -> "Integer"
            is Double -> "Double"
            is List<*> -> "List"
            else -> "invalid"
        }
        return i
    }
}
fun read() = getText()
fun getText() = "read this quote"

    // fun reformat(
    //    str: String,
    //    normalizeCase: Boolean = true,
    //    upperCaseFirstLetter: Boolean = true,
    //    divideByCamelHumps: Boolean = false,
    //    wordSeparator: Char = ' ',
    // ):Unit { return }

fun sum(vararg number: Int, initialSum: Int): Int {
    var totalSum = initialSum
    for (i in number){
        totalSum += i
    }
    return totalSum
}

fun factorial1(num: Int): Long {
    return if (num == 1)  num.toLong()
    else num*factorial1(num-1)
}

tailrec fun factorial2(num: Int, total: Int): Long {
    return if (num == 1)  total.toLong()
    else factorial2(num-1, total*num)
}

fun tailFactorial(factorial: (Int,Int)-> Long,a: Int,b: Int): Long {
    return factorial(a,b)
}

val product = {a: Int, b: Int -> println(a*b) }
