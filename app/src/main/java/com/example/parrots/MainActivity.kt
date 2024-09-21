package com.example.parrots

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvParrots: RecyclerView
    private val list = ArrayList<Parrots>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.color.colorPrimary, theme))
        supportActionBar?.apply {
            title = getString(R.string.app_name)
        }

        rvParrots = findViewById(R.id.rv_parrots)
        rvParrots.setHasFixedSize(true)

        list.addAll(getListParrots())
        showRecyclerList()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showRecyclerList() {
        rvParrots.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListParrotAdapter(list)
        rvParrots.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListParrotAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Parrots) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })
    }

    private fun showSelectedParrots(data: Parrots) {
        Toast.makeText(this, "Kamu memilih " + data.name, Toast.LENGTH_SHORT).show()
    }

    private fun getListParrots(): Collection<Parrots> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listHero = ArrayList<Parrots>()
        for (i in dataName.indices) {
            val hero = Parrots(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listHero.add(hero)
        }
        return listHero
    }
}