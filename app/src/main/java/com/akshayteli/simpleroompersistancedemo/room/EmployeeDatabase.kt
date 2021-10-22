package com.akshayteli.simpleroompersistancedemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.akshayteli.simpleroompersistancedemo.model.Employee

/**
 * Created by Akshay Teli on 13,May,2021
 */

@Database(
    entities = [Employee::class],
    version = 1,
)
abstract class EmployeeDatabase : RoomDatabase(){

    abstract fun employeeDao(): EmployeeDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: EmployeeDatabase? = null

        fun getDatabase(context: Context): EmployeeDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            val DATABASE_NAME = "employee_db"

            return INSTANCE ?: synchronized(this) {

                val instance = databaseBuilder(
                    context.applicationContext,
                    EmployeeDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}