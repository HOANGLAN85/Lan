//package com.example.dhlan.ui.home
//
//import android.app.AlertDialog
//import android.content.Intent.getIntent
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ListAdapter
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProviders
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.dhlan.Adapter.HomeAdapter
//import com.example.dhlan.Api.ApiInterface
//import com.example.dhlan.Api.RetrofitClient
//import com.example.dhlan.Model.Comment.HomeModelbody
//import com.example.dhlan.Model.Comment.HomeViewModel
//import com.example.dhlan.Model.Comment.ObjectHome
//import com.example.dhlan.R
//import dmax.dialog.SpotsDialog
//import kotlinx.android.synthetic.main.activity_company.*
//import kotlinx.android.synthetic.main.fragment_home.*
//import org.w3c.dom.Comment
//import retrofit2.Call
//import retrofit2.Response
//
//
//class HomeFragment : Fragment() {
//
//    private lateinit var homeViewModel: HomeViewModel
//    lateinit var linearLayoutManager: LinearLayoutManager
//    private lateinit var Adapter: HomeAdapter
//    lateinit var dialog: AlertDialog
//    val ListAdapter: ListAdapter?=null
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        //
//
//
//    this.dialog = SpotsDialog.Builder()
//        .setContext(context)
//        .setCancelable(false)
//        .build()
////        Comment()
//
////        getdata()
//        return root
//    }
//
////    private fun getdata() {
////        dialog.show()
////        //gọi acceestoken của homeAdapter
////        val intent = activity?.intent
////        val tokena = intent?.getStringExtra("accessToken") ?:""
////        //
////        val retIn = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
////        retIn.home("Bearer + $tokena",HomeModelbody(companyId=2,type=null,pageSize=10,pageIndex=1,isPaging=1)).enqueue(object : retrofit2.Callback<ObjectHome> {
////
////            override fun onFailure(call: Call<ObjectHome>, t: Throwable) {
////                Toast.makeText(activity,t.message,Toast.LENGTH_LONG).show()
////                Log.i("error", t.message?:"")
////            }
////            override fun onResponse(
////                call: Call<ObjectHome>,
////                response: Response<ObjectHome>
////            ) {
////                val movies = response.body()?.result?.data
////                activity?.let {
////                    Adapter = HomeAdapter(it.baseContext, movies)
////                    Adapter.notifyDataSetChanged()
////                    recyclerviewhome.adapter = Adapter
//////                    recyclerviewhome.setHasFixedSize(true)
//////                    recyclerviewhome.layoutManager = LinearLayoutManager(activity)
////  //                  recyclerviewhome.adapter = HomeAdapter(movies)
////                }
////
////            }
////        })
////
////        dialog.dismiss()
////    }
////   private fun Comment() {
////        recyclerviewhome.setHasFixedSize(true)
////        recyclerviewhome.layoutManager = LinearLayoutManager(activity)
////        recyclerviewhome.adapter = HomeAdapter(Adapter)
////    }
//
//}