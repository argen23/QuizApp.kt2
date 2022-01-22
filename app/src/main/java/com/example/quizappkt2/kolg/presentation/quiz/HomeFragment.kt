package com.example.quizappkt2.kolg.presentation.quiz

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quizappkt2.R
import com.example.quizappkt2.databinding.FragmentHomeBinding
import com.example.quizappkt2.kolg.BaseFragment
import com.example.quizappkt2.kolg.domain.model.Questions
import com.example.quizappkt2.kolg.uitls.extensions.visibility
import com.example.quizappkt2.kolg.network.Status
import com.example.quizappkt2.kolg.uitls.Constant
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val binding: FragmentHomeBinding by viewBinding()

    private var amount: String = "10"
    private var category: String = "All"
    private var diff: String = "All"
    private var categoryName: String = "All"
    private lateinit var options1: ArrayList<String>
    private lateinit var options2: ArrayList<String>
    private val viewModel: HomeViewModel by viewModels()

    override fun bind(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)

    }

    override fun setupListener() {
        binding.spinnerCategory.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                categoryName = options1[position]

                category = if (position == 0) options1[0]
                else (16 + position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                category = options1[0]
            }
        }

        binding.spinnerDifficulty.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    diff = options2[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    diff = options2[0]
                }

            }
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvAmount.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.btnStart.setOnClickListener {
            getData()
        }
        viewModel.progressBar.observe(viewLifecycleOwner, {
            binding.progressBar.visibility(it)
        })
    }

    private fun getData() {
        viewModel.getQuestions(amount, category, diff).observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    viewModel.progressBar.postValue(true)
                }
                Status.SUCCESS -> {
                    viewModel.progressBar.postValue(false)
                    it.data?.let { it1 -> openQuizFragment(it1) }
                }
                Status.ERROR -> {
                    viewModel.progressBar.postValue(false)
                    Toast.makeText(requireContext(), "check all", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun openQuizFragment(data: Questions) {
        val bundle = Bundle()
        bundle.putString(Constant.DATA_KEY, Gson().toJson(data))
        bundle.putString(Constant.CATEGORY_KEY, categoryName)
        bundle.putString(Constant.DIFFICULTY_KEY, diff)
        findNavController().popBackStack(R.id.navigation_quiz, true)
        findNavController().navigate(R.id.quizFragment, bundle)
    }




    override fun initView() {
        options1 = resources.getStringArray(R.array.category_list).toList() as ArrayList<String>
        options2 = resources.getStringArray(R.array.difficulty_list).toList() as ArrayList<String>

        binding.spinnerCategory.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, options1)
        binding.spinnerDifficulty.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, options2)
    }


}