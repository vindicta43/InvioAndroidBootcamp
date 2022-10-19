package com.alperen.odev6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alperen.odev6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setVideoContent()
    }

    private fun setVideoContent() {
        val layoutList = arrayListOf(
            binding.layout1,
            binding.layout2,
            binding.layout3,
            binding.layout4,
            binding.layout5,
            binding.layout6,
            binding.layout7,
            binding.layout8,
            binding.layout9,
            binding.layout10,
        )

        val videoImages = arrayListOf(
            "video1", "video2", "video3", "video4", "video5", "video6", "video7", "video8", "video9", "video10"
        )

        val channelImages = arrayListOf(
            "pp1",
            "pp2",
            "pp3",
            "pp4",
            "pp5",
            "pp6",
            "pp7",
            "pp8",
            "pp9",
            "pp10",
        )

        val videoTitles = arrayListOf(
            "Doktorun Bir Günü | Röportaj Adam",
            "Kaptan Amerika'nın Enerji Kalkanını Yaptım.",
            "Elon Musk, Twitter'ı “her şey uygulaması”na dönüştürebilir mi?",
            "Göksel - Sen Orda Yoksun (Full Albüm)",
            "YÜZÜKLERİN EFENDİSİ SEZON FİNALİ YORUMLARI İşte Sauron!",
            "Dünyanın En Fakir Ülkesi 'BURUNDİ' (Gördüklerimi Asla Unutamayacağım)",
            "Oto Yıkamadan Zirveye | Mustafa Baranlı Kimdir?",
            "Ara - Zeynep Bastık (Paro Official ZB Version) | Music Video",
            "Şerbetli Kavurmaya Dükkanlar Yetmedi | Bizden Başka Herkes Battı",
            "Astsubay Büşra Bilge Demir Ses Analizi (Kerküğün Zindanı)",
        )

        val videoDescriptions = arrayListOf(
            "Röportaj Adam · 1,4 Mn görüntüleme · 1 gün önce",
            "Tolga Özuygur · 636 B görüntüleme · 12 gün önce",
            "Barış Özcan · 102 B görüntüleme · 6 saat önce",
            "Avrupa Müzik · 11 Mn görüntüleme · 7 yıl önce",
            "Yüzüklerin Efendisi - Orta Dünya · 190 B görüntüleme · 1 gün önce",
            "Ruhi Çenet Medya · 15 Mn görüntüleme · 9 ay önce",
            "StoryBox · 117 B görüntüleme · 2 gün önce",
            "Zeynep Bastık · 60 Mn görüntüleme · 2 ay önce",
            "Gurmoss · 868 B görüntüleme · 4 ay önce",
            "Emre Yücelen Şan Dersi · 201 B görüntüleme · 13 gün önce",
        )

        layoutList.forEachIndexed { index, singleItem ->
            singleItem.apply {
                ivVideoImage.setImageResource(
                    resources.getIdentifier(
                        videoImages[index], "drawable", packageName
                    )
                )

                ivChannelImage.setImageResource(resources.getIdentifier(channelImages[index], "drawable", packageName))

                tvVideoTitle.text = videoTitles[index]

                tvVideoDescription.text = videoDescriptions[index]
            }
        }
    }
}