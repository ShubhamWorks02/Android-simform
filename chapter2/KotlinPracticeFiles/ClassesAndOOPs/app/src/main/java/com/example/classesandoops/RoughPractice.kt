package com.example.classesandoops

// file should be ignored, purpose: experiment

open class ScopeFunction {
    val lmbd: () -> ScopeFunction = { this }
    var count: Int = 3
}

fun main() {
    val lambda = ScopeFunction().lmbd()
    lambda.count = 89
    println(ScopeFunction().count)
    println(lambda.count)
    lambda.count.toDouble()
    var (name,age) = Customer("shubh", 5)
    println(name)
    val rectangle = Shape.Rectangle
    println(rectangle.length)
    val dbConnection = DatabaseConnection.createConnection()
    dbConnection.userName = "shubham"
    println("Connection Name: ${dbConnection.connectionName}")
    println("Connection Full Url: ${dbConnection.getFullConnection()}")
//    var sealeadObj = object : Company1 {
//
//    }
}

class Customer(var name: String,var age: Int) {
    operator fun component2(): Any {
        return age
    }
    operator fun component1(): Any {
        return name
    }
}

class EmployeeData(var empId: Int, var empName: String) {
    // fields which are common across class objects
    companion object OrgData {
        val companyName = "CompanyA"
        val companyLocation = "New York"
    }
}

// Companion object fields can be accessed using
// EmployeeData.OrgData

sealed class Shape {
    class Circle(var radius: Float): Shape()
    class Square(var length: Int): Shape()
    object Rectangle: Shape()
    {
        var length: Int = 0
        var breadth : Int = 0
    }
}

fun eval(e: Shape) =
    when (e) {
        is Shape.Circle -> println("Circle area is ${3.14*e.radius*e.radius}")
        is Shape.Square -> println("Square area is ${e.length*e.length}")
        Shape.Rectangle -> println("Rectangle area is ${Shape.Rectangle.length*Shape.Rectangle.breadth}")
    }

class DatabaseConnection(var connectionName: String) {
    var userName : String = ""
    fun getFullConnection() : String {
        return "$connectionName:$userName"
    }
    companion object {
        val HOST = "hostA"
        val PORT = "1234"
        // create method - used as a factory to create parent class obj
        fun createConnection() : DatabaseConnection =             DatabaseConnection("$HOST:$PORT")
    }
}

//class A {
//
//    fun canCreateCompObj() {
//
//    }
//
//
//
//    class B {
//        companion object   {
//           private var xz: Int = 9
//        }
//
//
//    }
//
//    object C {
//       private var x: Int = 0
//    }
//
//    fun F.hello() {
//
//    }
//    object F  {
//        var xx: Int = 8
//    }
//    object R {
//
//    }
//    fun C.hello() {
//
//    }
//
//    var count: Int = 7
//}

// to be continued work
//interface Software {
//    fun getLicense(): String
//}
//
//class ProprietarySoftware : Software {
//    override fun getLicense() = "Some Proprietary Software License"
//}
//
//class FreeSoftware : Software {
//    override fun getLicense() = "Some Free Software License"
//}
//
// class WordProcessor(software: Software) : Software by software
 interface Heloo {
    // Modifier 'protected' is not applicable inside 'file
}
 open class Scope: SealedScope {
    // Can use in outside the file where it is declared
    // Sealed Class can use conventional interface
    fun x() {
        println("")
    }
}

class RoughPractice: Heloo {}

sealed interface Company1
sealed interface Simform : Company1
sealed interface Tata : Company1
 class SimformSolution : Simform
 class SimformPvtLtd : Simform
 class Titan : Tata
 class Tcs : Tata

fun check(company: Company1) {
    val result = when (company) {
        is Simform -> println("Simform")
        is Tata -> println("tata")
    }
}