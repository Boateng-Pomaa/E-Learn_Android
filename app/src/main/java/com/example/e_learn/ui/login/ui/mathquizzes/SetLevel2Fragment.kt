package com.example.e_learn.ui.login.ui.mathquizzes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.e_learn.HomeActivity
import com.example.e_learn.R
import com.example.e_learn.data.api.ApiList
import com.example.e_learn.data.repository.*
import com.example.e_learn.databinding.FragmentSetLevel2Binding
import com.example.e_learn.utils.SharedPreferenceUtil
import com.example.e_learn.viewModels.BaseViewModelFactory


class SetLevel2Fragment : Fragment() {
   private var _binding:FragmentSetLevel2Binding? = null
    private val binding get() = _binding!!
    private lateinit var questions: List<Question>
    private var currentQuestionIndex = 0
    private var score = 0
    private var isAnsweredCorrectly = false
    private lateinit var currentQuestionView: View
    private lateinit var viewModelFactory: BaseViewModelFactory
    private var apilist =  ApiList.create()
    private lateinit var viewModel: ScoreViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
        viewModel = ViewModelProvider(this,viewModelFactory)[ScoreViewModel::class.java]
        _binding = FragmentSetLevel2Binding.inflate(inflater, container, false)
        val root = binding.root

        (activity as HomeActivity).updateFloatingActionButtonVisibility()
        loadQuestions()
        displayQuestion(root)
        setupSubmitButton(root)

        return root
    }
    private fun loadQuestions() {
        // Load questions from JSON (use the readJsonFile function from previous answer)
        questions = readJsonFromAssets(requireContext(),"Setquiz2.json")
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
                    binding.txtScore1.text = score.toString()
                }
                markCorrectOption()
                // Increment the question index and display the next question
                currentQuestionIndex++
                if (currentQuestionIndex < questions.size) {
                    displayQuestion(view)
                } else {
                    val quiz = "Sets Level 2"
                    val sharePref = SharedPreferenceUtil(requireContext())
                    val userId = sharePref.retrieveData("userId").toString()
                    viewModel.saveScore(userId,score,quiz)
                    // All questions answered, display a message or navigate to a result screen
                    val bundle = Bundle().apply {
                        putInt("Score",score)
                    }
                    findNavController().navigate(R.id.action_setLevel2_to_quizComplete,bundle)

                }
            }else{
                Toast.makeText(context, "Please select an answer", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun markCorrectOption() {
        val correctRadioButton = binding.optionsRadioGroup.getChildAt(questions[currentQuestionIndex].correctOption) as RadioButton
        correctRadioButton.isChecked = true
    }

    data class Question(val question: String, val options: List<String>, val correctOption: Int)
}