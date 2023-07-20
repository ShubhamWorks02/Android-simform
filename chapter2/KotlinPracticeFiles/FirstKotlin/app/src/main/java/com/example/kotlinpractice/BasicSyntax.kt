package com.example.kotlinpractice

fun main() {

    val priceOfPhone: Float = 10_00.5f
    val priceOfMilk: Float = 45.6f
    val intArray = arrayOf(4,5,6)
    //val charArray = charArrayOf('d','d')
    //val arr = Array(8,{i->i*2})

    println(priceOfPhone + priceOfMilk )

    for (num in intArray){
        println(num)
    }

    println("""Milk price is $priceOfMilk""")

    val firstName = "shubham "
    val lastName = "bhatt"
    val fullName = "$firstName $lastName"
    println("$fullName has ${fullName.length} character in his/her name")
    println(""""$firstName" has ${firstName.length} character """)

    var toCheckConditional = 6
    val getOutput = if (toCheckConditional == 4) {
        toCheckConditional
    } else if (toCheckConditional == 5) {
        toCheckConditional++
    } else {
        "helo"
    }
    println(getOutput)

    val personAge = 66
    when (personAge) {
        in 0..40 -> print("you are young comparitively")
        in 40..55 -> print("you are little bit old")
        in 55..66 -> print("you are above 50 years")
        in 66..110 -> print("you are old")
        else -> print("None of the above")

    }

    val teamMates = arrayOf("shubham", "darsan","shyam")
    teamMates.filter { it.contains("sh") }.map { it.uppercase() }.forEach { println(it) }
    for (item in teamMates) println(item)

    //basic sysntax
    //31/03/2023

    fun sum (valueToAdd1: Int,valueToAdd2: Int): Int{ //with param and with return type
        return valueToAdd1+valueToAdd2
    }

    var hasValueCheck: Int? = null
    println(hasValueCheck)
    hasValueCheck = 8
    //hasValueCheck = 5
    println(hasValueCheck)

    val operationWithValue = ValueManipulation()
    operationWithValue.assigneValue()
    operationWithValue.checkValOfScope()

    //String practice
    var concatInt: Int = 1
    // simple name in template:
    val templateString1 = "string is $concatInt"
    println(templateString1)
    concatInt = 2
    // arbitrary expression in template:
    val templateString2 = "${templateString1.replace(oldValue = "is", newValue = "was")}, now having $concatInt"
    println(templateString2)
    val concateString = templateString2.plus(templateString1) //returns concated str
    println(concateString)
    println(operationWithValue.useOfWhenExpression("hello"))

    val rangeCheckArray = arrayOf(4,5,67)
    if (0 !in 0..rangeCheckArray.lastIndex) println("not in range")
    else println("in range")


    val stepAdderRange = 4
    for (stepAdderRange in stepAdderRange..10 step 3) print(stepAdderRange)

    println()
//    println(x)
    val stepDown = 10
    for (stepDown in stepDown downTo 0 step 1) println(stepDown)

    println(operationWithValue.checkNullAndCast(1))
    println(operationWithValue.checkNullAndCast("str"))

    val capacityFloat = 2.7182818284f //limits decimal points to its capacity

    println(capacityFloat)
    val smallRangeInt: Int = 100
    val smallAssignedIntA: Int? = smallRangeInt
    val checkIntA: Int? = smallRangeInt

    val bigRangeInt: Int = 100000
    val bigAssignedIntB: Int? = bigRangeInt
    val checkIntB: Int? = bigRangeInt
    //above compiler provides direct object in assignment but in bigger sizes than 127 range like 10000 it does not
    println(smallAssignedIntA === checkIntA)
    println(bigAssignedIntB === checkIntB)
    //Due to different representations, smaller types are not subtypes of bigger ones.
    //you cant assign int  to long and check following bcz it tries to check whether it is type of long or not
    val intNumber: Int = 1 // A boxed Int (java.lang.Integer)

    val longNumber: Long = intNumber.toLong()
    // Implicit conversion yields a boxed Long (java.lang.Long)
    println(longNumber == intNumber.toLong())

    val charaToBreakLine: Char = '\n' //newline
    val charToConvertInInt = '5'
    val convertedInt = charToConvertInInt.digitToInt()
    println("concatenating character $charaToBreakLine and $convertedInt in  string")

    //Strings are immutable. Once you initialize a string, you can't change its value or assign a new value to it. All operations that transform strings return their results in a new String object, leaving the original string unchanged
    //    Kotlin has two types of string literals:
    //    Escaped strings , Raw Strings

    val pattern = """   //contains arbitary text and spaces//intentionally added space
        
        
        
    *  
    * *  
    * * *  
    * * * *  
        
    """

    print(pattern)
}

var scopeCheckField  = 0

class ValueManipulation (){  //practice of class and fun

//    Inheritance between classes is declared by a colon (:). Classes are final by default; to make a class inheritable, mark it as open.
    fun assigneValue(){
        scopeCheckField = 3
        println(scopeCheckField)
    }
    fun checkValOfScope(){
        println(scopeCheckField)
    }
    //The root of the Kotlin class hierarchy. Every Kotlin class has Any as a superclass
    //The root of the Kotlin class hierarchy. Every Kotlin class has Any as a superclass
    fun useOfWhenExpression(inp: Any): Any {
        when{
            (inp is Int) -> return "input is Int"
            (inp is String) -> return "input is String"
            else -> return 0
        }
    }

    fun checkNullAndCast(temp: Any): String?{
        if (temp is String) return temp //automatically cast to String
        return null //outside if it is still Any
    }

}

