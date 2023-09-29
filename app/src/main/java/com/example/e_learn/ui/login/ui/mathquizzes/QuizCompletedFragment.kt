package com.example.e_learn.ui.login.ui.mathquizzes

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.e_learn.R
import com.example.e_learn.databinding.FragmentQuizCompletedBinding


class QuizCompletedFragment : Fragment() {
    private var _binding:FragmentQuizCompletedBinding? = null
    private val binding get() = _binding!!
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuizCompletedBinding.inflate(inflater, container, false)
        val root = binding.root
        val totalScore = 100
        val score = arguments?.getInt("Score")
        Log.d("Score", score.toString())
        binding.textView11.text = "You scored: "+score.toString()+" out of "+totalScore
        binding.button4.setOnClickListener {
            findNavController().navigate(R.id.action_quizComplete_to_setLevel2)
        }
        binding.button5.setOnClickListener {
            findNavController().navigate(R.id.nav_setQuiz)
        }
        binding.button6.setOnClickListener {
            findNavController().navigate(R.id.nav_mathTopics)

        }
        binding.button7.setOnClickListener {
            findNavController().navigate(R.id.nav_home)
        }
        return root
    }
}