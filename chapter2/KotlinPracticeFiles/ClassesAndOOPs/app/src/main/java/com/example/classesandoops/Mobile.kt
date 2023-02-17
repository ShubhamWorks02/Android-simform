package com.example.classesandoops

import kotlin.properties.Delegates

// Manufacturer
// online-portal
// Customer
// warehouse

interface WarehouseImpl {
    fun mobileIsAvailable(mobile: Mobile): Boolean
    fun addArrivedMobileToInventory(mobileContainer: Map<String,Int>)
}

interface ManufacturerImpl {
    fun provideMobiles(): Map<String,Int>
}

class WareHouse: WarehouseImpl {
     val mobileInventory : MutableMap<String,Int> = mutableMapOf("galaxy" to 1, "iphone" to 2)

    override fun mobileIsAvailable(mobile: Mobile): Boolean {
        if (!isMobileAvailable(mobile)) {
            return false
        }
        updateMobileCount(mobile)
        return true
    }
    private fun isMobileAvailable(mobile: Mobile): Boolean =
        mobileInventory.contains(mobile.model) && mobileInventory.getValue(mobile.model) > 0

    private fun updateMobileCount(mobile: Mobile) { // 10
        mobileInventory[mobile.model] = mobileInventory.getValue(mobile.model) - 1 // 11  mobileInventory[mobile.model] - 1
    }

    override fun addArrivedMobileToInventory(mobileContainer: Map<String,Int>) {
        mobileContainer.forEach { if (mobileInventory.contains(it.key))
            mobileInventory[it.key] = mobileInventory.getValue(it.key)+it.value
        }
    }
}

class Portal(
    private val wareHouse: WarehouseImpl,
    private val manufacturer: ManufacturerImpl
): WarehouseImpl by wareHouse, ManufacturerImpl by manufacturer {

    fun customerBuysMobile(mobile: Mobile) { // 15 removed customer
        // println("Welcome $customer")
        if (mobileIsAvailable(mobile)) {
            println("customer is Buying mobile having model of ${mobile.model}")
            addArrivedMobileToInventory(provideMobiles())
        } else {
            println("mobile is not available currently , will be in stock soon")
        }
    }
}

class Manufacturer: ManufacturerImpl {
    override fun provideMobiles(): Map<String,Int> {
        return mapOf("galaxy" to 2, "iphone" to 2)
    }
}

data class Mobile(val model: String,val price: Float)

fun main() {
    val wareHouse = WareHouse()
    val flipkart = Portal(wareHouse, Manufacturer())
    val mobile = Mobile("galaxy", 56f)
    flipkart.customerBuysMobile(mobile)
    println(wareHouse.mobileInventory)
}

// fun checkInventory(): Boolean {
// mobileInventory.forEach {
// if (it.value == 0) return false
// }
// return true
// }
