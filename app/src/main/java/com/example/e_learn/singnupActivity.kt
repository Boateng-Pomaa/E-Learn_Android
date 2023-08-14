package com.example.e_learn

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.repository.*
import com.example.e_learn.databinding.ActivitySingnupBinding
import com.example.e_learn.viewModels.BaseViewModelFactory
import com.example.e_learn.viewModels.SignUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class singnupActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivitySingnupBinding
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist = ApiList.create()
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingnupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginRepo = LoginRepository(apilist)
        val signupRepo = SignupRepository(apilist)
        val postRepo = PostRepository(apilist)
        val comRepo = FeedRepository(apilist)
        val userRepo = UserRepository(apilist)
        val uQRepo = UserQuestionsRepository(apilist)
        viewModelFactory = BaseViewModelFactory(application,loginRepo,signupRepo,postRepo,comRepo,uQRepo,userRepo)
        signUpViewModel = ViewModelProvider(this, viewModelFactory)[SignUpViewModel::class.java]

        signUpViewModel.signUpResult.observe(this) { Resource ->
           if (Resource.isLoading()){
               binding.loading1.visibility = View.VISIBLE
           }
            else if (Resource.isSuccess()) {
               binding.loading1.visibility = View.GONE
                val to = Intent(this, LoginActivity::class.java)
                startActivity(to)
               finish()
            } else if (Resource.isError()) {
               binding.loading1.visibility = View.GONE
                Toast.makeText(applicationContext, "Registration Failed", Toast.LENGTH_LONG).show()
            }
        }
        binding.btnSignup.setOnClickListener {
            launch {
                register()
            }
        }

        binding.textView6.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun register() {
        if (validation()) {
            val password = binding.editTextTextPassword2.text.toString().trim()
            val username = binding.editTextTextPersonName.text.toString().trim()
            val email = binding.emailTxt.text.toString().trim()
            val confirmPass = binding.confirmTxt.text.toString().trim()
            val json = JSONObject()
            json.put("username", username)
            json.put("password", password)
            json.put("email",email)
            if(password == confirmPass){
                signUpViewModel.signUp(username, password,email)
            }else{
                Toast.makeText(applicationContext, "Password Do Not Match", Toast.LENGTH_LONG).show()
            }


        }
   }

    private fun validation(): Boolean {
        var value = true

        val passe = binding.editTextTextPassword2.text.toString().trim()
        val user = binding.editTextTextPersonName.text.toString().trim()
        val email = binding.emailTxt.text.toString().trim()
        val confirmPass = binding.confirmTxt.text.toString().trim()


        if(passe.length < 6){
            binding.editTextTextPassword2.error = "Password must be 6 characters and above"
            binding.editTextTextPassword2.requestFocus()
            value = false
        }
        if (passe.isEmpty()) {
            binding.editTextTextPassword2.error = "Password required"
            binding.editTextTextPassword2.requestFocus()
            value = false
        }

        if (user.isEmpty()) {
            binding.editTextTextPersonName.error = "Username required"
            binding.editTextTextPersonName.requestFocus()
            value = false
        }
        if (confirmPass.isEmpty()){
            binding.editTextTextPassword2.error = "Confirm Password"
            binding.editTextTextPassword2.requestFocus()
            value = false
        }

        if (email.isEmpty()){
            binding.emailTxt.error ="Email is required"
            binding.emailTxt.requestFocus()
            value = false
        }

        return value
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}