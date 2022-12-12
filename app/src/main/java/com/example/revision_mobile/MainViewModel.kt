package com.example.revision_mobile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class MainViewModel : ViewModel() {

    lateinit var recyclerListData: MutableLiveData<EmployeeList>
    
    init {
    recyclerListData = MutableLiveData()        
    }

    fun getEmployeeListObservable(): MutableLiveData<EmployeeList> {
        return  recyclerListData
    }
    
    fun getEmployeeList() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService:: class.java)
        val call = retroInstance.getEmployeeList()
        
        call.enqueue(object : Callback<EmployeeList> {
            override fun onFailure (call: Call<EmployeeList>, t:Throwable){
                recyclerListData.postValue(null)
            }
            
            override fun onResponse(call: Call<EmployeeList>, response: Response<EmployeeList>){
            if (response.isSuccessful){
                recyclerListData.postValue(response.body())
            }else{
                recyclerListData.postValue(null)
            }
            }
        })
    }
}