package com.example.kotlinpractice

fun main(){
    //In Kotlin, if is an expression: it returns a value. Therefore,
    //there is no ternary operator (condition ? then : else) because ordinary if works fine in this role.

    val personAge = 17
    val isAdult: String = if (personAge>18) "person is eligible for voting" else "person is not adult"
    println(isAdult)

    //gender checking
    val genderOfPerson = 'M'
    when (genderOfPerson) {
        'M','m' -> print("person is male")
        'F','f' -> print("person is female")
        else -> {
            print("person chose is not identifying as male or female rather not to disclose")
        }
    }
}