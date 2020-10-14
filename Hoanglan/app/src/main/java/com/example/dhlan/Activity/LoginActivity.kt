package com.example.dhlan.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.dhlan.Adapter.CompanyAdapter
import com.example.dhlan.Adapter.HomeAdapter
import com.example.dhlan.Adapter.customeSpinnerAdapter
import com.example.dhlan.Api.ApiInterface
import com.example.dhlan.Api.RetrofitClient
import com.example.dhlan.Model.Company.Objectdata
import com.example.dhlan.Model.Login.LoginModelbody
import com.example.dhlan.Model.Login.ObjectLogin
import com.example.dhlan.R
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

    lateinit var sharedPreferences: SharedPreferences
    lateinit var Adapter: customeSpinnerAdapter
    lateinit var dialog: android.app.AlertDialog
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //hàm dialog
        dialog = SpotsDialog.Builder()
            .setContext(this)
            .setCancelable(false)
            .build()
        Spinnerdata()
//        preferences.edit().putString("token",).apply();
        sharedPreferences = getSharedPreferences("SHARE_PRE", Context.MODE_PRIVATE)
        //login
        btnsubmit.setOnClickListener {
            val systemcode = edt_namecompany.toString().trim()
            val email = edt_mail.text.toString().trim()
            val password = edt_password.text.toString().trim()

            if (systemcode.isEmpty()) {
                edt_namecompany.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                edt_mail.error = "Email required"
                edt_mail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                edt_password.error = "Password required"
                edt_password.requestFocus()
                return@setOnClickListener
            }
            val retIn = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
            retIn.addUser(LoginModelbody(email, password, "iPhone 7", "vi", "android", "1.0"))
                .enqueue(object : retrofit2.Callback<ObjectLogin> {
                    override fun onFailure(call: Call<ObjectLogin>, t: Throwable) {
                        Toast.makeText(
                            this@LoginActivity,
                            t.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        //Log.i("error", t.message?:"")
                    }

                    override fun onResponse(
                        call: Call<ObjectLogin>, response: Response<ObjectLogin>
                    ) {
                        response.body()?.result?.userInfo
                        Log.i(
                            "error onResponse",
                            response.code().toString() + "status " + response.body()?.status ?: ""
                        )
                        if (response.code() == 200 && response.body()?.status == "SUCCESS") {
                            //accessetoken
                            val accessToken = response.body()?.result?.accessToken
                            val refreshToken = response.body()?.result?.refreshToken
                            intent.putExtra("accessToken", accessToken)

                            // lưu giữ password khi đăng nhập
                            val edit = sharedPreferences.edit()
                            edit.putString("accessToken", accessToken)
                            edit.putString("refreshToken", refreshToken)
                            edit.putBoolean("isLogin", true)
                            edit.apply()
                            //
                            val intent = Intent(applicationContext, HomeActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            Toast.makeText(
                                this@LoginActivity,
                                "Login thành công!",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Sai tai khoan hoac mat khau!",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                })
        }
    }
    private fun Spinnerdata(){
        dialog.show()
        val retIn = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
        retIn.Company().enqueue(object : retrofit2.Callback<Objectdata> {
            override fun onFailure(call: Call<Objectdata>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<Objectdata>, response: Response<Objectdata>) {
                if (response.isSuccessful!!){
                    val states = response.body()?.result
                    val stateArray = arrayListOf<String>(states?.get(0)!!.systemCode.toString())
                    for (i in 0..(states.size-1)) {
                        stateArray.add(states.get(i).systemCode.toString())
                    }
                    val spinner = findViewById<Spinner>(R.id.edt_namecompany)
                    val aa = ArrayAdapter<String>(this@LoginActivity, android.R.layout.simple_list_item_1, stateArray)
//                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = aa
//                    Adapter = customeSpinnerAdapter(baseContext, states)
//                    Adapter.notifyDataSetChanged()
                    // custum spinner
//                    with(spinner)
//                    {
//                        adapter = aa
//                        setSelection(0, false)
//                        // onItemSelectedListener = this@LoginActivity
//                        prompt = "Select your favourite language"
//                        gravity = Gravity.CENTER
//
//                    }
//                    val ll = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//                    ll.setMargins(10, 40, 10, 10)
                    //

                    val options = stateArray
                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            Toast.makeText(this@LoginActivity, " You select >> "+options[position], Toast.LENGTH_SHORT).show();
                        }
                        override fun onNothingSelected(parent: AdapterView<*>) {
                            // sometimes you need nothing here
                        }
                    }
                }
            }
        })
        dialog.dismiss()
    }
}

//&& response.body()?.status == "SUCCESS"
//            val service = RetrofitClient.retrofitInstance?.create(ApiInterface::class.java)
//            val refreshToken = "yourRefreshToken"
//            val call = service?.refreshToken(refreshToken)
//            call?.enqueue(object: Callback<ObjectLogin> {
//                override fun onFailure(call: Call<ObjectLogin>, t: Throwable) {
//                    print("throw Message"+t.message)
//                    Toast.makeText(applicationContext,"Error reading JSON",Toast.LENGTH_LONG).show()
//                }
//
//                override fun onResponse(call: Call<ObjectLogin>, response: Response<ObjectLogin>) {
//                    val body = response?.body()
//                    if(body!=null){
//                        //do your work
//                    }
//                }
//
//            })
        // intent sang activity or fragment
//        btnsubmit.setOnClickListener {
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//        }
//
//        //ánh xạ id bên layout
  //      nametext = findViewById(R.id.edt_namecompany) as
//        loginButton = findViewById(R.id.btnsubmit) as Button
//        passwordText = findViewById(R.id.edt_password) as EditText
//        emailText = findViewById(R.id.edt_mail) as EditText


