package com.example.hu_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.LayoutInflaterCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hu_project.adapter.HorizontalPostAdapter
import com.example.hu_project.databinding.ActivityMainBinding
import com.example.hu_project.model.main_data

class MainActivity : AppCompatActivity() {

    val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // Toolbar 설정
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 하단 메뉴바
        setBottomNavigationView()

        if(savedInstanceState == null){
            binding.bottomNavigationView.selectedItemId = R.id.fragment_home
        }

        // 더미 데이터
        val playlistData = createDummyPosts("Song")
        val eatData = createDummyPosts("Food")
        val lookData = createDummyPosts("Look")
        val placeData = createDummyPosts("Place")

        // category 에 RecyclerView 연결
        setupHorizontalRecyclerView(binding.playlistRecyclerView, playlistData)
        setupHorizontalRecyclerView(binding.eatRecyclerView, eatData)
        setupHorizontalRecyclerView(binding.lookRecyclerView, lookData)
        setupHorizontalRecyclerView(binding.placeRecyclerView, placeData)
    }

    private fun setupHorizontalRecyclerView(recyclerView: RecyclerView, posts: List<main_data.Post>) {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = HorizontalPostAdapter(posts)
    }

    private fun createDummyPosts(category: String): List<main_data.Post> {
        return List(4) { index ->
            main_data.Post(
                title = "$category Title $index",
                imageUrl = "@drawable/whale"
            )
        }
    }

    fun setBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.fragment_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main, HomeFragment()).commit()
                    true
                }
                R.id.fragment_search -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main, SearchFragment()).commit()
                    true
                }
                R.id.fragment_posting -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main, PostingFragment()).commit()
                    true
                }
                R.id.fragment_profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main, ProfileFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
}