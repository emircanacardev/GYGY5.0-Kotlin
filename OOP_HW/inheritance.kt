// Araç örneği: Ortak özellikler miras alınır, kod tekrarı önlenir.

// Üst (Parent) Sınıf. 'open' kelimesi miras alınabileceğini belirtir.
open class Vehicle(val brand: String, val model: String) {
    open fun startEngine() {
        println("$brand $model motoru çalıştırıldı.")
    }
}

// Alt Sınıf 1: Araba (Vehicle'dan miras alır)
class Car(brand: String, model: String, val numberOfDoors: Int) : Vehicle(brand, model) {
    fun openTrunk() {
        println("$brand $model bagajı açıldı.")
    }
}

// Alt Sınıf 2: Motosiklet (Vehicle'dan miras alır)
class Motorcycle(brand: String, model: String, val hasSidecar: Boolean) : Vehicle(brand, model) {
    // Motosiklete özel motor çalıştırma sesi (Ata metodunu eziyoruz - Override)
    override fun startEngine() {
        println("$brand $model motosikleti çalıştı!")
    }
}

fun main() {
    val myCar = Car("Toyota", "Corolla", 4)
    myCar.startEngine() // Miras aldığı metodu kullanır
    myCar.openTrunk()   // Kendi özel yeteneğini kullanır

    println("---")

    val myMotorcycle = Motorcycle("Yamaha", "MT-07", false)
    myMotorcycle.startEngine() // Kendi ezdiği metodu kullanır
}