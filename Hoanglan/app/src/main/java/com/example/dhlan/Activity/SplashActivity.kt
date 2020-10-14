package com.example.dhlan.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.dhlan.Api.ApiInterface
import com.example.dhlan.Api.RetrofitClient
import com.example.dhlan.Model.Login.LoginModelbody
import com.example.dhlan.Model.Login.ObjectLogin
import com.example.dhlan.Model.RefreshToken.ObjectToken
import com.example.dhlan.Model.RefreshToken.Refreshtokenbody
import com.example.dhlan.R
import retrofit2.Call
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //start main activity
        Handler().postDelayed({
            val sharedPreferences = getSharedPreferences("SHARE_PRE", Context.MODE_PRIVATE)
            val isLogin = sharedPreferences.getBoolean("isLogin", false)
            if (isLogin) {
                val refresh = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
                val refreshToken = intent?.getStringExtra("refreshToken") ?:""
                refresh.refreshToken(Refreshtokenbody(refreshToken,"","android","9.9.16")).enqueue(object : retrofit2.Callback<ObjectToken> {
                    override fun onFailure(call: Call<ObjectToken>, t: Throwable) {
                        Toast.makeText(
                            this@SplashActivity,
                            t.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    override fun onResponse(
                        call: Call<ObjectToken>,
                        response: Response<ObjectToken>
                    ) {
                        response.body()?.result?.userInfo
                        Log.i(
                            "error onResponse",
                            response.code().toString() + "status " + response.body()?.status
                        )
                        //4second splash time
                        Handler().postDelayed({
                            val sharedPreferences = getSharedPreferences("SHARE_PRE", Context.MODE_PRIVATE)
                            val isLogin = sharedPreferences.getBoolean("isLogin", false)
                            if (isLogin) {
                                //refreshtoken
                                var accessToken = response.body()?.result?.accessToken
                                var refreshToken = response.body()?.result?.refreshToken
                                val edit = sharedPreferences.edit()
                                edit.putString("accessToken", accessToken)
                                edit.putString("refreshToken", refreshToken)
                                edit.apply()
                                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                            } else {
                                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                            }
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                            //finish this activity
                            finish()
                        }, 3000)
                    }
                })
            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            //finish this activity
            finish()
        }, 3000)

    }
}




