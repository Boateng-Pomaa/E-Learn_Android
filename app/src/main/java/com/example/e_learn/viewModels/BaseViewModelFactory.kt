package com.example.e_learn.viewModels
import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_learn.data.repository.*
import com.example.e_learn.ui.login.ui.PostQuestion.GalleryViewModel
import com.example.e_learn.ui.login.ui.answer.AnswerViewModel
import com.example.e_learn.ui.login.ui.community.SlideshowViewModel
import com.example.e_learn.ui.login.ui.profile.ProfileViewModel
import com.example.e_learn.ui.login.ui.profile.QuestionsViewModel


@Suppress("UNCHECKED_CAST")
class BaseViewModelFactory constructor(private val application: Application, private val repo:LoginRepository, private val repo2: SignupRepository, private val repo3:PostRepository, private val repo4:FeedRepository,private val repo5:UserQuestionsRepository, private val repo6: UserRepository,private val repo7:AnswerRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
               LoginViewModel(this.repo, application) as T
        }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(this.repo2) as T
            }
            modelClass.isAssignableFrom(GalleryViewModel::class.java) ->{
                GalleryViewModel(this.repo3) as T
            }
            modelClass.isAssignableFrom(SlideshowViewModel::class.java) ->{
                SlideshowViewModel(this.repo4) as T
            }
            modelClass.isAssignableFrom(QuestionsViewModel::class.java) ->{
                QuestionsViewModel(this.repo5) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) ->{
                ProfileViewModel(this.repo6) as T
            }
            modelClass.isAssignableFrom(AnswerViewModel::class.java) ->{
                AnswerViewModel(this.repo7) as T
            }
              else ->  throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}