package com.example.e_learn.ui.login.ui.answer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.repository.*
import com.example.e_learn.databinding.FragmentQuestionScrollingBinding
import com.example.e_learn.utils.SharedPreferenceUtil
import com.example.e_learn.viewModels.BaseViewModelFactory
import org.json.JSONObject

class QuestionScrollingFragment : Fragment() {
    private  var _binding:FragmentQuestionScrollingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist =  ApiList.create()
    private lateinit var viewModel:AnswerViewModel
    private var questionId: String = ""
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
        viewModel = ViewModelProvider(this,viewModelFactory)[AnswerViewModel::class.java]
        _binding = FragmentQuestionScrollingBinding.inflate(inflater, container, false)
        val root:View = binding.root
        val sharePref = SharedPreferenceUtil(requireContext())

        sharedViewModel.sharedData.observe(viewLifecycleOwner) { data ->
            // Handle the shared data here
            binding.txtTitle.text = data.title
            binding.txtDescription.text = data.question
            binding.txtUser1.text = data.username
            binding.answerBtn.text = data.createdAt
            questionId = data._id
            Log.d("RECEIVED DATA", data.question)
        }
        viewModel.answerResult.observe(this){Resource->
            if(Resource.isLoading()){
//                binding.loading7.visibility = View.VISIBLE
                Toast.makeText(requireContext(),"Recording Answer", Toast.LENGTH_SHORT).show()
                binding.editTextTextMultiLine4.text.clear()
            }
            else if (Resource.isSuccess()) {
                Toast.makeText(requireContext(),"Answer Recorded!", Toast.LENGTH_LONG).show()

            }else if (Resource.isError()){
//                binding.loading7.visibility = View.GONE
//                binding.isError.visibility = View.VISIBLE
                Toast.makeText(requireContext(),"Failed Try Again", Toast.LENGTH_SHORT).show()
            }
        }


        val userId = sharePref.retrieveData("userId").toString()
        binding.answerBtn.setOnClickListener{
            binding.button3.visibility = View.VISIBLE
            binding.editTextTextMultiLine4.visibility = View.VISIBLE
        }

        binding.button3.setOnClickListener {
            val content = binding.editTextTextMultiLine4.text.toString().trim()
            val json = JSONObject()
            json.put("content", content)
            Log.d("QUESTION_ID",questionId)
           viewModel.answerPost(userId,questionId,content)
        }

        return root
    }
}