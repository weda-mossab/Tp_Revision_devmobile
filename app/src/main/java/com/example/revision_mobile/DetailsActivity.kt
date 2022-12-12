package com.example.revision_mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.lang.System.exit


class DetailsActivity : AppCompatActivity() {
    lateinit var viewModel: CreateNewEmpView
    var  id:String? = null
    lateinit var emp_salary : EditText
    lateinit var emp_age : EditText
    lateinit var emp_name : EditText
    lateinit var createbtn:Button
    lateinit var deletebtn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)

         id = intent.getStringExtra("id")
         emp_name = findViewById(R.id.emp_name)
         emp_salary = findViewById(R.id.emp_salary)
         emp_age = findViewById(R.id.emp_age)
         createbtn = findViewById(R.id.createbtn)
         deletebtn = findViewById(R.id.deletebtn)

         viewModel = ViewModelProvider(this).get(CreateNewEmpView::class.java)
        if(id!=null){
            loadEmpData(id)
        }
        createbtn.setOnClickListener {
            createEmp(id)
        }

    }

    private fun loadEmpData(id : String?){
        viewModel.getLoadEmpObservable().observe(this, Observer <EmployeeResponse?>{
            if (it != null){
            emp_name.setText(it.data?.employee_name)
            emp_salary.setText(it.data?.employee_salary.toString())
            emp_age.setText(it.data?.employee_age.toString())
            }
        })
        viewModel.getEmpData(id)
    }

    private fun createEmp(id: String?){
        val emp = Employee("", emp_name.text.toString(),emp_salary.text.toString().toFloat(), emp_age.text.toString().toInt())
        if(id== null){
            viewModel.getCreateNewEmpObservable().observe(this, Observer<EmployeeResponse?>{
                if (it != null){
                    Toast.makeText(this@DetailsActivity, "Done...", Toast.LENGTH_LONG).show()

                    finish()
                }
                else{
                    Toast.makeText(this@DetailsActivity, "Failed...", Toast.LENGTH_LONG).show()
                }
            })
            viewModel.createEmp(emp)

        }else{
            viewModel.getCreateNewEmpObservable().observe(this, Observer<EmployeeResponse?>{
                if (it != null){
                    Toast.makeText(this@DetailsActivity, "Done...", Toast.LENGTH_LONG).show()

                    finish()
                }
                else{
                    Toast.makeText(this@DetailsActivity, "Failed...", Toast.LENGTH_LONG).show()
                }
            })

        viewModel.updateEmp(id , emp)
        }

}
}