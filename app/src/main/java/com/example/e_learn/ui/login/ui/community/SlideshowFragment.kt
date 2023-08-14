package com.example.e_learn.ui.login.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.repository.*
import com.example.e_learn.databinding.FragmentSlideshowBinding
import com.example.e_learn.viewModels.BaseViewModelFactory
import org.json.JSONObject

class SlideshowFragment: Fragment() {
    private lateinit var feedViewModel:SlideshowViewModel
    private var _binding: FragmentSlideshowBinding? = null
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist =  ApiList.create()
    private val binding get() = _binding!!

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
        val uQRepo = UserQuestionsRepository(apilist)
        viewModelFactory = BaseViewModelFactory(requireActivity().application,loginRepo,signupRepo,postRepo,comRepo,uQRepo,userRepo)
         feedViewModel = ViewModelProvider(this,viewModelFactory)[SlideshowViewModel::class.java]
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.imageButton.setOnClickListener{
            search()
        }
        val adapter = FeedAdapter()
        binding.feedList.layoutManager = LinearLayoutManager(requireContext())
        binding.feedList.adapter = adapter

        feedViewModel.feeds.observe(this) { Resource ->
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
                binding.editTextTextMultiLine3.visibility = View.INVISIBLE
                binding.imageButton.visibility = View.INVISIBLE
                binding.imageView3.visibility = View.VISIBLE
                binding.isError.visibility = View.VISIBLE
                Toast.makeText(requireContext(),"Failed to Load Feed "+" Pull to Refresh", Toast.LENGTH_LONG).show()
            }
        }

        feedViewModel.getFeed()
        return root
    }

   private fun search(){
        val searchTitle = binding.editTextTextMultiLine3.text.toString()
        val json = JSONObject()
        json.put("keyword",searchTitle)
        feedViewModel.search(searchTitle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}