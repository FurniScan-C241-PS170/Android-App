package com.dicoding.furniscan.ui.Profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.dicoding.furniscan.R
import com.dicoding.furniscan.ViewModelFactory
import com.dicoding.furniscan.data.preference.TokenPreferences
import com.dicoding.furniscan.data.preference.dataStore
import com.dicoding.furniscan.databinding.FragmentProfileBinding
import com.dicoding.furniscan.ui.welcome.WelcomeActivity

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory: ViewModelFactory =
            ViewModelFactory.getInstance(
                requireActivity(),
                TokenPreferences.getInstance(requireContext().dataStore)
            )
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireActivity()).apply {
                setTitle(getString(R.string.logout))
                setMessage(getString(R.string.are_your_sure))
                setPositiveButton(getString(R.string.continue_dialog)) { _, _ ->
                    viewModel.logout()
                    val intent = Intent(context, WelcomeActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
                setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                create()
                show()

            }
        }
    }

}