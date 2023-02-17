package com.example.classesandoops

enum class BuildVariant (val status: String) {
    Beta("still in beta phase"),
    Release("to be released"),
    Debug("debugging phase")
}
enum class DAYS(val isWeekend: Boolean = false) {
    SUNDAY(true) {
        override fun weekend() {
            println("Today includes weekendType")
        }
    },
    MONDAY {
        override fun weekend() {
            println("Today doesnt include weekendType")
        }
    },
    TUESDAY {
        override fun weekend() {
            println("Today doesnt include weekendType")
        }
    },
    WEDNESDAY {
         override fun weekend() {
            println("Today doesnt include weekendType")
        }
    },
    THURSDAY {
        override fun weekend() {
            println("$THURSDAY doesnt include weekendType")
        }
    },
    FRIDAY {
        override fun weekend() {
            println("Today doesnt include weekendType")
        }
    },
    SATURDAY(true) {
        override fun weekend() {
            println("Today doesnt include weekendType")
        }
    };
    abstract fun weekend()
    // Default value overridden

}

fun variantCheck(buildVariant: BuildVariant) {
    when (buildVariant) {
        BuildVariant.Beta -> {
            // Code to handle beta build variant
            println("beta variant selected")
            println(BuildVariant.Beta.status)
        }
        BuildVariant.Release -> {
            // Code to handle release build variant
            println("release variant selected")
        }
        BuildVariant.Debug -> {
            // Code to handle debug build variant
            println("debug variant selected")
        }
    }
}