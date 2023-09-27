package com.example.e_learn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.repository.*
import com.example.e_learn.databinding.ActivityForgetPasswordBinding
import com.example.e_learn.utils.SharedPreferenceUtil
import com.example.e_learn.viewModels.BaseViewModelFactory
import com.example.e_learn.viewModels.PasswordViewModel
import com.example.e_learn.viewModels.ResetViewModel
import org.json.JSONObject

class ForgetPassword : AppCompatActivity() {
    private lateinit var passwordViewModel: PasswordViewModel
    private lateinit var resetViewModel:ResetViewModel
    private lateinit var binding:ActivityForgetPasswordBinding
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist =  ApiList.create()
    private lateinit var token:String
    private lateinit var userId:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginRepo = LoginRepository(apilist)
        val signupRepo = SignupRepository(apilist)
        val postRepo = PostRepository(apilist)
        val comRepo = FeedRepository(apilist)
        val userRepo = UserRepository(apilist)
        val ansRepo = AnswerRepository(apilist)
        val scoreRepo = ScoreRepository(apilist)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        viewModelFactory = BaseViewModelFactory(application,loginRepo,signupRepo,postRepo,comRepo,userRepo,ansRepo,scoreRepo)
        passwordViewModel = ViewModelProvider(this,viewModelFactory)[PasswordViewModel::class.java]
        resetViewModel = ViewModelProvider(this,viewModelFactory)[ResetViewModel::class.java]
        setContentView(binding.root)

        passwordViewModel.result.observe(this) { Resource ->
            if (Resource.isLoading()) {
                binding.loading5.visibility = View.VISIBLE
            } else if (Resource.isSuccess()) {
                val reset = "reset"
                binding.loading5.visibility = View.GONE
                binding.editTextTextEmailAddress.visibility = View.GONE
                binding.newPassword.visibility = View.VISIBLE
                binding.confirmNewPassword.visibility = View.VISIBLE
                binding.changePassword.text = reset
                Toast.makeText(this, "Verification Email Sent", Toast.LENGTH_LONG).show()
                token = Resource.data?.Token.toString()
                userId = Resource.data?.Id.toString()

            } else if (Resource.isError()) {
                binding.loading5.visibility = View.GONE
                Toast.makeText(this, "Failed to send verification email", Toast.LENGTH_LONG).show()
            }
        }
        resetViewModel.results.observe(this){ Resource ->
            if (Resource.isLoading()) {
                binding.loading5.visibility = View.VISIBLE
            } else if (Resource.isSuccess()) {

                Toast.makeText(this, "Password Reset", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()

            } else if (Resource.isError()) {
                binding.loading5.visibility = View.GONE
                Toast.makeText(this, "Failed to Reset Password", Toast.LENGTH_LONG).show()
            }
        }

        binding.changePassword.setOnClickListener {
            val btnText = binding.changePassword.text.toString()
            if(btnText == "Send"){
           val email =  binding.editTextTextEmailAddress.text.toString()
            passwordViewModel.requestPassword(email)
            }else{
                val newPass = binding.newPassword.text.toString()
                val confirmPass = binding.confirmNewPassword.text.toString()
                val json = JSONObject()
                json.put("password", newPass)
                if(newPass == confirmPass){
                    Log.d("TOKEN",token)
                    Log.d("ID",userId)
                    resetViewModel.resetPassword(userId,token,newPass)
                }else{
                    Toast.makeText(this, "Password Do Not Match", Toast.LENGTH_LONG).show()
               }

            }
        }


    }
}