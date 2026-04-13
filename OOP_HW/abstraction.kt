//Abstraction

// Mesajlaşma altyapısı örneği: Bağımlılıkları tersine çevirme ve gizleme.

// Arayüz katmanının (UI) bileceği tek şey bu arayüzdür.
interface MessageService {
    fun sendMessage(message: String)
}

// RabbitMQ altyapısı (Karmaşık detaylar burada saklıdır)
class RabbitMqManager : MessageService {
    override fun sendMessage(message: String) {
        // Bağlantılar, kuyruk işlemleri vb. arayüzden gizlendi.
        println("RabbitMQ üzerinden kuyruğa eklendi: $message")
    }
}

// Firebase altyapısı
class FirebaseManager : MessageService {
    override fun sendMessage(message: String) {
        // HTTP istekleri, token işlemleri vb. arayüzden gizlendi.
        println("Firebase Cloud Messaging ile push atıldı: $message")
    }
}

// UI veya Controller Katmanı
// Dikkat burada RabbitMQ veya Firebase kelimesi geçmez!
class ChatScreen(private val messageService: MessageService) {
    fun onSendButtonClicked(text: String) {
        messageService.sendMessage(text)
    }
}

fun main() {
    // Uygulamanın en tepesinde, bağımlılığı (Dependency) veriyoruz.
    // Yarın Firebase'e geçmek istersek sadece aşağıdaki 1 satırı değiştireceğiz.
    
    val service: MessageService = RabbitMqManager() 
    // val service: MessageService = FirebaseManager() // Firebase'e geçiş bu kadar kolay.

    // UI katmanına servisi veriyoruz.
    val chatScreen = ChatScreen(service)
    
    // UI katmanındaki kodlar arka planda ne çalıştığını bilmeden işini yapar.
    chatScreen.onSendButtonClicked("Acil durum sinyali!")
}