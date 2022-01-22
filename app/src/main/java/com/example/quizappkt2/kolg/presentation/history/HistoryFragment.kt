package com.example.quizappkt2.kolg.presentation.history

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quizappkt2.R
import com.example.quizappkt2.databinding.FragmentDashboardBinding
import com.example.quizappkt2.kolg.BaseFragment
import com.example.quizappkt2.kolg.domain.model.History
import com.example.quizappkt2.kolg.uitls.extensions.deleteDialog
import com.example.quizappkt2.kolg.uitls.extensions.visibility
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.inject.Inject


@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentDashboardBinding>() {

    private  val historyViewModel: HistoryViewModel by viewModels()
    private  val binding: FragmentDashboardBinding by viewBinding()
    private lateinit var history: History
    private lateinit var adapter: HistoryAdapter


    override fun bind(): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(layoutInflater)
    }


    override fun setupListener() {

    }



    override fun initView() {
        historyViewModel.getAllHistories()
        historyViewModel.result.observe(viewLifecycleOwner, {
            if (it.isEmpty()) binding.tvEmpty.visibility(true)
            else initAdapter(it)
        })
    }

    private fun initAdapter(it: List<History>) {
        adapter = HistoryAdapter(it, this::onItemClick)
        binding.rvHistory.adapter = adapter
    }

    private fun onItemClick(history: History) {
        this.history = history
        deleteDialog(requireContext(), "", getString(R.string.are_you_sure), this::delete)

    }
    private fun delete() {
        historyViewModel.delete(history)
        historyViewModel.getAllHistories()
    }

}
