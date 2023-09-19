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

        (activity as HomeActivity).updateFloatingActionButtonVisibility()
        (activity as HomeActivity).updateFloatingActionButtonActions()

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as HomeActivity).setAppBarTitle("Sets And Operations on Sets")

    }

    override fun onPageScrolled(page: Int, positionOffset: Float) {
//        val currentPage = page + 1
//        val totalPages = binding.pdfView.pageCount

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}