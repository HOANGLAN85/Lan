package com.example.dhlan.Activity

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dhlan.Adapter.CompanyAdapter
import com.example.dhlan.Api.ApiInterface
import com.example.dhlan.Api.RetrofitClient
import com.example.dhlan.Model.Company.Objectdata
import com.example.dhlan.R
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_company.*
import retrofit2.Call
import retrofit2.Response

class CompanyActivity : AppCompatActivity() {
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var Adapter: CompanyAdapter
    lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company)

        recyclerview.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = linearLayoutManager
        dialog  = SpotsDialog.Builder()
            .setContext(this)
            .setCancelable(false)
            .build()
        getdata()
    }
    private fun getdata() {
        dialog.show()
        val retIn = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
        retIn.Company().enqueue(object : retrofit2.Callback<Objectdata> {
            override fun onFailure(call: Call<Objectdata>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<Objectdata>,
                response: Response<Objectdata>
            ) {
                Adapter = CompanyAdapter(baseContext, response.body()?.result)
                Adapter.notifyDataSetChanged()
                recyclerview.adapter = Adapter
                dialog.dismiss()
            }
        })
    }
}