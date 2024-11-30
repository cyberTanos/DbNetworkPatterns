package com.example

/**
Реализовать паттерн Builder для класса Car (поля и свойства можете придумать сами).
Так же написать реализацию Abstract Factory для этого класса.
 **/

class Car private constructor(
    val brand: String,
    val color: String,
    val number: String
) {
    class CarBuilder {
        private var brand = ""
        private var color = ""
        private var number = ""

        fun setBrand(brand: String) = apply { this.brand = brand }
        fun setColor(color: String) = apply { this.color = color }
        fun setNumber(number: String) = apply { this.number = number }
        fun build(): Car = Car(brand, color, number)
    }

    override fun toString(): String {
        return "Car($brand, $color, $number)"
    }
}

interface CarFactory {
    fun createCar(): Car
}

class RangeRoverFactory : CarFactory {
    override fun createCar(): Car {
        return Car.CarBuilder()
            .setBrand("RangeRover")
            .setColor("Black")
            .setNumber("001")
            .build()
    }
}

class ToyotaFactory : CarFactory {
    override fun createCar(): Car {
        return Car.CarBuilder()
            .setBrand("Toyota")
            .setColor("Gray")
            .setNumber("999")
            .build()
    }
}
