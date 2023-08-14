package com.example.e_learn.ui.login.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_learn.HomeActivity
import com.example.e_learn.databinding.FragmentHostBinding
import com.google.android.material.tabs.TabLayoutMediator


class HostFragment : Fragment() {
    private var _binding:FragmentHostBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHostBinding.inflate(inflater, container, false)
        val root: View = binding.root
        (activity as HomeActivity).setAppBarTitle("Profile")
        val adapter = FragmentAdapter(requireActivity())
        // Add your child fragments to the adapter
        adapter.addFragment(ProfileFragment(), "Profile")
        adapter.addFragment(QuestionsFragment(),"Your Questions")
        adapter.addFragment(NotificationFragment(),"Your Answers")
        // Set the adapter to the ViewPager2
        binding.viewPager.adapter = adapter
        // Link the TabLayout with the ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()

        return root
    }

}