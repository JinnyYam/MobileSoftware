package com.example.hu_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hu_project.databinding.ActivityMainBinding
import com.example.hu_project.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 초기 탭 설정
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main, HomeFragment())
                .commit()
        }
        // 하단 메뉴바
        setBottomNavigationView()
    }
//
//        if(savedInstanceState == null){
//            binding.bottomNavigationView.selectedItemId = R.id.fragment_home
//        }
//
//        // 더미 데이터
//        val playlistData = createDummyPosts("Song")
//        val eatData = createDummyPosts("Food")
//        val lookData = createDummyPosts("Look")
//        val placeData = createDummyPosts("Place")
//
//        // category 에 RecyclerView 연결
//        setupHorizontalRecyclerView(binding.playlistRecyclerView, playlistData)
//        setupHorizontalRecyclerView(binding.eatRecyclerView, eatData)
//        setupHorizontalRecyclerView(binding.lookRecyclerView, lookData)
//        setupHorizontalRecyclerView(binding.placeRecyclerView, placeData)
//    }
//
//    private fun setupHorizontalRecyclerView(recyclerView: RecyclerView, posts: List<main_data.Post>) {
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.adapter = HorizontalPostAdapter(posts) { post ->
//            // 클릭 시 상세 페이지로 이동
//            val fragment = DetailPostFragment()
//            val args = Bundle().apply {
//                putParcelable("post", post)
//            }
//            fragment.arguments = args
//
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.main, fragment)
//                .addToBackStack(null)
//                .commit()
//        }
//    }
//
//    private fun createDummyPosts(category: String): List<main_data.Post> {
//        return List(5) { index ->
//            main_data.Post(
//                title = "$category Title $index",
//                imageUrl = "@drawable/whale"
//            )
//        }
//    }

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