package com.example.hu_project.account

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hu_project.databinding.ActivityLoadingBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3초 후 LoginActivity로 전환
        lifecycleScope.launch {
            delay(3000)
            startActivity(Intent(this@LoadingActivity, LoginActivity::class.java))
            finish()
        }

    }
}