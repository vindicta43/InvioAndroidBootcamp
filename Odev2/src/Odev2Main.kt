import Odev2.Companion.factorial

/**
 * Created by Alperen Erdoğan on 30.09.2022.
 */

// Alperen Erdoğan

// Fonksiyonlarin kullanildigi dosya

fun main() {
    // Fonksiyonlari kullanmak icin nesne olusturuyoruz
    val f = Odev2()

    // 1.
    val celsius = 25.0
    val fahrenheit = f.celsiusToFahrenheit(celsius)
    println("$celsius selsiyus = $fahrenheit fahrenhayt")

    val celsius2 = 48.6
    val fahrenheit2 = f.celsiusToFahrenheit(celsius2)
    println("$celsius2 selsiyus = $fahrenheit2 fahrenhayt")
    println("-----")

    // 2.
    val edge1 = 5.0
    val edge2 = 5.0
    val edge3 = 9.5
    val edge4 = 9.5
    val perimeter = f.rectanglePerimeter(edge1, edge2, edge3, edge4)
    println("kenarları $edge1 $edge2 $edge3 $edge4 olan dikdörtgenin çevresi = $perimeter")

    val edge5 = 2.3
    val edge6 = 4.5
    val edge7 = 6.7
    val edge8 = 8.9
    val perimeter2 = f.rectanglePerimeter(edge5, edge6, edge7, edge8)
    println("kenarları $edge5 $edge6 $edge7 $edge8 olan dikdörtgenin çevresi = $perimeter2")
    println("-----")

    // 3.
    val num = 5
    val factorialNum = num.factorial()
    println("${num}! = $factorialNum")

    val num2 = 7
    val factorialNum2 = num2.factorial()
    println("${num2}! = $factorialNum2")
    println("-----")

    // 4.
    val text = "Merhaba dünya"
    val search = 'a'
    f.letterCount(text, search)

    val text2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
    val search2 = 'i'
    f.letterCount(text2, search2)
    println("-----")

    // 5.
    val edgeCount = 2
    val anglesSum = f.fromEdgeToAngle(edgeCount)
    println("$edgeCount kenarlı şeklin iç açıları toplamı = $anglesSum")

    val edgeCount2 = 6
    val anglesSum2 = f.fromEdgeToAngle(edgeCount2)
    println("$edgeCount2 kenarlı şeklin iç açıları toplamı = $anglesSum2")
    println("-----")

    // 6.
    val workedDays = 15
    val salary = f.calculateSalary(workedDays)
    println("$workedDays gün çalışan kişinin alacağı maaş = ${salary}₺")

    val workedDays2 = 40
    val salary2 = f.calculateSalary(workedDays2)
    println("$workedDays2 gün çalışan kişinin alacağı maaş = ${salary2}₺")
    println("-----")

    // 7.
    val usedQuota = 20
    val quotaPrice = f.calculateQuota(usedQuota)
    println("$usedQuota GB kullanım ücreti = ${quotaPrice}₺")

    val usedQuota2 = 148
    val quotaPrice2 = f.calculateQuota(usedQuota2)
    println("$usedQuota2 GB kullanım ücreti = ${quotaPrice2}₺")
    println("-----")
}