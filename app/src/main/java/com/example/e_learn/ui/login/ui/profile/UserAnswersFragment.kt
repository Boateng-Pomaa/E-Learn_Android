package com.example.e_learn.ui.login.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.repository.*
import com.example.e_learn.databinding.FragmentNotificationBinding
import com.example.e_learn.ui.login.ui.answer.AnswerAdapter
import com.example.e_learn.ui.login.ui.answer.AnswerViewModel
import com.example.e_learn.utils.SharedPreferenceUtil
import com.example.e_learn.viewModels.BaseViewModelFactory

class NotificationFragment : Fragment() {
    private var _binding:FragmentNotificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var answerviewModel: AnswerViewModel
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist =  ApiList.create()
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
        answerviewModel = ViewModelProvider(this,viewModelFactory)[AnswerViewModel::class.java]
        _binding =  FragmentNotificationBinding.inflate(inflater, container, false)
        val root:View = binding.root
        val sharePref = SharedPreferenceUtil(requireContext())
        val userId = sharePref.retrieveData("userId").toString()
        binding.userAnsList.layoutManager = LinearLayoutManager(requireContext())
        answerviewModel.answerResult.observe(this){Resource->
            if(Resource.isLoading()){
//                binding.loading3.visibility = View.VISIBLE
                Toast.makeText(requireContext(),"Loading answers", Toast.LENGTH_LONG).show()
            }
            else if (Resource.isSuccess()) {
//                binding.loading3.visibility = View.GONE
                val answers = Resource.data
                val adapter = UserAnswersAdapter(answers!!.answers)
                binding.userAnsList.adapter = adapter
                adapter.setAnswers(answers)
                adapter.notifyDataSetChanged()
            }else if (Resource.isError()){
//                binding.loading3.visibility = View.GONE
//                binding.isError.visibility = View.VISIBLE
                Toast.makeText(requireContext(),"Failed to Load answers \n Pull to Refresh", Toast.LENGTH_LONG).show()
                binding.swipeRefreshLayout2.setOnRefreshListener {
                    answerviewModel.userAnswers(userId)
                }
            }
        }

        Log.d("RETRIEVED",userId)
        answerviewModel.userAnswers(userId)

        return root
    }
}