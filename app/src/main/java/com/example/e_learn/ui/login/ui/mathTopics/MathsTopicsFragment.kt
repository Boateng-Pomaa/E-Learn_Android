package com.example.e_learn.ui.login.ui.mathTopics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_learn.HomeActivity
import com.example.e_learn.R
import com.example.e_learn.databinding.FragmentMathsTopicsBinding


class MathsTopicsFragment : Fragment() ,MathsTopicsAdapter.onItemSelectedListener,
    AdapterView.OnItemSelectedListener {

    override fun onItemSelected(maths: String) {
        when (maths) {
            "SETS AND OPERATIONS ON SETS" ->{
                findNavController().navigate(R.id.action_nav_mathTopics_to_nav_setsFragment)
            }
            "REAL NUMBER SYSTEM" ->{
                findNavController().navigate(R.id.action_nav_mathTopics_to_nav_realNumbers)

            }
            "ALGEBRAIC EXPRESSIONS" ->{
                findNavController().navigate(R.id.action_nav_mathTopics_to_nav_algebra)
                Toast.makeText(requireContext(),"Clicked: $maths",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private var _binding: FragmentMathsTopicsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMathsTopicsBinding.inflate(inflater, container, false)
        val rootView = binding.root

        (activity as HomeActivity).setAppBarTitle("Core Mathematics")

        val yearTwoTopics = MathTopics2.getData().map { it.topic }
        val adapter2 = Spinner2Adapter(requireContext(),yearTwoTopics)
        binding.mathsSpinner2.adapter = adapter2

        val yearThreeTopics = MathTopics3.getData().map { it.topic }
        val adapter3 = Spinner3Adapter(requireContext(),yearThreeTopics)
        binding.mathsSpinner3.adapter = adapter3

        val topics = MathTopics.getData().map { it.topic }
        val adapter = MathsTopicsAdapter(requireContext(), topics)
        binding.mathsSpinner.adapter = adapter

       binding.mathsSpinner.onItemSelectedListener = this

        return rootView
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


    private var isFirstSelection = true
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(isFirstSelection){
            isFirstSelection = false
        }else {
            val initialPosition = 0
            if(position != initialPosition){
                val selectedItem = binding.mathsSpinner.getItemAtPosition(position)
                onItemSelected(selectedItem as String)
            }
            binding.mathsSpinner.setSelection(initialPosition)

        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
