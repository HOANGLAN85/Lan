package com.example.dhlan.ui.home

import androidx.lifecycle.MutableLiveData
import com.google.gson.annotations.Expose

class Model {
    @Expose(serialize = false, deserialize = false)
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
}