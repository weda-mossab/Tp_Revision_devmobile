package com.example.revision_mobile

// Product informations
data class EmployeeList(val data: List<Employee>)
data class Employee (var id:String,var employee_name: String, var employee_salary: Float, var employee_age:Int)
data class EmployeeResponse(val code: Int?, val meta: String?, val data: Employee?)