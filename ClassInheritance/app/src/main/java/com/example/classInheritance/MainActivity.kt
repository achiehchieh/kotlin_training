package com.example.classInheritance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.math.PI
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val squareCabin = SquareCabin(6, 50.0)
        with(squareCabin) {
            println("\nSquare Cabin\n============")
            println("Capacity: $capacity")
            println("Material: $buildingMaterial")
            println("Has room? ${hasRoom()}")
            println("Floor area: ${floorArea()}")
        }

        val roundHut = RoundHut(3, 10.0)
        with(roundHut) {
            println("\nRoundHut\n============")
            println("Capacity: $capacity")
            println("Material: $buildingMaterial")
            println("Has room? ${hasRoom()}")
            println("Floor area: %.2f".format(floorArea()))
            println("Has room? ${hasRoom()}")
            getRoom()
            println("Has room? ${hasRoom()}")
            getRoom()
        }

        val roundTower = RoundTower(4, 15.5)
        with(roundTower) {
            println("\nRoundTower\n============")
            println("Capacity: $capacity")
            println("Material: $buildingMaterial")
            println("Has room? ${hasRoom()}")
            println("Floor area: %.2f".format(floorArea()))
        }

    }

    abstract class Dwelling(private var residents: Int) {

        // given abstract type or initialization
        abstract val buildingMaterial: String
        abstract val capacity: Int

        fun hasRoom(): Boolean {
            return capacity > residents
        }

        abstract fun floorArea(): Double

        fun getRoom() {
            if (hasRoom()) {
                residents++
                println("You got a room!")
            } else {
                println("Sorry, Sorry, at capacity and no rooms left.")
            }
        }
    }

    class SquareCabin(residents: Int, private val length: Double) : Dwelling(residents) {

        override val buildingMaterial = "Wood"
        override val capacity = 6
        override fun floorArea(): Double {
            return length * length
        }

    }

    open class RoundHut(residents: Int, private val radius: Double) : Dwelling(residents) {
        override val buildingMaterial = "Straw"
        override val capacity = 4

        override fun floorArea(): Double {
            return PI * radius * radius
        }

        fun calculateMaxCarpetLength(): Double {
            return sqrt(2.0) * radius
        }
    }

    class RoundTower(
        residents: Int,
        radius: Double,
        val floors: Int = 2
    ) : RoundHut(residents, radius) {
        override val buildingMaterial = "Stone"
        override val capacity = 4 * floors

        override fun floorArea(): Double {
            return super.floorArea() * floors
        }
    }
}