package com.example.e_learn.ui.login.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.repository.*
import com.example.e_learn.databinding.FragmentProfileBinding
import com.example.e_learn.utils.SharedPreferenceUtil
import com.example.e_learn.viewModels.BaseViewModelFactory

class ProfileFragment : Fragment() {
    private var _binding:FragmentProfileBinding? = null
    private val binding  get() = _binding!!
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist =  ApiList.create()
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginRepo = LoginRepository(apilist)
        val signupRepo = SignupRepository(apilist)
        val postRepo = PostRepository(apilist)
        val comRepo = FeedRepository(apilist)
        val userRepo = UserRepository(apilist)
        val uQRepo = UserQuestionsRepository(apilist)
        viewModelFactory = BaseViewModelFactory(requireActivity().application,loginRepo,signupRepo,postRepo,comRepo,uQRepo,userRepo)
        viewModel = ViewModelProvider(this,viewModelFactory)[ProfileViewModel::class.java]
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.profile.observe(this) { Resource ->
            if (Resource.isLoading()) {
//                binding.loading3.visibility = View.VISIBLE
                Toast.makeText(requireContext(), "Loading Profile", Toast.LENGTH_LONG).show()
            } else if (Resource.isSuccess()) {
//                binding.loading3.visibility = View.GONE
                val profile = Resource.data
                if (profile != null) {
                    binding.usernameText.text = profile.data.username
                    binding.emailText.text = profile.data.email
                } else if (Resource.isError()) {
//                binding.loading3.visibility = View.GONE
//                binding.imageView3.visibility = View.VISIBLE
//                binding.isError.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), "Failed to Load Feed " + " Pull to Refresh", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        val userDetails = SharedPreferenceUtil.getUserData(requireContext())
        val userId = userDetails?.id
        if (userId != null) {
            Log.i("RETRIEVED", userId)
        }
        if (userId != null) {
            viewModel.getUser(userId)
        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}