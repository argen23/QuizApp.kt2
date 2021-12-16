package com.example.quizappkt2.kolg.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizappkt2.databinding.FragmentNotificationsBinding

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        settingsViewModel = ViewModelProvider(this)[SettingsViewModel::class.java]

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root

    }


}