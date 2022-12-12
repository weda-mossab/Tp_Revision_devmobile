package com.example.revision_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), RecycleAdapter.OnItemClickListener {
    lateinit var recycleAdapter: RecycleAdapter
    lateinit var viewModel: MainViewModel
    private lateinit var recyclerView : RecyclerView
    lateinit var createbtn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        createbtn = findViewById(R.id.createUserButton)
        initRecyclerView()
        initViewModel()

        createbtn.setOnClickListener {
            lunchCreate()
        }

    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recycleAdapter = RecycleAdapter(this@MainActivity)
            adapter = recycleAdapter

        }
    }

    private fun lunchCreate(){
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        startActivityForResult(intent, 1000)
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getEmployeeListObservable().observe(this, Observer<EmployeeList> {
            if(it == null) {
                Toast.makeText(this@MainActivity, "no result found...", Toast.LENGTH_LONG).show()
            } else {
                recycleAdapter.employeeList = it.data.toMutableList()
                recycleAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getEmployeeList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1000) {
            viewModel.getEmployeeList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onItemEditCLick(employee: Employee) {
       // Toast.makeText(this@MainActivity, "Clicked...", Toast.LENGTH_LONG).show()
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        intent.putExtra("id", employee.id)
        startActivityForResult(intent, 1000)
    }
}