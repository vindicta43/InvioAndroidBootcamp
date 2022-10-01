/**
 * Created by Alperen Erdoğan on 30.09.2022.
 */

// Alperen Erdoğan

// Fonksiyonlarin olusturuldugu dosya

class Odev2 {
    // 1.
    // Dogrudan esitleme
    fun celsiusToFahrenheit(c: Double) = (c * 1.8) + 32

    // 2.
    // Return ile dogrudan dondurme
    fun rectanglePerimeter(r1: Double, r2: Double, r3: Double, r4: Double): Double {
        return r1 + r2 + r3 + r4
    }

    // Extension oldugu icin erisime acik olmasi gerekti
    companion object {
        // 3.
        // Extension
        fun Int.factorial(): Int {
            var result = 1

            for (i in 1..this) {
                result *= i
            }
            return result
        }
    }

    // 4.
    // Return etmeden sonucu yazdirma
    fun letterCount(text: String, char: Char) {
        var result = 0

        for (i in text) {
            if (i == char)
                result++
        }

        println("$text içindeki \"${char}\" sayısı: $result")
    }

    // 5.
    fun fromEdgeToAngle(edgeCount: Int): Any {
        return if (edgeCount < 3) {
            "Kenar sayısı 3 ten büyük olmalı"
        } else {
            (edgeCount - 2) * 180
        }
    }

    // 6.
    fun calculateSalary(workedDays: Int): Int {
        val workedHours = workedDays * 8

        return if (workedHours > 160) {
            val overShiftHours = workedHours - 160
            // 160 saat calisma = 1600
            // fazla mesai ucretini buna ekleyip donduruyoruz
            (overShiftHours * 20) + 1600
        } else {
            workedHours * 10
        }
    }

    // 7.
    fun calculateQuota(usedQuota: Int): Int {
        // 1 GB = 2 TL
        // asim durumunda 1 GB = 4 TL

        return if (usedQuota > 50) {
            val overUsedQuota = usedQuota - 50
            (overUsedQuota * 4) + 100
        } else {
            usedQuota * 2
        }
    }
}