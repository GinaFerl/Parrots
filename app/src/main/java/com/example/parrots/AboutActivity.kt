package com.example.parrots

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.color.colorPrimary, theme))

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)  // Menampilkan tombol Back
            setDisplayShowHomeEnabled(true)  // Menampilkan ikon Back di toolbar
        }
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)

        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {  // Tombol Back ditekan
                onBackPressed()  // Kembali ke aktivitas sebelumnya
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Fungsi untuk menangani kembali ke layar sebelumnya
    override fun onBackPressed() {
        // Logika yang dilakukan saat tombol Back ditekan
        super.onBackPressed()  // Default behavior (kembali ke aktivitas sebelumnya)
    }
}