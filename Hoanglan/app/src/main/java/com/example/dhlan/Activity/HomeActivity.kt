package com.example.dhlan.Activity

import android.app.AlertDialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dhlan.Adapter.HomeAdapter
import com.example.dhlan.Api.ApiInterface
import com.example.dhlan.Api.RetrofitClient
import com.example.dhlan.Model.Comment.HomeModelbody
import com.example.dhlan.Model.Comment.HomeViewModel
import com.example.dhlan.Model.Comment.ObjectHome
import com.example.dhlan.R
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response
import java.util.Locale.filter
import kotlin.math.log

class HomeActivity : AppCompatActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var Adapter: HomeAdapter
    lateinit var dialog: AlertDialog
    //
    private lateinit var  list: ArrayList<HomeViewModel>
    private lateinit var edittextsearch:EditText
    private lateinit var mprocessBar: ProgressBar
    //
    private lateinit var recyclerView: RecyclerView
//    var notLoadding = true
//    var page = 1
//    var isLoading = false
//    var limit = 5
    lateinit var sharedPreferences: SharedPreferences
    var imggaview: ImageView? = null
    var key = "thongtincomment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //
        edittextsearch = findViewById(R.id.txtsearch)
        mprocessBar = findViewById(R.id.progressbar)

        sharedPreferences = getSharedPreferences("SHARE_PRE", Context.MODE_PRIVATE);
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        recyclerview.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        this.dialog = SpotsDialog.Builder()
            .setContext(this)
            .setCancelable(false)
            .build()
//        getPage()
        getdata()
        loaddata()
        //
        edittextsearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(s: CharSequence?, i: Int, i1: Int, i2: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                //after the change calling the method and passing the search input
               fifterlist(s.toString());
            }
        })
//        addscrollistener()
        imggaview = findViewById(R.id.imgback) as ImageView
        imggaview!!.setOnClickListener {
            var editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            }
        }

    private fun fifterlist(item: String) {
            var itemList:MutableList<HomeViewModel> =  ArrayList()
        for (d in list){
            if (item in d.submitByName.toString()){
                    itemList.add(d)
            }
        }
        Adapter.updatelist(itemList)
    }

    private fun loaddata() {
        recyclerview.setOnClickListener(){
            val intent1 = Intent(applicationContext,loadcomment::class.java)

            startActivity(intent1)
        }
    }
    protected fun showProgressBar() {
        hideSoftKeyBoard()
        progressbar.visibility = View.VISIBLE
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
    private fun hideSoftKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (imm.isAcceptingText) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }
    protected fun hideProgressBar() {
        progressbar.visibility = View.GONE
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
        //show back inert
        private fun getdata() {
            dialog.show()
            //gọi acceestoken của homeAdapter

          //  val tokena = intent?.getStringExtra("accessToken") ?:""
            //
            mprocessBar.visibility = View.VISIBLE
            val retIn = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
            retIn.home(
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZHRwdGQiLCJhdXRoIjoiSlBNIzEwMDAwXzAwMDIsSlBNIzEwMDIwXzAwMDIsSlBNIzEwNDAwXzAwMDIsSlBNIzEwNTgwXzAwMDIsSlBNIzEwNjAwXzAwMDIsSlBNIzEwNjQwXzAwMDIsSERCYW5rI0NBUl9SRU5UQUwsSERCYW5rI0hEQmFua19Ub190cmluaF90dXllbl9kdW5nX3F1YW5fbHksSlBNIzEwMTYxXzAwMDIsSlBNIzEwMTYyXzAwMDIsSlBNIzEwMTY0XzAwMDIsSlBNIzEwMTY1XzAwMDIsSlBNIzEwMTc1XzAwMDIsSlBNIzEwMTc3XzAwMDIsSlBNIzEwMTg1XzAwMDIsSlBNIzEwMTg2XzAwMDIsSlBNIzEwMjAzXzAwMDIsSlBNIzEwMjA0XzAwMDIsSlBNIzEwMjA1XzAwMDIsSlBNIzEwMjA2XzAwMDIsSlBNIzEwMjA3XzAwMDIsSlBNIzEwMjIwXzAwMDIsSlBNIzEwMjQwXzAwMDIsSlBNIzEwMjYzXzAwMDIsSlBNIzEwMjY2XzAwMDIsSlBNIzEwMjY4XzAwMDIsSlBNIzEwMzAxXzAwMDIsSlBNIzEwMzIyXzAwMDIsSlBNIzEwMzYwXzAwMDIsSlBNIzEwMTYxXzAwMDYsSlBNIzEwMTYyXzAwMDYsSlBNIzEwMTY0XzAwMDYsSlBNIzEwMTY1XzAwMDYsSlBNIzEwMTc1XzAwMDYsSlBNIzEwMTc3XzAwMDYsSlBNIzEwMTg1XzAwMDYsSlBNIzEwMTg2XzAwMDYsSlBNIzEwMjAzXzAwMDYsSlBNIzEwMjA0XzAwMDYsSlBNIzEwMjA1XzAwMDYsSlBNIzEwMjA2XzAwMDYsSlBNIzEwMjA3XzAwMDYsSlBNIzEwMjIwXzAwMDYsSlBNIzEwMjQwXzAwMDYsSlBNIzEwMjYzXzAwMDYsSlBNIzEwMjY2XzAwMDYsSlBNIzEwMjY4XzAwMDYsSlBNIzEwMzAxXzAwMDYsSlBNIzEwMzIyXzAwMDYsSlBNIzEwMzYwXzAwMDYsRUYxI1MwM19NeURvY3VtZW50cyxFRjEjUzAzX015RG9jdW1lbnRzOkVkaXQsRUYxI1MwMl9TZXJ2aWNlQm9hcmQsRUYxI1MwMl9TZXJ2aWNlQm9hcmQ6RWRpdCxKUE0jMTAwMDJfMDAwMSxKUE0jMTAwMDNfMDAwMSxKUE0jMTAwNDBfMDAwMSxKUE0jMTAwNDNfMDAwMSxKUE0jMTAwMDBfMDAwMSxKUE0jMTAwMjBfMDAwMSxKUE0jMTA0MDBfMDAwMSxKUE0jMTA1ODBfMDAwMSxKUE0jMTA2MDBfMDAwMSxKUE0jMTA2NDBfMDAwMSxFRjAjUzAwX1Rhc2tTdGF0aXN0aWMsRUYwI1MwMF9UYXNrU3RhdGlzdGljOkVkaXQsSlBNIzEwMTkwXzAwMDQsSlBNIzEwMjAwXzAwMTMsSlBNIzEwMjIxXzAwMTMsSlBNIzEwMjI0XzAwMTMsSlBNIzEwMjYyXzAwMTMsSlBNIzEwMjY1XzAwMTMsSlBNIzEwMjY3XzAwMTMsSlBNIzEwMjgwXzAwMTMsSlBNIzEwMzAwXzAwMTMsSlBNIzEwMzAyXzAwMTMsSlBNIzEwMzIzXzAwMTMsSlBNIzEwMzgxXzAwMDIsSlBNIzEwMzgyXzAwMDIsSlBNIzEwNDAxXzAwMDIsSlBNIzEwNDAyXzAwMDIsSlBNIzEwNDAzXzAwMDIsSlBNIzEwNDIwXzAwMDIsSlBNIzEwNDQwXzAwMDIsSlBNIzEwNDQxXzAwMDIsSlBNIzEwNDYwXzAwMDIsSlBNIzEwNDYxXzAwMDIsSlBNIzEwNDgwXzAwMDIsSlBNIzEwNDgxXzAwMDIsSlBNIzEwNDg2XzAwMDIsSlBNIzEwNTIwXzAwMDIsSlBNIzEwNTQxXzAwMDIsSlBNIzEwNTQyXzAwMDIsSlBNIzEwNTQzXzAwMDIsSlBNIzEwNTQ1XzAwMDIsSlBNIzEwNTQ2XzAwMDIsSERCYW5rI1RvdHJpbmh0dXllbmR1bmd0aGVvbG8sSERCYW5rI0RhbmdreW5naGksUk9MRV9BVVRIRUQiLCJjb21wIjoyLCJleHAiOjE2MDI3MzA5NzksImxhbmd1YWdlIjoidmkiLCJ1c2VyTG9jYWwiOiIwIiwib3MiOiJpb3MiLCJ2ZXJzaW9uQXBwIjoiMS4wLjgyIn0.ZGGlolPImHKtni9vPd4t4SD7f6zsbp7-_jj_Liv7O1rFtePNrWHM8cJ4P9ithYF5BuWeSVcfesfDcAaVAgNLxw",
                HomeModelbody(
                    companyId = 2,
                    type = null,
                    pageSize = 10,
                    pageIndex = 1,
                    isPaging = 1
                )
            ).enqueue(object : retrofit2.Callback<ObjectHome> {

                override fun onFailure(call: Call<ObjectHome>, t: Throwable) {
                    mprocessBar.visibility = View.GONE
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    Log.i("error", t.message ?: "")
                }
                override fun onResponse(
                    call: Call<ObjectHome>,
                    response: Response<ObjectHome>
                ) {
                    var accessToken = response.body()?.result?.data

                    val movies = response.body()?.result?.data
                    Adapter = HomeAdapter(baseContext, movies)
                    Adapter.notifyDataSetChanged()
                    recyclerview.adapter = Adapter
                }
            })
            dialog.dismiss()
        }
}
