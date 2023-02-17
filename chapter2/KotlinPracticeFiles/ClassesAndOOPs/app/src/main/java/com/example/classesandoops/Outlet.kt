package com.example.classesandoops

/*
Created Demo using delegation, data class , collection, Abstraction.
*/

class Outlet(
  private val restaurant: Restaurant,
  private val delivery: Delivery
): Restaurant by restaurant, Delivery by delivery  {
    fun processOrder(cart: List<String>) {
        Order(1)
            .let {
                prepareOrder(it, cart)
            }?.also {
                deliver(it)
            } ?: println("Item is not available")

        // with(order){
        // val newOrder = prepareOrder(this,cart)
        // if (newOrder == null) {
        // println("Item is not available")
        // return
        // }
        // deliver(newOrder)
        // }
        // order.also{
        // val newOrder = prepareOrder(it,cart)
        // if (newOrder == null) {
        // println("Item is not available")
        // return
        // }
        // deliver(newOrder)
        // }
    }
}

interface Restaurant {
    fun prepareOrder(order: Order, cart: List<String>): Order?
}

interface Delivery {
    fun deliver(order: Order)
}

class Honest: Restaurant {
    private val inventory = mutableMapOf<String,Int>("maggie" to 1, "Ice cream" to 3, "Pizza" to 2)

    override fun prepareOrder(order: Order, cart: List<String>): Order? {
        if (!orderCanProcess(cart)) return null
        updateInventory(cart)
        return order
    }

    private fun orderCanProcess(cart: List<String>): Boolean =
         cart.all { itemAvailability(it) }

    private fun itemAvailability(item: String): Boolean =
        inventory.contains(item) && inventory.getValue(item) > 0

    private fun updateInventory(cart: List<String>) {
        cart.forEach {
            val quantity = inventory.getValue(it) - 1
            inventory[it] = quantity
        }
    }
}

class Zomato: Delivery {
    override fun deliver(order: Order) =
        println("order having id ${order.id} will be delivered withing 20 mins")
}

data class Order (val id: Int)

fun main() {
    val outlet = Outlet(Honest(), Zomato())
    outlet.processOrder(listOf("maggie", "Ice cream", "izza"))
}
