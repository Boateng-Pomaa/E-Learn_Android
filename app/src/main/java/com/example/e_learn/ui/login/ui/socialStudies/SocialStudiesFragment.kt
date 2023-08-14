package com.example.e_learn.ui.login.ui.socialStudies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_learn.databinding.FragmentSocialStudiesBinding
import com.example.e_learn.ui.login.ui.mathTopics.*


class SocialStudiesFragment : Fragment() {
    private var _binding:FragmentSocialStudiesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       
        _binding = FragmentSocialStudiesBinding.inflate(inflater, container, false)

        val topics = SocialData.getData().map { it.topic }
        val adapter = Spinner1Adapter(requireContext(),topics)
        binding.socialSpinner.adapter = adapter

        val yearTwoTopics = SocialStudies.getData().map { it.topic }
        val adapter2 = Spinner2Adapter(requireContext(),yearTwoTopics)
        binding.socialSpinner2.adapter = adapter2

        val yearThreeTopics = SocialStudies3.getData().map { it.topic }
        val adapter3 = Spinner3Adapter(requireContext(),yearThreeTopics)
        binding.socialSpinner3.adapter = adapter3


        return binding.root
    }

}