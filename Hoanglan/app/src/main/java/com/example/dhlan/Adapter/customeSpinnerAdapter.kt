package com.example.dhlan.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.dhlan.R
import kotlinx.android.synthetic.main.layout_company.view.*

class customeSpinnerAdapter : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.layout_company,parent)
        view.txtspinner.setText(company)
        return view
    }


    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return 0
    }

    lateinit var company: String

    //Add this
    lateinit var inflater: LayoutInflater

    fun customeSpinnerAdapter(context: Context, name: String) {
        this.company = name
        //val inflater  = LayoutInflater.from(context)
        inflater = LayoutInflater.from(context)
    }

}






