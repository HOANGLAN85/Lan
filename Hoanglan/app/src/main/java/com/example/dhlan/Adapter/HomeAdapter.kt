package com.example.dhlan.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dhlan.Model.Comment.HomeViewModel
import com.example.dhlan.R
import com.google.gson.internal.bind.util.ISO8601Utils.format
import kotlinx.android.synthetic.main.activity_loadcomment.view.*
import kotlinx.android.synthetic.main.layout_fragment_home.view.*
import okhttp3.internal.http.HttpDate.format
import java.lang.String.format
import java.text.DateFormat
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*
import java.util.zip.Inflater
import kotlin.collections.ArrayList

class HomeAdapter(
    private val context: Context,
    private var list: ArrayList<HomeViewModel>?
): RecyclerView.Adapter<HomeAdapter.HomeviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeviewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.layout_fragment_home, parent, false)
        return HomeviewHolder(itemView)

    }
    override fun getItemCount(): Int {
        return list?.size?:0
    }
    override fun onBindViewHolder(holder: HomeviewHolder, position: Int) {
        holder.txtcomment.text = list?.get(position)?.submitByName?.orEmpty()
        holder.txtpeople.text = String.format("Người gửi: " + list?.get(position)?.submitByPosition?.orEmpty())
        var date = list?.get(position)?.dueDate?:""
      //  holder.txtdate.text =  DateFormat.format("hh:mm:ss", Date(date))
        holder.txttype.text = String.format("- " + list?.get(position)?.docState?.orEmpty())
    }
    class HomeviewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var txtcomment: TextView
        var txtpeople: TextView
        var txtdate: TextView
        var txttype: TextView
        init {
            txtcomment = itemView.txtcomment
            txtpeople = itemView.txtpepole
            txtdate = itemView.txtdate
            txttype = itemView.txttype
        }

    }
    fun updatelist(view: ArrayList<HomeViewModel>){
        list = view
        notifyDataSetChanged()
    }
//    fun getFilter(): Filter {
//        return object : Filter() {
//            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
//                homeviewHolder = p1?.values as ArrayList<HomeViewModel>
//                notifyDataSetChanged()
//            }
//            override fun performFiltering(charSequence: CharSequence): FilterResults {
//                val charString = charSequence.toString()
//                Log.d("MainAdapter", charString)
//                //if query string is empty add all in filtered list
//                if (charString.isEmpty()) {
//                    homeviewHolder = list
//                } else {
//                    var filteredList = ArrayList<HomeViewModel>()
//                    if (list != null) {
//                        for (row in list!!) {
//                            // if found query string add country in filtered lsit
//                            if (row.submitByName?.toLowerCase()!!.contains(charString.toLowerCase())) {
//                                filteredList!!.add(row)
//                            }
//                        }
//                    }
//
//                    homeviewHolder = filteredList
//                }
//
//                val filter = FilterResults()
//                filter.values = homeviewHolder
//                return filter
//            }
//        }
//    }

}