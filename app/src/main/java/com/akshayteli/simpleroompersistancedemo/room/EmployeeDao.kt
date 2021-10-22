package com.akshayteli.simpleroompersistancedemo.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.akshayteli.simpleroompersistancedemo.model.Employee
import kotlinx.coroutines.flow.Flow

/**
 * Created by Akshay Teli on 13,May,2021
 */

@Dao
public interface EmployeeDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.

    //Get all employees
    @Query("SELECT * FROM employee ")
    fun getAllEmployees(): Flow<List<Employee>>

    //Add new Employee
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(emp: Employee)

//    Delete Selected Employee
    @Query("DELETE FROM employee WHERE name = :name")
    suspend fun deleteEmployee(name:String)

//    Delete All Employees
    @Query("DELETE FROM employee")
    suspend fun deleteAllEmployee()

//    Update Employee Data Except for the Name as name is the Primary key
    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(emp: Employee)
}