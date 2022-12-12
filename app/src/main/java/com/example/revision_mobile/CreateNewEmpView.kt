package com.example.revision_mobile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class CreateNewEmpView : ViewModel() {

    lateinit var createNewEmpLiveData: MutableLiveData<EmployeeResponse?>
    lateinit var loadEmpData: MutableLiveData<EmployeeResponse?>
    lateinit var deleteEmpLiveData: MutableLiveData<EmployeeResponse?>

    init {
        createNewEmpLiveData = MutableLiveData()
        loadEmpData = MutableLiveData()
        deleteEmpLiveData = MutableLiveData()
    }
    fun getCreateNewEmpObservable(): MutableLiveData<EmployeeResponse?> {
        return  createNewEmpLiveData
    }

    fun getDeleteEmpObservable(): MutableLiveData<EmployeeResponse?> {
        return  deleteEmpLiveData
    }

    fun getLoadEmpObservable(): MutableLiveData<EmployeeResponse?> {
        return  loadEmpData
    }

    fun createEmp(employee: Employee){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.createEmployee(employee)
        call.enqueue(object  : Callback<EmployeeResponse?>{
            override fun onFailure(call: Call<EmployeeResponse?>, t: Throwable) {
                createNewEmpLiveData.postValue(null)
            }
            override fun onResponse(call: Call<EmployeeResponse?>, response: Response<EmployeeResponse?>) {
            if(response.isSuccessful){
                createNewEmpLiveData.postValue(response.body())
            }else{
                createNewEmpLiveData.postValue(null)
            }
            }
        })
    }

    fun updateEmp(id_emp : String ,employee: Employee){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.updateEmployee(id_emp , employee)
        call.enqueue(object  : Callback<EmployeeResponse?>{
            override fun onFailure(call: Call<EmployeeResponse?>, t: Throwable) {
                createNewEmpLiveData.postValue(null)
            }
            override fun onResponse(call: Call<EmployeeResponse?>, response: Response<EmployeeResponse?>) {
                if(response.isSuccessful){
                    createNewEmpLiveData.postValue(response.body())
                }else{
                    createNewEmpLiveData.postValue(null)
                }
            }
        })
    }

    fun deleteEmp(id_emp : String?){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.deleteEmployee(id_emp!!)
        call.enqueue(object  : Callback<EmployeeResponse?>{
            override fun onFailure(call: Call<EmployeeResponse?>, t: Throwable) {
                deleteEmpLiveData.postValue(null)
            }
            override fun onResponse(call: Call<EmployeeResponse?>, response: Response<EmployeeResponse?>) {
                if(response.isSuccessful){
                    deleteEmpLiveData.postValue(response.body())
                }else{
                    deleteEmpLiveData.postValue(null)
                }
            }
        })
    }
    fun getEmpData(id_emp : String?){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getEmployee(id_emp!!)
        call.enqueue(object  : Callback<EmployeeResponse?>{
            override fun onFailure(call: Call<EmployeeResponse?>, t: Throwable) {
                loadEmpData.postValue(null)
            }
            override fun onResponse(call: Call<EmployeeResponse?>, response: Response<EmployeeResponse?>) {
                if(response.isSuccessful){
                    loadEmpData.postValue(response.body())
                }else{
                    loadEmpData.postValue(null)
                }
            }
        })
    }


}