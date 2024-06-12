package com.dicoding.furniscan.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.furniscan.ui.Profile.ProfileFragment
import com.dicoding.furniscan.R
import com.dicoding.furniscan.databinding.FragmentHomeBinding
import com.dicoding.furniscan.ui.detail.DetailActivity


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFurniture.layoutManager = GridLayoutManager(context, 2)

        binding.profile.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainLayout, ProfileFragment())
                .commit()
        }

        binding.catChair.setOnClickListener {
           val intent = Intent(context, DetailActivity::class.java)
            startActivity(intent)
        }


    }
}