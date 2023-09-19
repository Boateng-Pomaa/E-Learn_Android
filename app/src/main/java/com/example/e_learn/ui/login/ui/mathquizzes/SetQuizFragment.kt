package com.example.e_learn.ui.login.ui.mathquizzes

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.e_learn.R
import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.repository.*
import com.example.e_learn.databinding.FragmentSetQuizBinding
import com.example.e_learn.utils.SharedPreferenceUtil
import com.example.e_learn.viewModels.BaseViewModelFactory

class SetQuizFragment : Fragment() {
    private var _binding:FragmentSetQuizBinding? = null
    private val binding get() = _binding!!
    private lateinit var questions: List<Question>
    private var currentQuestionIndex = 0
    private var score = 0
    private var isAnsweredCorrectly = false
    private lateinit var currentQuestionView: View
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist =  ApiList.create()
    private lateinit var viewModel: ScoreViewModel
    @RequiresApi(Build.VERSION_CODES.KITKAT)
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
        viewModel = ViewModelProvider(this,viewModelFactory)[ScoreViewModel::class.java]
        _binding = FragmentSetQuizBinding.inflate(inflater, container, false)
        val root:View = binding.root

        loadQuestions()
        displayQuestion(root)
        setupSubmitButton(root)


        return root
    }
    private fun loadQuestions() {
        // Load questions from JSON (use the readJsonFile function from previous answer)
        questions = readJsonFromAssets(requireContext(),"SetQuestions.json")
    }
    private fun readJsonFromAssets(context: Context, fileName: String): List<Question> {
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        val parser = Parser.default()
        val json = parser.parse(StringBuilder(jsonString)) as JsonObject
        val questions = json.array<JsonObject>("Quiz")?.map { questionJson ->
            val question = questionJson.string("question") ?: ""
            val options = questionJson.array<String>("options") ?: emptyList()
            val correctOption = questionJson.int("correctOption") ?: -1
            Question(question, options, correctOption)
        } ?: emptyList()
        return questions
    }

    private fun displayQuestion(view: View){
        val questionTextView = binding.questionTextView
        val optionsRadioGroup: RadioGroup = binding.optionsRadioGroup
        currentQuestionView = view

        if (currentQuestionIndex < questions.size) {
            val correctOptionIndex = questions[currentQuestionIndex].correctOption
            for (i in 0 until optionsRadioGroup.childCount) {
                val radioButton = optionsRadioGroup.getChildAt(i) as RadioButton
                if (i == correctOptionIndex) {
                    radioButton.setTextColor(resources.getColor(R.color.green))
                } else {
                    radioButton.setTextColor(resources.getColor(R.color.red))
                }
            }
        }
        val currentQuestion = questions[currentQuestionIndex]
        questionTextView.text = currentQuestion.question
        // Clear previously displayed options
        optionsRadioGroup.removeAllViews()
        for (i in currentQuestion.options.indices) {
            val radioButton = RadioButton(context)
            radioButton.text = currentQuestion.options[i]
            optionsRadioGroup.addView(radioButton)
        }

    }

    private fun setupSubmitButton(view: View) {
        currentQuestionView = view
        binding.submitButton.setOnClickListener {
            binding.optionsRadioGroup.isEnabled = false
            val selectedOptionId = binding.optionsRadioGroup.checkedRadioButtonId
            if (selectedOptionId != -1) {
                val selectedRadioButton =
                    currentQuestionView.findViewById<RadioButton>(selectedOptionId)
                val selectedOption = selectedRadioButton.text.toString()

                val correctOption =
                    questions[currentQuestionIndex].options[questions[currentQuestionIndex].correctOption]
                isAnsweredCorrectly = selectedOption == correctOption
                // Update the score if the answer is correct
                if (isAnsweredCorrectly) {
                    score += 5 // Increment by 5 for each correct answer
                    binding.txtScore.text = score.toString()
                }
                markCorrectOption()
                // Increment the question index and display the next question
                currentQuestionIndex++
                if (currentQuestionIndex < questions.size) {
                    displayQuestion(view)
                } else {
                    val quiz = "Sets Level 1"
                    val sharePref = SharedPreferenceUtil(requireContext())
                    val userId = sharePref.retrieveData("userId").toString()
                    viewModel.saveScore(userId,score,quiz)
                    // All questions answered, display a message or navigate to a result screen
                    Toast.makeText(context, "Quiz completed!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, "Please select an answer", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun markCorrectOption() {
        val correctRadioButton = binding.optionsRadioGroup.getChildAt(questions[currentQuestionIndex].correctOption) as RadioButton
        correctRadioButton.isChecked = true
    }
    data class Question(val question: String, val options: List<String>, val correctOption: Int)

}