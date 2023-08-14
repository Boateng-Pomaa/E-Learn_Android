package com.example.e_learn.ui.login.ui.mathTopics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.e_learn.HomeActivity
import com.example.e_learn.MainActivity
import com.example.e_learn.databinding.FragmentNumbersBinding


class NumbersFragment : Fragment() {

    private var _binding: FragmentNumbersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNumbersBinding.inflate(inflater, container, false)


        binding.pdfView.fromAsset("CORE_MATHEMATICS_FOR_SENIOR_HIGH_SCHOOLS.pdf")
            .password(null)
            .defaultPage(0)
            .load()

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as HomeActivity).setAppBarTitle("Real Number System")

        binding.button.setOnClickListener {
           showDialogue()
        }
    }

    private fun showDialogue(){
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle("READY TO TAKE THIS QUIZ?")
        alert.setMessage("ARE YOU SURE YOU ARE READY TO TAKE THIS QUIZ?" +
                "Make sure you have covered the topics in order to do this!" +
                "Do you still want to take the quiz")
        alert.setPositiveButton("YES") { _, _: Int ->
            Toast.makeText(
                requireContext(),
                "Brave of you!",
                Toast.LENGTH_SHORT
            ).show()
        }
        alert.setNegativeButton("NO") { _, _: Int ->
            Toast.makeText(
                requireContext(),
                "Good choice,learning makes a man perfect",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}