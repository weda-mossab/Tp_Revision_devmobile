package com.example.revision_mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class RecycleAdapter(private val clickListener: OnItemClickListener): RecyclerView.Adapter<RecycleAdapter.MyViewHolder>() {

    var employeeList = mutableListOf<Employee>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycle_row_list, parent, false)
        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewAge = view.findViewById<TextView>(R.id.textViewAge)
        val textViewSalary = view.findViewById<TextView>(R.id.textViewSalary)

        fun bind(data : Employee) {
            textViewName.text = data.employee_name
            textViewSalary.text = data.employee_salary.toString()
            textViewAge.text = data.employee_age.toString()
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(employeeList[position])
    holder.itemView.setOnClickListener{
        clickListener.onItemEditCLick(employeeList[position])
    }
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    interface OnItemClickListener {
        fun onItemEditCLick(employee: Employee)
    }
}