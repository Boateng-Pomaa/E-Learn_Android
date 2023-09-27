package com.example.e_learn.ui.login.ui.mathquizzes

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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuizCompletedBinding.inflate(inflater, container, false)
        val root = binding.root
        val score = arguments?.getInt("Score")
        Log.d("Score", score.toString())
        binding.textView11.text = score.toString()
        binding.button4.setOnClickListener {
            TODO()
        }
        binding.button5.setOnClickListener {
            findNavController().navigate(R.id.nav_setQuiz)
        }
        binding.button6.setOnClickListener {
            findNavController().navigate(R.id.nav_mathTopics)
        }
        return root
    }
}