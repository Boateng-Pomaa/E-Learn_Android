package com.example.e_learn.ui.login.ui.answer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.e_learn.HomeActivity
import com.example.e_learn.data.model.FeedModel
import com.example.e_learn.databinding.FragmentAnswerHostBinding
import com.google.android.material.tabs.TabLayoutMediator


class AnswerHostFragment : Fragment() {
    private var _binding:FragmentAnswerHostBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
         _binding = FragmentAnswerHostBinding.inflate(inflater, container, false)
        val root:View = binding.root
        // Observe the shared data
        (activity as HomeActivity).setAppBarTitle("Question")
        val adapter = AnswerHostAdapter(requireActivity())

        val received = arguments?.getParcelable<FeedModel>("question_Details")
        if (received != null) {
            sharedViewModel.setSharedData(received)
        }
        Log.d("RECEIVED_DATA", received.toString())


        adapter.addFragments(QuestionScrollingFragment(),"Question")
        adapter.addFragments(AnswerFragment(),"Answers")


        binding.viewPager.adapter = adapter
        // Link the TabLayout with the ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)

        }.attach()

        return root
    }
    }
