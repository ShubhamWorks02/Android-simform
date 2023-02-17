// Inheritance - oops
open class Vehicle(
    private val numberOfWheels: Int,
    open val price: Float,
    val manufacturer: String
) {
    // constructor() : this(7,4f,"fdf")
    open fun getInfo() {
        println("vehicle is manufactured by $manufacturer and has $numberOfWheels wheels , valued at $price")
    }
}

class Car(
    private val numberOfGears: Int,
    val isSportsCar: Boolean ,
    val isAutomatic: Boolean,
    override val price: Float // overriding property
) : Vehicle( 4, 4500f, "Hyundai") {

    init {
        println("car is having $numberOfGears gears")
    }

    constructor(isSportsCar: Boolean, isAutomatic: Boolean) : this(3 , isSportsCar, isAutomatic, 48.3f) {
        if (isSportsCar)
            println("Car is initialized as sports-car with default Features")
        if (isAutomatic)
            println("it is an automatic car")
        println("Extra Features added in car")
    }

    override fun getInfo() {
        // super.getInfo()
        println("car is manufactured by $manufacturer , has $numberOfGears gears, priced at $price")
    }

    inner class Engine(private val horsepower: Int, private val cylinders: Int) {
        fun getEngineInfo(): String {
            return "$horsepower horsepower, $cylinders cylinders, and is a sportscar? ans: $isSportsCar"
        }
    }
}

open class Bike(private val numberOfGears: Int, maxSpeed: Int): Vehicle(2,150f,"Tvs"),VehicleAction {
    var maxSpeed: Int = maxSpeed // declaring as private and using getter setter to perform action
        get() {
            println("getter called")
            return field
        }
        set(newValue) { // put private set if you want to it to be settable only in class
            println("maxSpeed set")
            field = newValue
        }
    override val vehicleType: String
        get(){
            // getter property will check that instance is type of bike then it will give VehicleType of Bike
            if (this is Bike) return "Bike"
            return "Vehicle"
        }

        init {
            println("Bike is having $numberOfGears gears and $maxSpeed max-speed")
        }

    constructor(): this(2,180)
    override fun getInfo() {
        println("Bike is having ${this.numberOfGears} gears and $maxSpeed max-speed")
    }
    override fun start() {
        println("bike is starting and can reach upto the speed of $maxSpeed")
    }

    override fun stop() {
        println("bike has stopped")
    }
}

open class Girl {
    var age: Int = 0
        get() = field
        set(ageOfGirl) {
            field = if (ageOfGirl < 18)
                18
            else if (ageOfGirl in 18..30)
                ageOfGirl
            else
                ageOfGirl-3
        }
}


interface VehicleAction {
    val vehicleType: String
    fun start() {
        println("vehicle is starting")
    }
    fun stop()
}

 interface TwoWheelerAction: VehicleAction {
     override fun start() {
         println("dfdsf")
         //super.start()
     }
 }

class Scooter: TwoWheelerAction {
    override val vehicleType: String
        get() = "Scooter"
    override fun start() {
        super.start()
        println()
    }
    override fun stop() {

    }
}
