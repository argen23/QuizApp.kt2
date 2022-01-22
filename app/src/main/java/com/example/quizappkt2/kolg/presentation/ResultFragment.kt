package com.example.quizappkt2.kolg.presentation

import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quizappkt2.R
import com.example.quizappkt2.databinding.FragmentResultBinding
import com.example.quizappkt2.kolg.BaseFragment
import com.example.quizappkt2.kolg.domain.model.History
import com.example.quizappkt2.kolg.uitls.Constant
import com.example.quizappkt2.kolg.uitls.extensions.visibility
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>() {

    lateinit var history: History

    private val binding : FragmentResultBinding by viewBinding()


    override fun bind(): FragmentResultBinding {
        return FragmentResultBinding.inflate(layoutInflater)
    }

    override fun setupListener() {
        binding.finish.setOnClickListener {
            findNavController().popBackStack(R.id.resultFragment, true)
            findNavController().navigate(R.id.navigation_quiz)
        }
    }

    override fun initView() {
        if (arguments != null)
            history = Gson().fromJson(
                requireArguments().getString(Constant.RESULT_KEY),
                History::class.java
            )
        setData()
    }

    private fun setData() {
        val amount = history.amount?.toInt()
        val score = (history.correctAnswers * 1) / amount!!

        if (score > 50) binding.ivCorrect.visibility(true)

        binding.tvCategoryResult.text = resources.getString(R.string.category_,history.category)
        binding.tvDiff.text = history.difficulty
        val correctAns = history.correctAnswers
        "$correctAns/$amount".also { binding.tvCorrectAns.text = it }
        "$score%".also { binding.tvResult.text = it }

    }

}