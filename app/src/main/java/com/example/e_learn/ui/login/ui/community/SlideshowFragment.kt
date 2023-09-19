package com.example.e_learn.ui.login.ui.community

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_learn.HomeActivity
import com.example.e_learn.R
import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.model.FeedModel
import com.example.e_learn.data.repository.*
import com.example.e_learn.databinding.FragmentSlideshowBinding
import com.example.e_learn.viewModels.BaseViewModelFactory
import org.json.JSONObject

class SlideshowFragment: Fragment(),FeedAdapter.OnItemClickListener {
    private lateinit var feedViewModel:SlideshowViewModel
    private var _binding: FragmentSlideshowBinding? = null
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist =  ApiList.create()
    private val binding get() = _binding!!


    //sset the adapter outside observer
    override fun onItemClick(feed:FeedModel) {
        Log.d("FEEEED", feed.toString())
        val bundle = Bundle().apply {
            putParcelable("question_Details",feed)
        }
        findNavController().navigate(R.id.action_nav_slideshow_to_answerHost, bundle)
    }

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
        val ansRepo = AnswerRepository(apilist)
        val scoreRepo = ScoreRepository(apilist)
        viewModelFactory = BaseViewModelFactory(requireActivity().application,loginRepo,signupRepo,postRepo,comRepo,uQRepo,userRepo,ansRepo,scoreRepo)
         feedViewModel = ViewModelProvider(this,viewModelFactory)[SlideshowViewModel::class.java]
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        (activity as HomeActivity).updateFloatingActionButtonVisibility()
        binding.imageButton.setOnClickListener{
            search()
            //adapter.notifyDataSetChanged()
        }

        binding.feedList.layoutManager = LinearLayoutManager(requireContext())


        feedViewModel.feeds.observe(this) { Resource ->
            if(Resource.isLoading()){
                binding.loading3.visibility = View.VISIBLE
                Toast.makeText(requireContext(),"Loading Feed", Toast.LENGTH_LONG).show()
            }
            else if (Resource.isSuccess()) {
                binding.loading3.visibility = View.GONE
                val posts = Resource.data
                if (posts != null) {
                    val adapter = FeedAdapter(posts.feeds,this)
                    binding.feedList.adapter = adapter
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