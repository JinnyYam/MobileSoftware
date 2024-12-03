package com.example.hu_project

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.ActivityNavigatorExtras
import com.example.hu_project.databinding.FragmentPostingBinding

class PostingFragment : Fragment() {
    private var selectedTextView: TextView? = null
    private var _binding: FragmentPostingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Add Image Button Click
        binding.addImageButton.setOnClickListener {
            openGallery()
        }

        // Category selection logic
        binding.categoryPlaylist.setOnClickListener { toggleSelection(binding.categoryPlaylist) }
        binding.categoryEat.setOnClickListener { toggleSelection(binding.categoryEat) }
        binding.categoryLook.setOnClickListener { toggleSelection(binding.categoryLook) }
        binding.categoryPlace.setOnClickListener { toggleSelection(binding.categoryPlace) }
    }

    private fun toggleSelection(selectedTextView: TextView) {
        this.selectedTextView?.setBackgroundColor(Color.TRANSPARENT)

        if (this.selectedTextView == selectedTextView) {
            this.selectedTextView = null
        } else {
            selectedTextView.setBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.sky_blue)
            )
            this.selectedTextView = selectedTextView
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply { type = "image/*" }
        galleryLauncher.launch(intent)
    }

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == AppCompatActivity.RESULT_OK && result.data != null) {
                val imageUri: Uri? = result.data?.data
                if(imageUri != null) {
                    binding.postImage.setImageURI((imageUri))
                } else {
                    Toast.makeText(requireContext(), "이미지를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}