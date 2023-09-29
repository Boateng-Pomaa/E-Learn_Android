package com.example.e_learn.ui.login.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.repository.*
import com.example.e_learn.databinding.FragmentProfileBinding
import com.example.e_learn.ui.login.ui.community.FeedAdapter
import com.example.e_learn.ui.login.ui.mathquizzes.ScoreViewModel
import com.example.e_learn.utils.SharedPreferenceUtil
import com.example.e_learn.viewModels.BaseViewModelFactory

class ProfileFragment : Fragment() {
    private var _binding:FragmentProfileBinding? = null
    private val binding  get() = _binding!!
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist =  ApiList.create()
    private lateinit var viewModel: ProfileViewModel
    private lateinit var scoreViewModel:ScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginRepo = LoginRepository(apilist)
        val signupRepo = SignupRepository(apilist)
        val postRepo = PostRepository(apilist)
        val comRepo = FeedRepository(apilist)
        val userRepo = UserRepository(apilist)
        val ansRepo = AnswerRepository(apilist)
        val scoreRepo = ScoreRepository(apilist)
        viewModelFactory = BaseViewModelFactory(requireActivity().application,loginRepo,signupRepo,postRepo,comRepo,userRepo,ansRepo,scoreRepo)
        viewModel = ViewModelProvider(this,viewModelFactory)[ProfileViewModel::class.java]
        scoreViewModel = ViewModelProvider(this,viewModelFactory)[ScoreViewModel::class.java]
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val sharePref = SharedPreferenceUtil(requireContext())
        val userId = sharePref.retrieveData("userId").toString()
        Log.d("RETRIEVED",userId)

        binding.textView10.setOnClickListener {
            scoreViewModel.getScore(userId)
        }
        binding.scoreList.layoutManager = LinearLayoutManager(requireContext())
        viewModel.profile.observe(this) { Resource ->
            if (Resource.isLoading()) {
                Toast.makeText(requireContext(), "Loading Profile", Toast.LENGTH_LONG).show()
            } else if (Resource.isSuccess()) {
                val profile = Resource.data
                if (profile != null) {
                    binding.usernameText.text = profile.username
                    binding.emailText.text = profile.email
                } else if (Resource.isError()) {
                    Toast.makeText(requireContext(), "Failed to Load Feed " + " Pull to Refresh", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        scoreViewModel.saveResult.observe(this) { Resource ->
            if (Resource.isLoading()) {
                Toast.makeText(requireContext(), "Loading Scores", Toast.LENGTH_SHORT).show()
            } else if (Resource.isSuccess()) {
                val scores = Resource.data
                if (scores != null) {
                    val adapter = ScoreAdapter(scores.Score)
                    binding.scoreList.adapter = adapter
                    adapter.setScores(scores)
                    adapter.notifyDataSetChanged()
                } else if (Resource.isError()) {
                    binding.swipe.setOnRefreshListener {
                        scoreViewModel.getScore(userId)
                    }
                    Toast.makeText(requireContext(), "Failed to Load Scores \n Pull to Refresh", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        viewModel.getUser(userId)
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}