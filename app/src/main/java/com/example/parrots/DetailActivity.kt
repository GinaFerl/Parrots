package com.example.parrots

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.color.colorPrimary, theme))

        val data = intent.getParcelableExtra<Parrots>("DATA")

        data?.photo?.let { findViewById<ImageView>(R.id.img_item_photo).setImageResource(it) }
        data?.name?.let { findViewById<TextView>(R.id.tv_detail_name).text = it }
        data?.description?.let { findViewById<TextView>(R.id.tv_detail_description).text = it }

        Log.d("Detail Data", data?.name.toString())

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)

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
}