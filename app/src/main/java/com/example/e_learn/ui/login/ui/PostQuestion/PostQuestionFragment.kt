package com.example.e_learn.ui.login.ui.PostQuestion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.e_learn.HomeActivity
import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.repository.*
import com.example.e_learn.databinding.FragmentGalleryBinding
import com.example.e_learn.utils.SharedPreferenceUtil
import com.example.e_learn.viewModels.BaseViewModelFactory
import org.json.JSONObject

class PostQuestionFragment : Fragment() {
    private lateinit var postViewModel:GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist =  ApiList.create()


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
        postViewModel = ViewModelProvider(this,viewModelFactory)[GalleryViewModel::class.java]
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val sharePref = SharedPreferenceUtil(requireContext())

        (activity as HomeActivity).updateFloatingActionButtonVisibility()
        (activity as HomeActivity).updateFloatingActionButtonActions()

        postViewModel.postResult.observe(this){
            Resource ->
            if(Resource.isLoading()){
                binding.loading2.visibility = View.VISIBLE
                Toast.makeText(requireContext(),"Posting Question", Toast.LENGTH_LONG).show()
            }else if (Resource.isSuccess()) {
                binding.loading2.visibility = View.GONE
                val res = Resource.data?._id.toString()
                sharePref.saveData("questionId",res)
                Toast.makeText(requireContext(),"Question Posted", Toast.LENGTH_LONG).show()
            }else if (Resource.isError()){
                binding.loading2.visibility = View.GONE
                Toast.makeText(requireContext(),"Make sure you are logged in\n Or check you internet connection", Toast.LENGTH_LONG).show()
            }
        }

        val userId = sharePref.retrieveData("userId").toString()
       Log.d("RETRIEVED",userId)
        binding.button2.setOnClickListener {
            val title = binding.editTextTextMultiLine2.text.toString().trim()
            val question = binding.editTextTextMultiLine.text.toString().trim()
            val json = JSONObject()
            json.put("title", title)
            json.put("question", question)
            postViewModel.postQuestion(userId,title, question)
             binding.editTextTextMultiLine2.text.clear()
             binding.editTextTextMultiLine.text.clear()
        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}