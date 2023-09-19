package com.example.e_learn.ui.login.ui.mathTopics

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.PopupWindow
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_learn.HomeActivity
import com.example.e_learn.R
import com.example.e_learn.databinding.FragmentSetsBinding
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener


class SetsFragment : Fragment(), OnPageScrollListener {
    private lateinit var popupButton:Button
    private  var popupWindow: PopupWindow? = null
    private var _binding: FragmentSetsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentSetsBinding.inflate(inflater, container, false)
        popupButton = inflater.inflate(R.layout.popup_button, null) as Button
        binding.pdfView.fromAsset("CORE_MATHEMATICS_FOR_SENIOR_HIGH_SCHOOLS.pdf")
            .password(null)
            .onPageScroll(this)
            .defaultPage(0)
            .load()

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as HomeActivity).setAppBarTitle("Sets And Operations on Sets")

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (popupWindow?.isShowing == true) {
                // If the popup window is showing, dismiss it
                popupWindow?.dismiss()
            } else {
                // If the popup window is not showing, handle the back button press as usual
                isEnabled = false
                requireActivity().onBackPressed()
            }
        }

    }


    override fun onPageScrolled(page: Int, positionOffset: Float) {
        val currentPage = page + 1
        val totalPages = binding.pdfView.pageCount

        if (currentPage == totalPages) {
            showPopupButton()
        } else {
            hidePopupButton()
        }
    }


    private fun showPopupButton() {

        val parentView = popupButton.parent as? ViewGroup
        parentView?.removeView(popupButton)

        popupWindow = PopupWindow(
            popupButton,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
//        popupWindow?.isOutsideTouchable = true
//        popupWindow?.isTouchable = true

        popupWindow?.setOnDismissListener {
            // Perform any necessary cleanup or additional actions
        }
        popupButton.setOnClickListener {
            showDialog()
            hidePopupButton()
        }
        popupWindow?.showAtLocation(requireView(), Gravity.CENTER, 0, 0)
    }

    private fun hidePopupButton() {
        if (popupWindow?.isShowing == true) {
            popupWindow?.dismiss()
        }
    }

    private fun showDialog(){
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle("READY TO TAKE THIS QUIZ?")
        alert.setMessage("ARE YOU SURE YOU ARE READY TO TAKE THIS QUIZ?\nMake sure you have covered the topics in order to do this!")
        alert.setPositiveButton("YES"){
                _, _: Int -> findNavController().navigate(R.id.action_nav_setsFragment_to_nav_setQuiz)
        }
        alert.setNegativeButton("NO"){
                _, _: Int ->Toast.makeText(requireContext(),"Good choice,learning makes a man perfect",Toast.LENGTH_SHORT).show()
        }
            .create().show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}