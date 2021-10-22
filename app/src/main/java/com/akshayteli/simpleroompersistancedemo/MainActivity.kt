package com.akshayteli.simpleroompersistancedemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akshayteli.simpleroompersistancedemo.model.Employee
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.sql.DataSource

import android.view.Menu

import android.view.MenuInflater
import android.view.MenuItem


class MainActivity : AppCompatActivity(), EmployeeListAdapter.CellClickListener {
    private val TAG = "MainActivity"
    lateinit var emp: Employee
    private val newWordActivityRequestCode = 1
    private val employeeViewModel: EmployeeViewModel by viewModels {
        WordViewModelFactory((application as EmployeesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = EmployeeListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        employeeViewModel.allEmployee.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNewEmployee::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getSerializableExtra(AddNewEmployee.EXTRA_REPLY)?.let {

                val employee =
                    intentData?.getSerializableExtra(AddNewEmployee.EXTRA_REPLY) as? Employee

                val emp = intentData?.getBooleanExtra(AddNewEmployee.EDIT_EMP, false)

                // we can check here for the update or new request and according to that we can show the toast and execute the commands i.e. Add or Update
                if (emp) {
                    // Conditions for the update part
                    employee?.let { employeeViewModel.update(it) }
                    Toast.makeText(this, "Employee Updated Successfully!", Toast.LENGTH_LONG).show()
                }
                else{
                    // Add new Employee
                    employee?.let { employeeViewModel.insert(it) }
                    Toast.makeText(this, "Employee Added Successfully!", Toast.LENGTH_LONG).show()
                }

            }

            val empNameToDelete = intentData?.getStringExtra(AddNewEmployee.DELETE_EMP)

            empNameToDelete?.let {
                employeeViewModel.delete(empNameToDelete)
                Toast.makeText(this, "Employee Deleted Successfully!", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCellClickListener(data: Employee) {
        Toast.makeText(this, data.name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MainActivity, AddNewEmployee::class.java)
        intent.putExtra("Employee", data)
        startActivityForResult(intent, newWordActivityRequestCode)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
               employeeViewModel.deleteAll()
                Toast.makeText(applicationContext,"All Employees Deleted Successfully",Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}