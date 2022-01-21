package com.example.quizappkt2.kolg.presentation


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quizappkt2.R
import com.example.quizappkt2.databinding.FragmentQuizBinding
import com.example.quizappkt2.kolg.BaseFragment
import com.example.quizappkt2.kolg.domain.model.History
import com.example.quizappkt2.kolg.domain.model.Questions
import com.example.quizappkt2.kolg.domain.model.Result
import com.example.quizappkt2.kolg.uitls.extensions.loadImage
import com.example.quizappkt2.kolg.uitls.extensions.visibility
import com.example.quizappkt2.kolg.uitls.Constant
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class QuizFragment : BaseFragment<FragmentQuizBinding>() {

    private var results: List<Result>? = null
    private var pos: Int = 0
    private lateinit var corrAns: String
    private var size = 0
    private  val binding: FragmentQuizBinding by viewBinding()
    private val viewModel: QuizViewModel by viewModel()
    private lateinit var history: History


    override fun bind(): FragmentQuizBinding {
        return FragmentQuizBinding.inflate(layoutInflater)
    }

    override fun setupListener() {
        binding.btn1.setOnClickListener { checkData(corrAns ==  binding.btn1.text) }
        binding.btn2.setOnClickListener { checkData(corrAns ==  binding.btn2.text) }
        binding.btn3.setOnClickListener { checkData(corrAns == binding.btn3.text) }
        binding.btn4.setOnClickListener { checkData(corrAns == binding.btn4.text) }

        binding.btn1Tf.setOnClickListener { checkData(corrAns == binding.btn1Tf.text) }
        binding.btn2Tf.setOnClickListener { checkData(corrAns == binding.btn2Tf.text) }
        binding.btnSkip.setOnClickListener { checkData(false) }


        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.navigation_quiz)

        }
    }

    private fun checkData(isCorrect: Boolean) {
        if (isCorrect) {
            viewModel.setCorrectAns()
        }
        binding.ivIsCorrect.visibility(true)
        binding.ivIsCorrect.loadImage(isCorrect)

        Handler(Looper.getMainLooper()).postDelayed({
            startGame()
        }, 1000)
    }


    override fun initView() {
        if (arguments != null) {
            results = Gson().fromJson(
                requireArguments().getString(Constant.DATA_KEY),
                Questions::class.java
            ).results

            history = History(
                0,
                requireArguments().getString(Constant.CATEGORY_KEY),
                0,
                requireArguments().getString(Constant.DIFFICULTY_KEY),
                results?.size.toString(),
                System.currentTimeMillis()
            )
            startGame()
        }

    }


    private fun startGame() {
        pos = viewModel.getPosition()
        size = results?.size!!

        if (size > pos) {
            setData(pos)
        } else {
            saveData()
        }
    }

    private fun saveData() {
        history.correctAnswers = viewModel.getCorrectAns()
        viewModel.insert(history)

        openResultFragment()

    }

    private fun setData(pos: Int) {
        val result = results?.get(pos)
        corrAns = checkText(result?.correct_answer.toString())

        binding.ivIsCorrect.visibility(false)
        binding.tvQuestion.text = checkText(result?.question)
        binding.tvCategory.text = result?.category
        binding.progressIndicator.max = size
        val position = pos + 1
        binding.progressIndicator.progress = position

        "$pos/$size".also { binding.tvProgress.text = it }

        if (result?.type == "multiple") {
            binding.multipleLayout.visibility(true)
            binding.trueFalseLayout.visibility(false)

            val answers = (result.incorrect_answers as ArrayList<String>?)!!
            answers.add(result.correct_answer.toString())
            answers.shuffle()

            binding.btn1.text = checkText(answers[0])
            binding.btn2.text = checkText(answers[1])
            binding.btn3.text = checkText(answers[2])
            binding.btn4.text = checkText(answers[3])

        } else {
            binding.multipleLayout.visibility(false)
            binding.trueFalseLayout.visibility(true)
        }
    }



    private fun openResultFragment() {
        val bundle = Bundle()

        bundle.putString(Constant.RESULT_KEY, Gson().toJson(history))

        findNavController().popBackStack(R.id.navigation_quiz, true)
        findNavController().navigate(R.id.resultFragment, bundle)
    }

    private fun checkText(text: String?): String {
        var newText: String = text?.replace("&quot;", "\"").toString()
        newText = newText.replace("&#039;", "'")

        return newText
    }




}