package com.example.e_learn.ui.login.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_learn.R
import com.example.e_learn.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    private var _binding:FragmentNotificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentNotificationBinding.inflate(inflater, container, false)
        val root:View = binding.root


        return root
    }
}