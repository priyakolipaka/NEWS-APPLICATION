package com.example.newsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_data.Instance
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val instance = Instance.getNewsRepository()
            val response = instance.getNewsHeadlines()
            Toast.makeText(this@MainActivity, "${response.articles.size}",Toast.LENGTH_SHORT).show()
            val adapter = recyclerViewAdapter(response.articles)

            newRecyclerView = findViewById(R.id.recyclerView)
            newRecyclerView.adapter = adapter
            newRecyclerView.layoutManager = manager

            adapter.setOnItemClickListener(object : recyclerViewAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(response.articles[position].url))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.setPackage("com.android.chrome")
                    startActivity(intent)
                }
            })
        }
    }
}
