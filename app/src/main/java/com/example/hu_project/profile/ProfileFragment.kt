package com.example.hu_project.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hu_project.DetailPostFragment
import com.example.hu_project.R
import com.example.hu_project.adapter.HorizontalPostAdapter
import com.example.hu_project.databinding.FragmentProfileBinding
import com.example.hu_project.model.main_data

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Image Button 클릭 시 프로필 수정 화면
        binding.profileModifyBtn.setOnClickListener{
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        // 더미 데이터
        val playlistData = createDummyPosts("Playlist")
        val eatData = createDummyPosts("Eat")
        val lookData = createDummyPosts("Look")
        val placeData = createDummyPosts("Place")

        // RecyclerView 연결
        setupHorizontalRecyclerView(binding.profilePlaylistRecyclerView, playlistData)
        setupHorizontalRecyclerView(binding.profileEatRecyclerView, eatData)
        setupHorizontalRecyclerView(binding.profileLookRecyclerView, lookData)
        setupHorizontalRecyclerView(binding.profilePlaceRecyclerView, placeData)
    }

    private fun setupHorizontalRecyclerView(recyclerView: RecyclerView, posts: List<main_data.Post>) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = HorizontalPostAdapter(posts){ post ->
            // 게시글 클릭 시 상세 게시글 보기로 이동
            navigationToPostDetailFragment(post)
        }
        recyclerView.adapter = adapter
    }

    private fun navigationToPostDetailFragment(post: main_data.Post){
        val bundle = Bundle().apply {
            putParcelable("post", post) // post data 전달
        }
        findNavController().navigate(R.id.action_profileFragment_to_postDetailFragment, bundle)
    }

    private fun createDummyPosts(category: String): List<main_data.Post> {
        return List(5) { index ->
            main_data.Post(
                title = "Post Title $index",
                imageUrl = "@drawable/whale"
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}