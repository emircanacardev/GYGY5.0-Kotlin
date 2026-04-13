# Kotlin ile Nesne Yönelimli Programlama (OOP)

Projeler ufakken her şey güzeldir ama kod tabanı büyümeye başladığında o kodun yönetilebilir ve esnek olması hayat kurtarır. İşte burada devreye giren **Nesne Yönelimli Programlama (Object-Oriented Programming)**, aslında sadece bir kural seti değil; gece rahat uyumamızı sağlayan iyi bir mimarinin temelidir.

İnternetteki o sayfalar dolusu akademik tanımları, *"Ata sınıftan türeyen bilmem neler"* cümlelerini bir kenara bırakalım. Bu yazıda OOP'nin 4 temel direğini, gerçekten kod yazarken ne işimize yaradığını konuşalım ayrıca **Kotlin** kodlarıyla destekleyerek inceleyelim.

## 1. Encapsulation (Kapsülleme)

**Encapsulation** olayını aslında sistemimizi dış dünyanın dikkatsizliklerinden korumak olarak düşünebiliriz. Bir sınıftaki verilere herkesin kafasına göre erişip değiştirebilmesi büyük bir kaostur.

Bunu bir banka hesabı gibi düşün. Hesaptaki `bakiye` değerine sistemin başka bir yerinden doğrudan `bakiye = 5000` diye müdahale edilebilmesi inanılmaz bir güvenlik açığı yaratır. Biri yanlışlıkla eksi bakiye bile yazabilir. Bunun yerine ne yapıyoruz? Bakiyeyi dışarıya kapatıyoruz (**private** yapıyoruz) ve diyoruz ki: *"Bakiyeyle oynamak istiyorsan sadece benim yazdığım 'Para Yatırma' ve 'Para Çekme' kurallarımdan geçmek zorundasın."* Böylece kontrol tamamen bizim elimizde kalıyor.

[Kotlin'de encapsulation örneğine buradan ulaşabilirsiniz.](encapsulation.kt)

## 2. Inheritance (Kalıtım)

Kod yazarken en can sıkıcı şey aynı şeyleri tekrar tekrar yazmaktır **(DRY - Don't Repeat Yourself)**. **Inheritance (Kalıtım)**, birbirine benzeyen nesnelerin ortak özelliklerini bir üst (Parent) sınıfta toplayıp, diğerlerinin bu hazıra konmasını, yani miras almasını sağlar.

Şöyle hayal et; projende **"Araba"** ve **"Motosiklet"** diye iki farklı sınıf oluşturuyorsun. İkisinin de markası, modeli var, ikisinin de motoru çalışıyor. Bunları iki sınıfa da ~~*amele*~~ gibi tek tek yazmak yerine, genel bir `Araç` sınıfı oluşturup temel özellikleri oraya yazarız. Araba ve Motosiklet de bu özellikleri miras alır ve üzerine sadece kendi özel yeteneklerini ekler. Kod hem kısalır hem de çok daha temiz durur.

[Kotlin'de inheritance örneğine buradan ulaşabilirsiniz.](inheritance.kt)

## 3. Polymorphism (Çok Biçimlilik)

Burası sistemin esnekliğini şova dönüştürdüğümüz yer. **Polymorphism**, aynı komutu alan farklı sınıfların, bu komutu kendi karakterlerine göre farklı şekillerde yerine getirmesidir. 

Diyelim ki bir spor salonu yönetim sistemi yazıyorsun ve kapıdaki turnike (kiosk) cihazını kodlayacaksın. Turnikeye kart okutulduğunda sistem sadece *"Giriş Yap"* der ve çekilir. Ama bu girişin arka planı kartı okutan kişiye göre değişir: Standart bir üye okutunca sadece kapı açılır, ama bir personel okutunca arka planda mesai saati de başlatılır. Turnike cihazını temsil eden fonksiyonumuz, kartı kimin okuttuğunu veya mesai loglarının nasıl tutulduğunu zerre kadar umursamaz. O sadece yetkiyi verir ve işi asıl sahibine bırakır. İşte bu müthiş bir esnekliktir.

[Kotlin'de polymorphism örneğine buradan ulaşabilirsiniz.](polymorphism.kt)

## 4. Abstraction (Soyutlama)

Geldik en çok kafa karıştıran kısma. Çoğu kişi kapsülleme ile soyutlamayı birbirine karıştırır. İşin arka planındaki port numaralarını, veritabanı şifrelerini gizlemek kapsüllemenin işidir. **Soyutlamanın (Abstraction)** asıl derdi çok daha vizyonerdir: **İşi "kimin" yaptığını gizlemek ve sistemi dış araçlara bağımlı olmaktan kurtarmak.**

Bunu gerçek bir mühendislik problemiyle düşünelim. Bir acil durum haberleşme uygulaması yazdın ve cihazlar arası mesajlaşma için arka planda **RabbitMQ** kullanıyorsun.

Eğer soyutlama yapmazsan, arayüzde "Gönder" butonunun olduğu 50 farklı dosyaya doğrudan şu kodu yazarsın:

```kotlin
val manager = RabbitMqManager()
manager.sendMessage("Acil durum!")
```

Arayüz kodu içerideki bağlantılarını görmez, evet. Ama çok daha tehlikeli bir şeyi bilir: **İçeride RabbitMQ çalıştığını!** Yarın öbür gün ekipçe *"RabbitMQ mobilde çok şarj yiyor, altyapıyı Firebase yapalım"* derseniz, geçmiş olsun. O 50 dosyayı tek tek bulup kodları değiştirmeniz gerekir. Projeni doğrudan RabbitMQ'ya bağlamışsındır.

**Peki Soyutlama Hayatımızı Nasıl Kurtarıyor?**  
Biz doğrudan RabbitMQ sınıfını çağırmak yerine, araya bir `MessageService` arayüzü koyarız. Arayüz katmanına deriz ki: *"Sen bana mesaj atabilen herhangi bir sistem ver, markası veya kim olduğu hiç umurumda değil."* Böylece yarın mesajlaşma altyapısını değiştirdiğinizde arayüz kodlarının tek bir satırına bile dokunmazsınız. Kodunuz değişime tamamen kapalı, gelişime açık hale gelir.    

[Kotlin'de abstraction örneğine buradan ulaşabilirsiniz.](abstraction.kt)