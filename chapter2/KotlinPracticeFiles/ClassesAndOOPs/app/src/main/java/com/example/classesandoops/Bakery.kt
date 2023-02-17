package com.example.classesandoops

import kotlin.properties.Delegates

// naive approach taken to experiment delegate

fun interface BakeryDelegate {
    fun makeCookie() {
        println("cookie will be made by bakery")
    }
    fun deliverCookie()
}

class Bakery(courierBoy: BakeryDelegate) : BakeryDelegate by courierBoy  {
    override fun makeCookie() {
        println("cookie is being made")
        deliverCookie()
    }
}

// data class DeliveryGuy(val name: String)
class Courier: BakeryDelegate {
    override fun deliverCookie() {
        println("cookie is being delivered By Courier")
    }
}

fun main() {
    val fedEx = Courier()
    val cookie = Bakery(fedEx)
    cookie.makeCookie()
    //fedEx.makeCookie()
    //fedEx.deliverCookie()
    // Courier(cookie).deliverCookie()
    val age: Int by lazy { // immutable
        println("lazy prop called")
        20
    }
    println(age)
    var name: String by Delegates.observable("shubham bhatt")
    {
        // default Previous value "shubham bhatt"
        property, oldValue, newValue ->
        oldValue
        println("property name is ${property.name},oldvalue was $oldValue,new is $newValue")
        // Old value is being
        // assigned a new value
    }
    println(name)
    name = "shyam"
    var max: Int by Delegates.vetoable(10)
    {
        // Defining variable max with vetoable default value as 10
        property, oldValue, newValue ->
        println("trying to set newvalue")
        newValue > oldValue
    }
    max = 5
    println(max) // cant assign value less than max
}

