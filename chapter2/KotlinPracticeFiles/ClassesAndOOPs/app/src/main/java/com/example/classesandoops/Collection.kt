package com.example.classesandoops

// collection just for sake of CRUD operation.

fun main() {

    val teamMates = mutableListOf("shubham", "darshan", "shyam",null)
    val teamMates1 = mutableListOf("shubham", "shyam",null,"darshan")
    teamMates.add("prem patel")
    // Note that altering a mutable collection doesn't require it to be a var: write operations
    // modify the same mutable collection object, so the reference doesn't change. Although,
    // if you try to reassign a val collection, you'll get a compilation error.

    println(teamMates)
    teamMates.shuffle()
    println(teamMates)
    println(teamMates.toString())
    println(teamMates == teamMates1)
    println(teamMates.set(2,""))
    teamMates.forEach { it?.contains("sh") }
    println(teamMates)
    println()

    val phNumber = setOf(9,6,7)
    phNumber.first()
    for (digit in phNumber) println(digit)
    println(phNumber.indexOf(6))
    // Map<K, V> is not an inheritor of the Collection interface

    var employee = mutableMapOf<Int,String>(1 to "shubham",2 to "Shyam", 3 to "darshan")
    println(employee.keys)
    println(employee.filterValues { it.length > 3 })
    println(employee[6])
    // Two maps containing the equal pairs are equal regardless of the pair order.
    employee.put(4,"x person")
    employee.putIfAbsent(4,"prem patel")
    println(employee)
    // employee = emptySet()

    var integers = List(4,{it+1})
    integers = List(5, {(0..Int.MAX_VALUE).random()})
    println(integers)
    // val set = setOf(4,5)
    // val convertSet = set.toSet()
    // println(convertSet)
    val numbers = listOf("one", "two", "three", "four")
    val numbersIterator = numbers.iterator()
    while (numbersIterator.hasNext()) {
        println(numbersIterator.next())
    }
    val longerThan3 = numbers.filter { it.length > 3 }
    println(longerThan3)
    for (i in 1 until 10) {  // i in 1 until 10, excluding 10 // Until Extension fun + infix
        print(i)
    }
    // scope function in collection
    val nums = mutableListOf(4,5,6)
    nums.apply { add(9) }
        .also { it.forEach{ println(it) } } // using multiple it across scopes
    // println(nums)
    var extensionLambda: Int.() -> Int = {
        println("having receiver value $this")
        this
    }
    5.extensionLambda()
}
