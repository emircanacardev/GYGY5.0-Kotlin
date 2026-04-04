fun main() {
    selamVer()
    selamVer2("Ahmet")

    var not1 = notHesapla(85) // ekrana yaz
    var not2 = notHesapla(72) // mail atmak
    var not3 = notHesapla(30) // sms atmak

    println(not1)
    // not2'yi email at
    // not3'ü sms at
}

// Programlamada fonksiyon => 
// Bir işi birden fazla kez yapıyorsam => Tekrarlamak yerine, tek bir noktadan çağırmak doğrudur.

// Fonksiyonlar => Bir işi yapan, isimlendirilmiş bloklardır.

fun selamVer() {
    println("Merhaba")
}

// parametreler
fun selamVer2(isim:String) {
    println("Merhaba $isim")
}

// Fonksiyonlar bir şey döndürmeyen (void,Unit) olabilirler,
// bir şey döndürebilirler (return) olabilirler.
fun notHesapla(not:Int): String {
    return when(not){
        in 90..100 -> "AA"
        in 80..89 -> "BA"
        in 70..79 -> "BB"
        in 60..69 -> "CB"
        in 50..59 -> "CC"
        in 40..49 -> "DC"
        in 30..39 -> "DD"
        in 0..29 -> "FF"
        else -> "Geçersiz not"
    }
}