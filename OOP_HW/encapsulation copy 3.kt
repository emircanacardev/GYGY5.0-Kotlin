// Banka hesabı örneği: Bakiye gizlenir, kontrollü erişim sağlanır.

class BankAccount(initialBalance: Double) {
    // Bakiyeyi dışarıya kapattık (private). Kimse kafasına göre değiştiremez.
    private var balance: Double = initialBalance

    // Sadece okumak için bir fonksiyon sunuyoruz.
    fun getBalance(): Double {
        return balance
    }

    // Para Yatırma kuralımız
    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("$amount TL yatırıldı. Güncel Bakiye: $balance TL")
        } else {
            println("Hatalı işlem: Yatırılacak miktar 0'dan büyük olmalı.")
        }
    }

    // Para Çekme kuralımız
    fun withdraw(amount: Double) {
        if (amount > 0 && balance >= amount) {
            balance -= amount
            println("$amount TL çekildi. Kalan Bakiye: $balance TL")
        } else {
            println("Hatalı işlem: Bakiye yetersiz veya geçersiz miktar.")
        }
    }
}

fun main() {
    val myAccount = BankAccount(1000.0)
    
    // myAccount.balance = 5000.0 // Hata! Buna dışarıdan erişilemez.
    
    myAccount.deposit(500.0)
    myAccount.withdraw(200.0)
    myAccount.withdraw(5000.0) // Yetersiz bakiye testi
}