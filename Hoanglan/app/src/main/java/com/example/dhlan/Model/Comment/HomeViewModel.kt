package com.example.dhlan.Model.Comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeViewModel (var caterogy: String){
    //
    @SerializedName("documentId")
    var documentId: Int? =  null
    @SerializedName("submitByName")
    var submitByName: String? = null
    @SerializedName("submitByPosition")
    var submitByPosition: String? = null
    @SerializedName("dueDate")
    var dueDate: String? = null
    @SerializedName("docState")
    var docState: String? = null
    init {
        this.caterogy = caterogy
    }
}