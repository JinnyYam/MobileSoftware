package com.example.hu_project.account

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hu_project.R
import com.example.hu_project.databinding.ActivityCheckPwBinding

class CheckPwActivity : AppCompatActivity() {
    val binding: ActivityCheckPwBinding by lazy {
        ActivityCheckPwBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var searchEmail = ""
        var searchPw = "0000"
        // 비밀번호 찾는 로직
        binding.searchPwButton.setOnClickListener{
            searchEmail = binding.searchIdTextview.text.toString()

            binding.myPw.text = "비밀번호는 \" $searchPw \"입니다. "
            binding.myPw.visibility = View.VISIBLE
        }
        binding.backButton.setOnClickListener {
            // 결과를 인텐트에 담아 반환
            intent.putExtra("ID",searchEmail)
            intent.putExtra("PW", searchPw)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}