package com.example.hu_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hu_project.adapter.HorizontalPostAdapter
import com.example.hu_project.databinding.FragmentProfileBinding
import com.example.hu_project.model.main_data
import com.google.android.material.tabs.TabLayout

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HorizontalPostAdapter // 게시글 recyclerView 어댑터

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        recyclerView.adapter = HorizontalPostAdapter(posts)
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