package com.example.e_learn.ui.login.ui.answer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.repository.*
import com.example.e_learn.databinding.FragmentAnswerBinding
import com.example.e_learn.viewModels.BaseViewModelFactory

class AnswerFragment : Fragment() {
    private var _binding:FragmentAnswerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AnswerViewModel
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist =  ApiList.create()
    private lateinit var questionId:String
    private val sharedViewModel: SharedViewModel by activityViewModels()
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
        val ansRepo = AnswerRepository(apilist)
        viewModelFactory = BaseViewModelFactory(requireActivity().application,loginRepo,signupRepo,postRepo,comRepo,uQRepo,userRepo,ansRepo)
        viewModel = ViewModelProvider(this,viewModelFactory)[AnswerViewModel::class.java]
        _binding = FragmentAnswerBinding.inflate(inflater, container, false)
        val root:View = binding.root



        binding.answerList.layoutManager = LinearLayoutManager(requireContext())


        viewModel.answerResult.observe(this){Resource->
            if(Resource.isLoading()){
//                binding.loading3.visibility = View.VISIBLE
                Toast.makeText(requireContext(),"Loading answers", Toast.LENGTH_LONG).show()
            }
            else if (Resource.isSuccess()) {
//                binding.loading3.visibility = View.GONE
                val answers = Resource.data
                val adapter = AnswerAdapter(answers!!.answers,voteClickListener = {answer,isUpVote ->
                    countVote(answer._id,isUpVote)
                })
                binding.answerList.adapter = adapter
                adapter.setAnswers(answers)
                adapter.notifyDataSetChanged()
            }else if (Resource.isError()){
//                binding.loading3.visibility = View.GONE
//                binding.isError.visibility = View.VISIBLE
                Toast.makeText(requireContext(),"Failed to Load answers "+" Pull to Refresh", Toast.LENGTH_LONG).show()
            }
        }
        sharedViewModel.sharedData.observe(viewLifecycleOwner){data ->
            questionId = data._id
            Log.d("RECEIVED ID", questionId)
            viewModel.getAnswers(questionId)
        }



        return root
    }
    private fun countVote(answerId:String, isUpVote:Boolean){

    }

}