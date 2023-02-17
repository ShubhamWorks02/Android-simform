package com.example.kotlinpractice

fun main(){
    println("Datatypes , type checking and type casting")
    val arrOfAny = arrayOf(4,5,"hello",'c') //multipleDataTypes
    for (element in arrOfAny) println(element)

    val intArray: IntArray = intArrayOf(1, 2, 3)
    arrOfAny[0] = arrOfAny[1] + arrOfAny[2]
    println(arrOfAny[0])

    // Array of int of size 5 with values [45, 45, 45, 45, 45]
    //val initWithZeroArr = IntArray(5){45}
    //TypeChecking and casting begins
    //TypeChecking and casting begins

    forTypeChecking()
}
fun forTypeChecking(){

    //TypeChecking and casting begins
    //TypeChecking and casting begins
    val validName = "Shubham"
    if (validName is String) {
        println("$validName is the valid name , Authenticated")
    } else {
        println("!prompt , Please Enter valid name")
    }
    //In most cases, you don't need to use explicit cast operators in Kotlin because
    // the compiler tracks the is-checks and explicit casts for immutable values and
    // inserts (safe) casts automatically when necessary
    // validName is automatically cast to String on the right-hand side of `||`
    if (validName !is String || validName.length == 0) return //here right side is always false but to check the smart cast it is done

    val typeCastString: String? = "type casting safely" as String?
    //val typeCastString: String? = null as String?
    val safeCastUse: String? = 'c' as? String //exception will not be thrown
    println(typeCastString)
    println(safeCastUse)


}