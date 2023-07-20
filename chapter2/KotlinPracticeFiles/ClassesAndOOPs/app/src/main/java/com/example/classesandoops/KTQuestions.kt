package com.example.classesandoops

import kotlin.properties.Delegates

fun main() {
    val aircraft: Aircraft1 = Helicopter1("shubham",500, 90000).apply {
        setAltitudeObserver {
            println("altitude has been changed to $it")
        }
    }
    aircraft.setAltitude(500u)
    aircraft.setAltitude(550u)
    aircraft.changeAltitude(50)
    aircraft.fuelAircraft(1)
    }

abstract class Aircraft1 {
    abstract val pilotName: String
    abstract val fuelCapacity: Int
    private var altitude1: UInt by Delegates.observable(0u){
        _, oldValue, newValue ->
        altitudeObserver(newValue)
    }
    private lateinit var altitudeObserver : (UInt) -> Unit
    fun setAltitudeObserver(altitudeObserver : (UInt) -> Unit) {
        this.altitudeObserver = altitudeObserver
    }
    private val listOfFuelers = mapOf(1 to "shubham",2 to "shyam")
    private var fuelingPerson: String = "ramesh"
    abstract val maxSpeed: Int
    fun assignFuelingPerson(id: Int): Boolean {
        if (!listOfFuelers.contains(id)) return false
        fuelingPerson = listOfFuelers.getValue(id)
        return true
    }
    fun getAltitude(): UInt {
        return altitude1
    }
    fun setAltitude(newAltitude: UInt) {
        altitude1 = newAltitude
        // println("altitude is set to ${setAltitudeObserver()}")
    }
    fun changeAltitude(difference: Int) {
        if (difference < 0 && difference.toUInt() > altitude1) return
        altitude1 = (altitude1.toInt() + difference).toUInt()
    }
    fun fuelAircraft(refluelerId: Int) {
         if (!assignFuelingPerson(id = refluelerId)) {
             println("can not find fueling person")
             return
         }
         println("person having id : $refluelerId is fueling aircraft")
    }

    fun isFighterjet(aircraft1: Aircraft1): Boolean = aircraft1 is FighterJet1
    abstract fun getInfo()
}


class Helicopter1(
    override val pilotName: String,
    override val fuelCapacity: Int,
    override val maxSpeed: Int
): Aircraft1() {
    private var isRotorRunning: Boolean = false

    override fun getInfo() {
        println("$pilotName is flying helicopter and it is having maxspeed of $maxSpeed")
    }
    fun startRotor() {
        if (isRotorRunning) {
            println("Rotor is already running")
        } else {
            println("Starting rotor")
            isRotorRunning = true
        }
    }
    fun stopRotor() {
        if (!isRotorRunning) {
            println("Rotor is already stopped")
        } else {
            println("Stopping rotor")
            isRotorRunning = false
        }
    }
}

class FighterJet1(
    override val pilotName: String,
    override val fuelCapacity: Int,
    override val maxSpeed: Int,
): Aircraft1() {
    private val missiles: List<String> = listOf("agni","parmaanu","brahmastra")

    override fun getInfo() {
        println("$pilotName is flying FighterJet and having maxspeed of $maxSpeed")
    }
    fun fireMissile(missileIndex: Int) {
        if (missileIndex < 0 || missileIndex >= missiles.size) {
            println("Invalid missile index")
            return
        }
        val missile = missiles[missileIndex]
        println("$pilotName fired missile $missile")
    }
}
