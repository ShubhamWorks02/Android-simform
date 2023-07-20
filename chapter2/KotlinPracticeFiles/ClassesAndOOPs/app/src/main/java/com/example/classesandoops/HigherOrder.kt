package com.example.classesandoops

import kotlin.Comparator
import kotlin.properties.Delegates

data class Company2 (
    val name:String,
    val type:String,
    val startYear:Int,
    val endYear:Int?
)

//The given Companies List contains the following data i.e 10 Companies.
//1.Company(“C1”, “Technology”, startYear = 1995, endYear = null)
//2.Company(“C2”, “Finance”, startYear = 1994, endYear = 2011)
//3.Company(“C3”, “Industry”, startYear = 1993, endYear = 2020)
//4.Company(“C4”, “Auto”, startYear = 2001, endYear = 2021)
//5.Company(“C5”, “Hotel”, startYear = 2001, endYear = 2020)
//6.Company(“C6”, “Technology”, startYear = 2005, endYear = 2015)
//7.Company(“C7”, “Finance”, startYear = 1996, endYear = 2014)
//8.Company(“C8”, “Finance”, startYear = 1980, endYear = 2007)
//9.Company(“C9”, “Auto”, startYear = 1985, endYear = 2008)
//10.Company(“C10”, “Hotel”, startYear = 1989, endYear = 2004)

//1. Get the List of Technology Companies.
//2. Get the List of Companies, which started after 1994 (i.e >1994)
//3. Get the List of Companies by their Type in Ascending Order.
//4. Get the List of Companies by their End Year in Descending Order
//5. Get the List with only the name of the Company
//6. Get a list with only the name and type of the Company. in pairs
//7. Get the total years for all companies i.e sum of age of all companies
//8. Get the sum of Start Year for all companies
//9. Get the list of companies after 1994 sorted by start year
//10. Get the sum of start year of companies started after 1994

class ComparatorCompany: Comparator<Company2> {
    override fun compare(companyOne: Company2?, companyTwo: Company2?): Int {
        if (companyOne?.name == null || companyTwo?.name == null)
            return 0
        if (companyOne.name != companyTwo.name)
            return companyOne.name.compareTo(companyTwo.name)
        if (companyOne.startYear != companyTwo.startYear)
            return companyOne.startYear.compareTo(companyTwo.startYear)
        if (companyOne.endYear != null && companyTwo.endYear != null)
            return companyOne.endYear.compareTo(companyTwo.endYear)
        if (companyOne.endYear == null && companyTwo.endYear == null)
            return 0
        return if (companyOne.endYear != null) 1
        else 0
    }

}

fun main() {
    var companyList = listOf( Company2("C1", "Technology", startYear = 1995, endYear = null),
        Company2("C2", "Finance", startYear = 1994, endYear = 2011),
        Company2("C3", "Industry", startYear = 1993, endYear = 2020),
        Company2("C4", "Auto", startYear = 2001, endYear = 2021),
        Company2("C5", "Hotel", startYear = 2001, endYear = 2020),
        Company2("C6", "Technology", startYear = 2005, endYear = 2015),
        Company2("C7", "Finance", startYear = 1996, endYear = 2014),
        Company2("C8", "Finance", startYear = 1980, endYear = 2007),
        Company2("C10", "Hotel", startYear = 1989, endYear = 2008),
        Company2("C10", "Hotel", startYear = 1989, endYear = null))

    var ans = companyList.filter { it.type == "Technology" } // 1
    println(ans)
    ans = companyList.filter { it.startYear > 1994 } // 2
    println(ans)
    println(ans)
    ans = companyList.sortedByDescending { one -> one.endYear } // 3,4
    ans = companyList.sortedWith(ComparatorCompany()) // Used and made Custom Comparator for sorting
    ans = companyList.sortedWith(ComparatorCompany()) // Used and made Custom Comparator for sorting
    println(ans)
    var ans5 = companyList.map { it.name }
    println(ans5)
    var ans6: List<Pair<String,String>> = companyList.map { Pair(it.name,it.type) }
    println(ans6)

    val sumOfCompanyStartYearAfer1994: Int = companyList.filter {
        it.startYear > 1994
    }.map {
        it.startYear
    }.reduce { total,i ->
        println("total is $total")
        total + i
    }
    println(sumOfCompanyStartYearAfer1994)
    val myLazyValue: String by lazy {
        println("Computing lazy value...")
        "Hello, World!"
    }
    val ex = DelegateProp()
    ex.name = "Darshan"
    println(ex.futureChange)
    println(myLazyValue)
    println(myLazyValue)

    var max: Int by Delegates.vetoable(0) { property, oldValue, newValue ->
        newValue > oldValue
    }

    println(max) // 0
    max = 5
    println(max) // 10
    val nullableList = listOf(2,3,56,null,null)
    val setofList = nullableList.toSet()
    println(setofList)
    // val countChar
    // print(countChar)

}

class DelegateProp {
    var name: String by Delegates.observable("<no >") { prop, old, new ->
        println("$old -> $new")
        futureChange = "shubham"
    }
    lateinit var futureChange: String
    val myLazyValue: String by lazy {
        println("Computing lazy value...")
        "Hello, World!"
    }
}
