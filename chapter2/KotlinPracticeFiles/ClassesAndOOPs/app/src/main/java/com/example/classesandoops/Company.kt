package com.example.classesandoops

class Company(val name: String, val founder: String) {
    var location: String?
    init {
        location = "US"
    }
    fun getInfo() {
        println("company having name : $name")
    }
}

fun main() {
    val meta = Company("Meta Co", "Zuckerberg").apply {
        println(location)
        location = "Miami"
        fun funcInScopeFunction() {
            println("funtion created in 'apply' ")
        }
        funcInScopeFunction()
    }

    // meta.location = null
    meta.location?.let { println("in the let statement location is $it") }
    // Context object refers to the object on which we are using the scope functions.
    // As in our previous example – ‘meta’ is our context object
    println(meta.location)
    with(meta) {
        println("using with scope func")
        getInfo()
    }
}
