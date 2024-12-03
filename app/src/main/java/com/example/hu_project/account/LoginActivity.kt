package com.example.hu_project.account

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.hu_project.HomeFragment
import com.example.hu_project.MainActivity
import com.example.hu_project.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Login Button Click
        binding.loginButton.setOnClickListener {
            val id = binding.idEditText.text.toString()
            val pw = binding.pwEditText.text.toString()

            if(id.isNotEmpty() && pw.isNotEmpty()) {
                val homeIntent = Intent(this, MainActivity::class.java)
                homeIntent.putExtra("ID", id)
                homeIntent.putExtra("PW", pw)
                startActivity(homeIntent)
                finish()    // 현재 로그인 화면 종료
            } else {
                Toast.makeText(this, "아아디와 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }

        // ActivityResultLauncher 등록
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                val id = result.data?.getStringExtra("ID")
                val pw = result.data?.getStringExtra("PW")
                binding.idEditText.setText(id.orEmpty())
                binding.pwEditText.setText(pw.orEmpty())
            }
        }

        // 비밀번호 찾기 화면 이동
        binding.searchPwTextview.setOnClickListener {
            val checkPwIntent = Intent(this, CheckPwActivity::class.java)
            launcher.launch(checkPwIntent)
        }

        // 회원가입 화면 이동
        binding.joinTextView.setOnClickListener {
            val joinIntent = Intent(this, JoinActivity::class.java)
            launcher.launch(joinIntent)
        }
    }

    // 초기화 처리 (필요 시 활성화)
    override fun onStart() {
        super.onStart()
        // 주석 처리: 화면 재활성화 시 텍스트를 초기화하지 않으려면 유지
        // binding.idEditText.text.clear()
        // binding.pwEditText.text.clear()
    }
}