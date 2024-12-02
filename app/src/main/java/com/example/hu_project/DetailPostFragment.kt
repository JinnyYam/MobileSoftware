package com.example.hu_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.hu_project.databinding.FragmentDetailPostBinding
import com.example.hu_project.model.main_data

class DetailPostFragment : Fragment() {

    private var _binding: FragmentDetailPostBinding? = null
    private val binding get() = _binding!!

    private val comments = mutableListOf<main_data.Comment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        // 데이터 전달받기
        val post: main_data.Post? = arguments?.getParcelable("post")
        post?.let {
            binding.toolbar.title = it.title
            binding.postImage.setImageResource(R.drawable.pikmins)
            binding.postContent.text = "게시물의 내용을 여기에 표시합니다."
            binding.postOwner.text = "@noonsongi-love"
        }
    }
    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        // 뒤로 가기 버튼 클릭 이벤트 처리
        binding.toolbar.setNavigationOnClickListener{
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}