package com.example.e_learn.ui.login.ui.answer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.e_learn.data.model.FeedModel

class AnswerHostAdapter (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()

    fun addFragments(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }
    
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}