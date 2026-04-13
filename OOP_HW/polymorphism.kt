// Turnike (Kiosk) örneği: Aynı komut, farklı sınıflarda farklı çalışır.

// Ortak arayüzümüz (Sözleşme)
interface GymMember {
    fun checkIn() // "Giriş Yap" komutu
}

// Standart Üye
class StandardMember(val name: String) : GymMember {
    override fun checkIn() {
        println("$name giriş yaptı: Kapı açıldı. İyi antrenmanlar!")
    }
}

// Personel
class StaffMember(val name: String) : GymMember {
    override fun checkIn() {
        println("$name (Personel) giriş yaptı: Kapı açıldı ve mesai başlatıldı.")
    }
}

// Turnike (Kiosk) Cihazını temsil eden fonksiyonumuz.
// Sadece GymMember arayüzünü tanır, üye mi personel mi olduğunu umursamaz.
fun kioskScan(member: GymMember) {
    println("Turnike: Kart okunuyor...")
    member.checkIn()
}

fun main() {
    val ahmet = StandardMember("Ahmet")
    val emircan = StaffMember("Emircan")

    // Aynı turnike (fonksiyon), aynı komut, bambaşka davranışlar!
    kioskScan(ahmet)
    println("---")
    kioskScan(emircan)
}