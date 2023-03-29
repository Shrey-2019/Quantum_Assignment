package com.example.appassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appassignment.adapters.NewsAdapter
import com.example.appassignment.models.NewsItemsModel

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    private val APIKEY: String = "428008991dd241c3b1de27b3001722f8"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        recyclerView = findViewById(R.id.mRecyclerNews)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<NewsItemsModel>()

        for (i in 1..20) {
            data.add(NewsItemsModel("BBC", "headline", "discriptiondiscriptionddiscriptiondiscriptiondiscriptiondiscriptiondiscriptiondiscriptiondiscriptioniscriptiondiscriptiondiscriptiondiscriptiondiscriptiondiscription", "2023", ""))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = NewsAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter
    }
}