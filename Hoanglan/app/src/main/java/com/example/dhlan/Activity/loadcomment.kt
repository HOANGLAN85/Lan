package com.example.dhlan.Activity

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dhlan.Adapter.CommentAdapter
import com.example.dhlan.Adapter.HomeAdapter
import com.example.dhlan.Api.ApiInterface
import com.example.dhlan.Api.RetrofitClient
import com.example.dhlan.Model.Comment.HomeModelbody
import com.example.dhlan.Model.Comment.HomeViewModel
import com.example.dhlan.Model.Comment.ObjectHome
import com.example.dhlan.Model.Loadcomment.LoadViewData
import com.example.dhlan.Model.Loadcomment.Loadmodelbody
import com.example.dhlan.Model.Loadcomment.ObjectLoad
import com.example.dhlan.R
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Response

class loadcomment : AppCompatActivity() {
    var nguoigui: TextView? = null
    var vitri: TextView? =null
    var phongban: TextView? = null
    var trangthai: TextView? =null
    var ngaytao: TextView? = null
    var ngayhoanthien: TextView?= null
    var uuten: TextView? = null
    var loaiyeucau: TextView? =  null
    lateinit var layoutManager: LinearLayoutManager
    lateinit var Adapter: CommentAdapter
    lateinit var dialog: AlertDialog
    lateinit var  list: ArrayList<LoadViewData>
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loadcomment)
                this.dialog = SpotsDialog.Builder()
            .setContext(this)
            .setCancelable(false)
            .build()
        anhxa()
        getdata()
    }

    private fun anhxa() {
        nguoigui = findViewById(R.id.txtnguoigui)
        vitri = findViewById(R.id.txtvitri)
        phongban = findViewById(R.id.txtphongban)
        trangthai = findViewById(R.id.txttrangthai)
        ngaytao = findViewById(R.id.txtngaytao)
        ngayhoanthien = findViewById(R.id.txtngayhoanthien)
        uuten = findViewById(R.id.txtuutien)
        loaiyeucau = findViewById(R.id.txtloaiyeucau)
    }
    private fun getdata() {
        dialog.show()
        //gọi acceestoken của homeAdapter

        //  val tokena = intent?.getStringExtra("accessToken") ?:""
        //
        val retIn = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
        retIn.Load(
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZHRwdGQiLCJhdXRoIjoiSlBNIzEwMDAwXzAwMDIsSlBNIzEwMDIwXzAwMDIsSlBNIzEwNDAwXzAwMDIsSlBNIzEwNTgwXzAwMDIsSlBNIzEwNjAwXzAwMDIsSlBNIzEwNjQwXzAwMDIsSERCYW5rI0NBUl9SRU5UQUwsSERCYW5rI0hEQmFua19Ub190cmluaF90dXllbl9kdW5nX3F1YW5fbHksSlBNIzEwMTYxXzAwMDIsSlBNIzEwMTYyXzAwMDIsSlBNIzEwMTY0XzAwMDIsSlBNIzEwMTY1XzAwMDIsSlBNIzEwMTc1XzAwMDIsSlBNIzEwMTc3XzAwMDIsSlBNIzEwMTg1XzAwMDIsSlBNIzEwMTg2XzAwMDIsSlBNIzEwMjAzXzAwMDIsSlBNIzEwMjA0XzAwMDIsSlBNIzEwMjA1XzAwMDIsSlBNIzEwMjA2XzAwMDIsSlBNIzEwMjA3XzAwMDIsSlBNIzEwMjIwXzAwMDIsSlBNIzEwMjQwXzAwMDIsSlBNIzEwMjYzXzAwMDIsSlBNIzEwMjY2XzAwMDIsSlBNIzEwMjY4XzAwMDIsSlBNIzEwMzAxXzAwMDIsSlBNIzEwMzIyXzAwMDIsSlBNIzEwMzYwXzAwMDIsSlBNIzEwMTYxXzAwMDYsSlBNIzEwMTYyXzAwMDYsSlBNIzEwMTY0XzAwMDYsSlBNIzEwMTY1XzAwMDYsSlBNIzEwMTc1XzAwMDYsSlBNIzEwMTc3XzAwMDYsSlBNIzEwMTg1XzAwMDYsSlBNIzEwMTg2XzAwMDYsSlBNIzEwMjAzXzAwMDYsSlBNIzEwMjA0XzAwMDYsSlBNIzEwMjA1XzAwMDYsSlBNIzEwMjA2XzAwMDYsSlBNIzEwMjA3XzAwMDYsSlBNIzEwMjIwXzAwMDYsSlBNIzEwMjQwXzAwMDYsSlBNIzEwMjYzXzAwMDYsSlBNIzEwMjY2XzAwMDYsSlBNIzEwMjY4XzAwMDYsSlBNIzEwMzAxXzAwMDYsSlBNIzEwMzIyXzAwMDYsSlBNIzEwMzYwXzAwMDYsRUYxI1MwM19NeURvY3VtZW50cyxFRjEjUzAzX015RG9jdW1lbnRzOkVkaXQsRUYxI1MwMl9TZXJ2aWNlQm9hcmQsRUYxI1MwMl9TZXJ2aWNlQm9hcmQ6RWRpdCxKUE0jMTAwMDJfMDAwMSxKUE0jMTAwMDNfMDAwMSxKUE0jMTAwNDBfMDAwMSxKUE0jMTAwNDNfMDAwMSxKUE0jMTAwMDBfMDAwMSxKUE0jMTAwMjBfMDAwMSxKUE0jMTA0MDBfMDAwMSxKUE0jMTA1ODBfMDAwMSxKUE0jMTA2MDBfMDAwMSxKUE0jMTA2NDBfMDAwMSxFRjAjUzAwX1Rhc2tTdGF0aXN0aWMsRUYwI1MwMF9UYXNrU3RhdGlzdGljOkVkaXQsSlBNIzEwMTkwXzAwMDQsSlBNIzEwMjAwXzAwMTMsSlBNIzEwMjIxXzAwMTMsSlBNIzEwMjI0XzAwMTMsSlBNIzEwMjYyXzAwMTMsSlBNIzEwMjY1XzAwMTMsSlBNIzEwMjY3XzAwMTMsSlBNIzEwMjgwXzAwMTMsSlBNIzEwMzAwXzAwMTMsSlBNIzEwMzAyXzAwMTMsSlBNIzEwMzIzXzAwMTMsSlBNIzEwMzgxXzAwMDIsSlBNIzEwMzgyXzAwMDIsSlBNIzEwNDAxXzAwMDIsSlBNIzEwNDAyXzAwMDIsSlBNIzEwNDAzXzAwMDIsSlBNIzEwNDIwXzAwMDIsSlBNIzEwNDQwXzAwMDIsSlBNIzEwNDQxXzAwMDIsSlBNIzEwNDYwXzAwMDIsSlBNIzEwNDYxXzAwMDIsSlBNIzEwNDgwXzAwMDIsSlBNIzEwNDgxXzAwMDIsSlBNIzEwNDg2XzAwMDIsSlBNIzEwNTIwXzAwMDIsSlBNIzEwNTQxXzAwMDIsSlBNIzEwNTQyXzAwMDIsSlBNIzEwNTQzXzAwMDIsSlBNIzEwNTQ1XzAwMDIsSlBNIzEwNTQ2XzAwMDIsSERCYW5rI1RvdHJpbmh0dXllbmR1bmd0aGVvbG8sSERCYW5rI0RhbmdreW5naGksUk9MRV9BVVRIRUQiLCJjb21wIjoyLCJleHAiOjE2MDI3MzA5NzksImxhbmd1YWdlIjoidmkiLCJ1c2VyTG9jYWwiOiIwIiwib3MiOiJpb3MiLCJ2ZXJzaW9uQXBwIjoiMS4wLjgyIn0.ZGGlolPImHKtni9vPd4t4SD7f6zsbp7-_jj_Liv7O1rFtePNrWHM8cJ4P9ithYF5BuWeSVcfesfDcAaVAgNLxw",
            Loadmodelbody(
                companyId = 2,
                type = null,
                pageSize = 10,
                pageIndex = 1,
                isPaging = 1
            )
        ).enqueue(object : retrofit2.Callback<ObjectLoad> {

            override fun onFailure(call: Call<ObjectLoad>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                Log.i("error", t.message ?: "")
            }
            override fun onResponse(
                call: Call<ObjectLoad>,
                response: Response<ObjectLoad>
            ) {
                var accessToken = response.body()?.result?.data

                val movies = response.body()?.result?.data
                Adapter = CommentAdapter(baseContext, movies)
                Adapter.notifyDataSetChanged()
            }
        })
        dialog.dismiss()
    }
}