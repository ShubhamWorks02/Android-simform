package com.example.classesandoops

// we cant create object of Sealed class so it is like abstract class

sealed interface AirCraftInfo {
    fun getInfo()
    // we cant extend sealed interface to another package so in real life scenario client cant
    // implement sealed interface functionality
}
// sealed class implementing sealed interface

sealed class AirCraft(val type: String): AirCraftInfo {
    // val type: String = "Boieng"
    // sealed class can contain abstract method also
    // public constructor() = this("Boieng370 default") //this cant be possible bcz sealed class constructor
    // can only have two possibilities protected (by default) or private
    open fun fly() {
        println("Aircraft is flying")
    }
    class Helicopter: AirCraft("Helicopter") {
        override fun getInfo() {
            println("type of Aircraft is Helicopter")
        }
        override fun fly() {
            println("Helicopter is flying")
        }
    }
}

class Aeroplane: AirCraft("Aeroplane") {
    override fun getInfo() {
        println("type of Aircraft is Aeroplane")
    }
    class HoverCraft: AirCraft("Aeroplane"){
        override fun getInfo() {
            println("type of Aircraft is HoverCraft")
        }
        override fun fly() {
            println("HoverCraft is flying")
        }
    }
    override fun fly() {
        println("Aeroplane is flying")
    }
}
// Direct subclasses of sealed classes and interfaces must be declared in the same package.

fun checkType(airCraft: AirCraft) = when(airCraft) {
    is Aeroplane -> { println("type of aeroplane") }
    is AirCraft.Helicopter -> { println("type of Helicopter") }
    is Aeroplane.HoverCraft -> { println("type of HoverCraft") }
}
