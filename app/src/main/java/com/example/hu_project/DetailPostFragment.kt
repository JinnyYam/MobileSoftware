package com.example.hu_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.hu_project.adapter.CommentAdapter
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
            Glide.with(this)
                .load(it.imageUrl)
                .placeholder(R.drawable.pikmins)
                .into(binding.postImage)
            binding.postContent.text = "게시물의 내용을 여기에 표시합니다."
            binding.postOwner.text = "@noonsongi-love"
        }

        // 댓글 RecyclerView 설정
        val commentAdapter = CommentAdapter(comments)
        binding.commentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.commentRecyclerView.adapter = commentAdapter

        // 댓글 추가 버튼 동작
        binding.addCommentButton.setOnClickListener{
            val commentContent = binding.commentInput.text.toString()
            if(commentContent.isNotEmpty()) {
                val newComment = main_data.Comment(
                    author = "User",
                    content = commentContent
                )
                comments.add(newComment)
                commentAdapter.notifyItemInserted(comments.size - 1)
                binding.commentInput.text.clear()
            } else {
                Toast.makeText(requireContext(), "댓글을 입력하세요.", Toast.LENGTH_SHORT).show()
            }
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