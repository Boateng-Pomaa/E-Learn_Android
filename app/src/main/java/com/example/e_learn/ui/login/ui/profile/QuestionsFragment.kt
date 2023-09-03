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
import com.example.e_learn.databinding.FragmentQuestionsBinding
import com.example.e_learn.utils.SharedPreferenceUtil
import com.example.e_learn.viewModels.BaseViewModelFactory

class QuestionsFragment : Fragment() {
    private var _binding: FragmentQuestionsBinding? = null
    private  val binding get() = _binding!!
    private lateinit var viewModel: QuestionsViewModel
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist =  ApiList.create()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQuestionsBinding.inflate( inflater, container, false)
        val root:View = binding.root
        val loginRepo = LoginRepository(apilist)
        val signupRepo = SignupRepository(apilist)
        val postRepo = PostRepository(apilist)
        val comRepo = FeedRepository(apilist)
        val userRepo = UserRepository(apilist)
        val uQRepo = UserQuestionsRepository(apilist)
        val ansRepo = AnswerRepository(apilist)
        viewModelFactory = BaseViewModelFactory(requireActivity().application,loginRepo,signupRepo,postRepo,comRepo,uQRepo,userRepo,ansRepo)
        viewModel = ViewModelProvider(this,viewModelFactory)[QuestionsViewModel::class.java]

        val adapter = QuestionAdapter()
        binding.feedList.layoutManager = LinearLayoutManager(requireContext())
        binding.feedList.adapter = adapter

        viewModel.feeds.observe(this) { Resource ->
            if(Resource.isLoading()){
                binding.loading3.visibility = View.VISIBLE
                Toast.makeText(requireContext(),"Loading Feed", Toast.LENGTH_LONG).show()
            }
            else if (Resource.isSuccess()) {
                binding.loading3.visibility = View.GONE
                val posts = Resource.data
                if (posts != null) {
                    adapter.setData(posts)
                    adapter.notifyDataSetChanged()
                }
            }else if (Resource.isError()){
                binding.loading3.visibility = View.GONE
                binding.imageView3.visibility = View.VISIBLE
                binding.isError.visibility = View.VISIBLE
                Toast.makeText(requireContext(),"Failed to Load Feed "+" Pull to Refresh", Toast.LENGTH_LONG).show()
            }
        }
        val sharePref = SharedPreferenceUtil(requireContext())
        val userId = sharePref.retrieveData("userId").toString()
        Log.d("RETRIEVED",userId)

        viewModel.getUserQuestions(userId)
        return root
    }


}