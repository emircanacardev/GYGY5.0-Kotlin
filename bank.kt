// Bank.kt dosyası oluşturalım.
// Bir bankacılık uygulaması (OOP yok)
// Min 5. adet bankacılık uygulamasında olması gerektiğini düşündüğünüz fonksiyonu tanımlayalım.
// Main fonksiyonunda da bu fonksiyonları çağırıp test edelim.

val customerNames = mutableListOf<String>()
val balances = mutableListOf<Int>()
var customerNumber = customerNames.size


fun main()
{
    createCustomer("Emir")
    createCustomer("Can",1000)

    getCustomerById(1)

    getCustomerList()

    depositToCustomer(0, 500)
    depositToCustomer(0, 500)
    depositToCustomer(3, 500)

    withdrawFromCustomer(0, 500)
    withdrawFromCustomer(0, 500)
    withdrawFromCustomer(0, 500)
    withdrawFromCustomer(3, 500)


    transferMoney(1,0,500)
    transferMoney(1,0,500)
    transferMoney(1,0,500)
    transferMoney(2,0,500)
    transferMoney(1,2,500)
}


//class kullanamayacağım için iki liste oluşturup aynı index ile müşteri ismine ve bakiyesine ulaşabilirim diye düşündüm.
fun createCustomer(customerName: String, balance: Int = 0)
{ 
    println("\n------")

    customerNames.add(customerName)
    balances.add(balance)

    customerNumber = customerNames.size

    println("Customer $customerName added to system with a balance of $balance.")

    println("------")
}


//indexleri müşteri id'si olarak düşündüm ve her müşteri eklediğimde bunun 0'dan sırayla ve doğru bir şekilde ilerleyeceğini varsaydım
fun getCustomerList()
{
    println("\n--- CUSTOMER LIST ---")
    val customerNumber = customerNames.size - 1
    for (i in 0..customerNumber)
    {
        val customers = customerNames[i]
        val balance = balances[i]
        println("${i+1}. Customer ID: $i - Name: $customers - Balance: $balance")
    }
}


fun getCustomerById(customerID: Int)
{
    println("\n--- CUSTOMER INFORMATION ---")

    if (customerID < customerNumber && customerID > 0)
    {
        val customers = customerNames[customerID]
        val balance = balances[customerID]
        println("Customer Name: $customers - Balance: $balance")
    }
    else
    {
        println("Customer not found!")
    }
}


fun depositToCustomer(customerID: Int, amount: Int)
{
    println("\n--- DEPOSIT ---")

    if (customerID < customerNumber && customerID > 0)
    {
        val customers = customerNames[customerID]
        balances[customerID] += amount
        val newBalance = balances[customerID]
        println("Customer Name: $customers - New Balance: $newBalance")
    }
    else
    {
        println("Customer not found!")
    }
}

fun withdrawFromCustomer(customerID: Int, amount: Int) {
    println("\n--- WITHDRAW ---")

    if (customerID < customerNumber && customerID > 0)
    {
        val customers = customerNames[customerID]

        if ((balances[customerID] - amount) >= 0)
        {
            balances[customerID] -= amount
            val newBalance = balances[customerID]
            println("Customer Name: $customers - New Balance: $newBalance")
        }
        else
        {
            println("Customer has not enough money!")
        }
    }
    else
    {
        println("Customer not found!")
    }
}


fun transferMoney(senderID: Int, receiverID: Int, amount: Int) {
    println("\n--- MONEY TRANSFER ---")

    if (senderID < customerNumber && receiverID < customerNumber && senderID >= 0 && receiverID >= 0) {
        
        if (balances[senderID] >= amount) {
            
            balances[senderID] -= amount
            balances[receiverID] += amount

            val senderName = customerNames[senderID]
            val receiverName = customerNames[receiverID]

            println("$amount transferred from $senderName to $receiverName successfully.")
            println("$senderName New Balance: ${balances[senderID]}")
            println("$receiverName New Balance: ${balances[receiverID]}")
        } 
        else {
            println("Error: ${customerNames[senderID]} has not enough money!")
        }
    } 
    else {
        println("Error: Sender or Receiver not found!")
    }
}


/** OUTPUT

------
Customer Emir added to system with a balance of 0.
------

------
Customer Can added to system with a balance of 1000.
------

--- CUSTOMER INFORMATION ---
Customer Name: Can - Balance: 1000

--- CUSTOMER LIST ---
1. Customer ID: 0 - Name: Emir - Balance: 0
2. Customer ID: 1 - Name: Can - Balance: 1000

--- DEPOSIT ---
Customer not found!

--- DEPOSIT ---
Customer not found!

--- DEPOSIT ---
Customer not found!

--- WITHDRAW ---
Customer not found!

--- WITHDRAW ---
Customer not found!

--- WITHDRAW ---
Customer not found!

--- WITHDRAW ---
Customer not found!

--- MONEY TRANSFER ---
500 transferred from Can to Emir successfully.
Can New Balance: 500
Emir New Balance: 500

--- MONEY TRANSFER ---
500 transferred from Can to Emir successfully.
Can New Balance: 0
Emir New Balance: 1000

--- MONEY TRANSFER ---
Error: Can has not enough money!

--- MONEY TRANSFER ---
Error: Sender or Receiver not found!

--- MONEY TRANSFER ---
Error: Sender or Receiver not found!

**/