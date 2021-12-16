package com.example.quizappkt2.kolg.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizappkt2.databinding.FragmentDashboardBinding

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }
}
