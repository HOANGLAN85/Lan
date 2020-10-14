package com.example.dhlan.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dhlan.Model.Loadcomment.LoadViewData
import com.example.dhlan.R
import kotlinx.android.synthetic.main.activity_loadcomment.view.*


class CommentAdapter (private val context: Context,
                      private var Motablelist: ArrayList<LoadViewData>?):
    RecyclerView.Adapter<CommentAdapter.LoadViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.LoadViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.activity_loadcomment, parent,false)
        return CommentAdapter.LoadViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return  Motablelist?.size?:0
    }

    override fun onBindViewHolder(holder: CommentAdapter.LoadViewHolder, position: Int) {
        holder.txtnguoigui.text = Motablelist?.get(position)?.staffName?.orEmpty()
        holder.txtvitri.text = Motablelist?.get(position)?.position?.orEmpty()
        holder.txtphongban.text = Motablelist?.get(position)?.departmentName?.orEmpty()
        holder.txttrangthai.text = Motablelist?.get(position)?.status?.orEmpty()
        holder.txtngaytao.text = Motablelist?.get(position)?.statusDate?.orEmpty()
        holder.txtngayhoanthien.text = Motablelist?.get(position)?.dueDate?.orEmpty()
        //   holder.txtuutien.text = list?.get(position)?.
        holder.txtloaiyeucau.text = Motablelist?.get(position)?.processName?.orEmpty()
    }

    class LoadViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        var txtnguoigui : TextView
        var txtvitri: TextView
        var txtphongban : TextView
        var txttrangthai: TextView
        var txtngaytao : TextView
        var txtngayhoanthien: TextView
        var txtuutien : TextView
        var txtloaiyeucau: TextView
        init {

            txtnguoigui = itemView.txtnguoigui
            txtvitri = itemView.txtvitri
            txtphongban =itemView.txtphongban
            txttrangthai = itemView.txttrangthai
            txtngaytao = itemView.txtngaytao
            txtngayhoanthien = itemView.txtngayhoanthien
            txtuutien = itemView.txtuutien
            txtloaiyeucau = itemView.txtloaiyeucau
        }
    }


    //    override fun onBindViewHolder(holder: CommentAdapter.LoadViewHolder, position: Int) {
//        holder.txtnguoigui.text = Motablelist?.get(position)?.staffName?.orEmpty()
//        holder.txtvitri.text = Motablelist?.get(position)?.position?.orEmpty()
//        holder.txtphongban.text = Motablelist?.get(position)?.departmentName?.orEmpty()
//        holder.txttrangthai.text = Motablelist?.get(position)?.status?.orEmpty()
//        holder.txtngaytao.text = Motablelist?.get(position)?.statusDate?.orEmpty()
//        holder.txtngayhoanthien.text = Motablelist?.get(position)?.dueDate?.orEmpty()
//        //   holder.txtuutien.text = list?.get(position)?.
//        holder.txtloaiyeucau.text = Motablelist?.get(position)?.processName?.orEmpty()
//    }
//
//    class LoadViewHolder(itemView: View) {
//        var txtnguoigui : TextView
//        var txtvitri: TextView
//        var txtphongban : TextView
//        var txttrangthai: TextView
//        var txtngaytao : TextView
//        var txtngayhoanthien: TextView
//        var txtuutien : TextView
//        var txtloaiyeucau: TextView
//        init {
//            txtnguoigui = itemView.txtnguoigui
//            txtvitri = itemView.txtvitri
//            txtphongban =itemView.txtphongban
//            txttrangthai = itemView.txttrangthai
//            txtngaytao = itemView.txtngaytao
//            txtngayhoanthien = itemView.txtngayhoanthien
//            txtuutien = itemView.txtuutien
//            txtloaiyeucau = itemView.txtloaiyeucau
//        }
//
//    }

}