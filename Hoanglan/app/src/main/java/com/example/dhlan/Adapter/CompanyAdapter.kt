package com.example.dhlan.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dhlan.Model.Company.Modelcompany
import com.example.dhlan.R
import kotlinx.android.synthetic.main.layout_company.view.*

class CompanyAdapter (private val context: Context, private val movieList: ArrayList<Modelcompany>?): RecyclerView.Adapter<CompanyAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.layout_company, parent,false)
        return HomeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movieList?.size?:0
    }

    override fun onBindViewHolder(holder: CompanyAdapter.HomeViewHolder, position: Int) {
      //  holder.txtcompany.text = movieList?.get(position)?.systemCode ?: ""
    }
    class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
      //  var txtcompany : TextView
        init {
         //   txtcompany = itemView.txtcompany
        }
    }
}


