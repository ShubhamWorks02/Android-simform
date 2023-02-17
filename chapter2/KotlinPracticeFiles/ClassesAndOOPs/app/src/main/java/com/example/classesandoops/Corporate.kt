package com.example.classesandoops


// Simple Example of Delegation for practice

interface Employee2 {
    fun doWork ()
}

class EmployeeImpl: Employee2 {
    override fun doWork() {
        println("Employee started working")
    }
}

class Boss(employee: Employee2): Employee2 by employee

fun main() {
    val boss = Boss(EmployeeImpl())
    boss.doWork()
}
