package com.example.hu_project

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hu_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setBottomNavigationView()

        if(savedInstanceState == null){
            binding.bottomNavigationView.selectedItemId = R.id.fragment_home
        }
    }
    fun setBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.fragment_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, HomeFragment()).commit()
                    true
                }
                R.id.fragment_search -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, SearchFragment()).commit()
                    true
                }
                R.id.fragment_posting -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, PostingFragment()).commit()
                    true
                }
                R.id.fragment_profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, ProfileFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
}