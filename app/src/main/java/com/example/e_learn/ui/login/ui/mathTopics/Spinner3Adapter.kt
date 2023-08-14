package com.example.e_learn.ui.login.ui.mathTopics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.e_learn.R

class Spinner3Adapter (context: Context, topics3:List<String>) :
    ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,topics3) {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: inflater.inflate(R.layout.spinner_item_layout, parent, false)
        val topic = getItem(position)
        val textView = view.findViewById<TextView>(R.id.spinnerItemText)
        textView.text = topic.toString()
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: inflater.inflate(R.layout.spinner_dropdown_item_layout, parent, false)
        val topic = getItem(position)
        val textView = view.findViewById<TextView>(R.id.spinnerDropdownItemText)
        textView.text = topic.toString()
        return view
    }

}