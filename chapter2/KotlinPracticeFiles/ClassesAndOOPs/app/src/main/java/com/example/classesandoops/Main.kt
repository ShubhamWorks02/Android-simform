package com.example.classesandoops

import Bike
import Car
import Girl
import Scooter
import android.provider.ContactsContract.Data
import java.util.Random

class Main
fun main() {
    val accountHolder = Account("shubham", 875569 , 832.5f)
    // val accountHolder = Account(800f)
    println(accountHolder.checkBalance())
    println(accountHolder.accHolderName)
    accountHolder.deposit(56f)
    println(accountHolder.checkBalance())
    accountHolder.withdraw(2000f)
    println(accountHolder.checkBalance())

    // getter setter practice
    val elizabeth = Girl()
    elizabeth.age = 35
    println(elizabeth.age)
    /*
     Topics Covered
     oops,constructor,inits,Visibility Modifier,override,properties(get set),nested-inner class
     */

    val car = Car(true,false)
    car.getInfo()
    println(car.Engine(4, 5).getEngineInfo())
    val bike = Bike(4,850)
    bike.maxSpeed = 100 // setter will be called
    println(bike.maxSpeed)  //getter will be called
    /*
    In Kotlin, late-initialized properties and variables are used when we need to initialize a property
    or variable at a later stage, rather than immediately when the object is created. This can be useful
    in situations where the initialization value is not known at the time of object creation
     */

    val example = LateInitProp()
    example.printMessage() // Message not initialized yet
    example.message = "Hello, world!"
    example.printMessage() // Hello, world!

    //used inner class in vehicles but just to clarify difference , small example which is created in
    //Account file and implementation is here
    val nestedClass = OuterClass.NestedClass()
    nestedClass.printName()
    val outerClass = OuterClass()
    // val innerClass = outerClass.InnerClass()
    // innerClass.printName()
    // 4/4
    // using interfaces
    bike.start()
    // println(bike.toString()) // cant have Readable result bcz it is not data class

    //enum
    val build = BuildVariant.Release
    println(build.status)
    val toDay = DAYS.THURSDAY
    toDay.weekend()
    println(BuildVariant.valueOf("Beta"))
    variantCheck(build)
    for (day in DAYS.values()) {
        println("${day.ordinal} = ${day.name}")
    }
    println("${DAYS.valueOf("WEDNESDAY")}")

    //Employee implementation
    val shyam = Employee( "shyam", 100)
    val (name,salary) = shyam // destructuring
    println(name)
    // Destructuring works on local variable only
    // compiles down to val name = shyam.component1() , val salary = shyam.component2()
    // The componentN() functions need to be marked with the operator keyword to allow using them
    // in a destructuring declaration.
    val shubham = shyam.copy(salary = 5)
    println(shyam.component1())
    val hashOfShubham = shubham.hashCode()
    val hashOfShyam = shyam.hashCode()
    println(hashOfShubham)
    println(hashOfShyam)
    println(hashOfShyam.equals(hashOfShubham))
    shubham.name = "Shubham"
    println(shyam.toString()) // it will only print primary constructor parameter
    println(shubham.toString())
    shubham.salary = 90
    println(shyam.toString())
    println(shubham.toString())
    val darshan = shyam.copy()
    darshan.salary = 800
    println(darshan.toString())
    println(shyam.toString())

    //Aircraft class implementation
    val chopper = AirCraft.Helicopter()
    chopper.fly()
    println(chopper.type)
    val airBus = Aeroplane()
    val waterCraft = Aeroplane.HoverCraft()
    waterCraft.fly()
    checkType(chopper)

    // Vehicle
    val activa = Scooter()
    activa.start()
    val yamaha = Bike()
    println(yamaha.vehicleType)

    // Equality
    println(hashOfShyam == hashOfShubham)
    println(hashOfShyam === hashOfShubham)
    // For values represented by primitive types at runtime (for example, Int),
    // the === equality check is equivalent to the == check.
    val jupiter = Bike()
    var olaElectric = Bike()
    println(jupiter === olaElectric) //false
    olaElectric = jupiter
    println(jupiter === olaElectric) //true Referential equality

    // Singleton concept - Object
    val mySql = Database
    println(mySql.capacityInfo())
    mySql.usedSpace = 11 // Usedspace using getter to customize used space according to private prop capacity
    println(mySql.usedSpace)
    // val doubleCapacity = object: Database() {
        // override fun capacityInfo() //Cannot inherit from a singleton
    // }
    val piston8 = object: Bike(5,200) { // annonymous object expression use with constructor parameters (explicit)
        override fun getInfo() {
            println("bike is having 8 piston")
        }
    }
    /*
    Make sure to only compare data objects structurally (using the == operator) and never by reference
    (using the === operator). This helps you avoid pitfalls when more than one instance of a data object exists at runtime.
     */
    piston8.getInfo() // anonymous object expression overridden method called
    piston8.start()
    println(piston8.maxSpeed)

    // Operator Overloading
    var krunal = User(500f, 50f)
    println(++krunal)
    println(-krunal)
    // var elephant = Animal() // cant create bcz Animal is private
//    val parent = Parent.Child()
    loop@ for (i in 1..10) {
         for (j in 1..10) {
             println("inside child")
             if(j == 3) break@loop
         }
    }

}

/*
in case of data object declaration
No copy() function. Because a data object declaration is intended to be used as singleton objects,
no copy() function is generated. The singleton pattern restricts the instantiation of a class to a single instance,
which would be violated by allowing copies of the instance to be created.
No componentN() function. Unlike a data class, a data object does not have any data properties.
Since attempting to destructure such an object without data properties would not make sense,
no componentN() functions are generated.
*/
/*
enum classes can't extend a sealed class (as well as any other class), but they can implement sealed interfaces.
An object declaration inside a class can be marked with the companion keyword
*/
