//kotlin javanın moderni 
//daha az kodla aynı işi yapabilen versiyonu

fun main()
{
    println("Hello World!")
    
    for(i in 0..5)  //5 dahil  
    {
        println(i)
    }
    
    for(i in 0..5 step 2)
    {
        println(i)
    }

    for(i in 5 downTo 0)
    {
        println(i)
    }

    for(i in 0 until 5)  //5 dahil değil
    {
        println(i)
    }


    val studentList = listOf("ahmet", "mehmet", "emircan")

    for (student in studentList){

        if (student == "mehmet")
        {
            println("deneme")
            continue
            println("deneme1")

        }

        println(student)
    }
}