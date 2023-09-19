package com.example.e_learn.ui.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_learn.HomeActivity
import com.example.e_learn.R
import com.example.e_learn.Subject
import com.example.e_learn.SubjectsAdapter
import com.example.e_learn.databinding.FragmentSubjectsBinding

class Subjects : Fragment(),SubjectsAdapter.onItemClickListener {

    override fun onItemClick(subject: Subject) {
        when (subject.title){
            "Core Mathematics" ->{
                findNavController().navigate(R.id.action_nav_subject_to_nav_mathTopics)
            }
            "Integrated Science" ->{
                 Toast.makeText(requireContext(),"Clicked: ${subject.title}",Toast.LENGTH_SHORT).show()
            }
            "English Language" ->{
                Toast.makeText(requireContext(),"Clicked: ${subject.title}",Toast.LENGTH_SHORT).show()
            }
            "Social Studies" ->{
                findNavController().navigate(R.id.action_nav_subject_to_nav_socialTopics)
            }
        }

    }
    private var _binding: FragmentSubjectsBinding? = null
    private val binding get() = _binding!!
    private val subjectList = listOf(
        Subject("Core Mathematics"),
        Subject("Integrated Science"),
        Subject("English Language"),
        Subject("Social Studies")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSubjectsBinding.inflate(inflater,container,false)
        val rootView = binding.root

        (activity as HomeActivity).setAppBarTitle("Subjects")
        (activity as HomeActivity).updateFloatingActionButtonVisibility()

        val adapter = SubjectsAdapter(subjectList,this)
        binding.subList.adapter = adapter
        binding.subList.layoutManager = LinearLayoutManager(requireContext())

        return rootView
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}