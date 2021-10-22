package com.akshayteli.simpleroompersistancedemo

import android.app.Application
import com.akshayteli.simpleroompersistancedemo.room.EmployeeDatabase
import com.akshayteli.simpleroompersistancedemo.room.EmployeeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * Created by Akshay Teli on 13,May,2021
 */
class EmployeesApplication: Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { EmployeeDatabase.getDatabase(this) }
    val repository by lazy { EmployeeRepository(database.employeeDao()) }
}