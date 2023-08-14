package com.example.e_learn

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.repository.*
import com.example.e_learn.databinding.ActivityLoginBinding
import com.example.e_learn.utils.SharedPreferenceUtil
import com.example.e_learn.viewModels.BaseViewModelFactory
import com.example.e_learn.viewModels.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class LoginActivity : AppCompatActivity(),CoroutineScope {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist =  ApiList.create()
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val loginRepo = LoginRepository(apilist)
        val signupRepo = SignupRepository(apilist)
        val postRepo = PostRepository(apilist)
        val comRepo = FeedRepository(apilist)
        val userRepo = UserRepository(apilist)
        val uQRepo = UserQuestionsRepository(apilist)
        viewModelFactory = BaseViewModelFactory(application,loginRepo,signupRepo,postRepo,comRepo,uQRepo,userRepo)
        loginViewModel = ViewModelProvider(this,viewModelFactory)[LoginViewModel::class.java]
        val userDetails = SharedPreferenceUtil.getUserData(this)
        val userId = userDetails?.id
        if (userId != null) {
            Log.i("RETRIEVED", userId)
        }
        loginViewModel.loginResult.observe(this) { Resource ->
            if(Resource.isLoading()){
                binding.loading.visibility = VISIBLE
            }
            else if (Resource.isSuccess()) {
//                val userData = Resource.data?.userdata
                binding.loading.visibility = GONE
//                val sharedPreferencesUtil = SharedPreferenceUtil(this)
//                if (userData != null) {
//                    Log.i("USERID", userData._id)
//                    sharedPreferencesUtil.saveDate("userId", userData._id)
//                }
//                if (userData != null) {
//                    Log.i("USERID", userData.username)
//                    sharedPreferencesUtil.saveDate("username", userData.username)
//                }
//                if (userData != null) {
//                    sharedPreferencesUtil.saveDate("token", userData.token)
//                }

                val o = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(o)
                finish()
            }else if (Resource.isError()){
                binding.loading.visibility = GONE
                Toast.makeText(applicationContext,"Login Failed", Toast.LENGTH_LONG).show()
            }
        }
        binding.login.setOnClickListener {
            launch {
                login()
            }

         }
        binding.txtSignup.setOnClickListener {
            val reg = Intent(this@LoginActivity,singnupActivity::class.java)
            startActivity(reg)
            finish()
        }
            }


        private fun login (){
            if (validation()) {
                val username = binding.username.text.toString().trim()
                val password = binding.password.text.toString().trim()
                val json = JSONObject()
                json.put("username", username)
                json.put("password", password)
                loginViewModel.doLogin(username,password)

                        }
                    }


        private fun validation(): Boolean {
            var value = true

            val passe = binding.password.text.toString().trim()
            val user = binding.username.text.toString().trim()

            if (passe.isEmpty()) {
                binding.password.error = "Password required"
                binding.password.requestFocus()
                value = false
            }

            if (user.isEmpty()) {
                binding.username.error = "Username required"
                binding.username.requestFocus()
                value = false
            }

            return value
        }
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    }