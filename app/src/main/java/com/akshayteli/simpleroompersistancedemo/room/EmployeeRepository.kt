package com.akshayteli.simpleroompersistancedemo.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

import com.akshayteli.simpleroompersistancedemo.model.Employee
import kotlinx.coroutines.flow.Flow


/**
 * Created by Akshay Teli on 13,May,2021
 */
class EmployeeRepository (private val employeeDao: EmployeeDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allEmployees: Flow<List<Employee>> = employeeDao.getAllEmployees()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.

    @WorkerThread
    suspend fun insert(emp: Employee) {
        employeeDao.insert(emp)
    }


    @WorkerThread
    suspend fun update(emp: Employee) {
        employeeDao.update(emp)
    }


    @WorkerThread
    suspend fun delete(emp: String) {
        employeeDao.deleteEmployee(emp)
    }


    @WorkerThread
    suspend fun deleteAll() {
        employeeDao.deleteAllEmployee()
    }

}