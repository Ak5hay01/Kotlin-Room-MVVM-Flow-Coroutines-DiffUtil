package com.akshayteli.simpleroompersistancedemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.akshayteli.simpleroompersistancedemo.model.Employee
import kotlinx.android.synthetic.main.activity_add_new_employee2.*

class AddNewEmployee : AppCompatActivity() {

    private lateinit var emp:Employee
    private var empName:String? = null
    var isEdit:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_employee2)

        val editName = findViewById<EditText>(R.id.edit_name)
        val editAddress = findViewById<EditText>(R.id.edit_address)
       val editNumber = findViewById<EditText>(R.id.edit_number)


        intent?.getSerializableExtra("Employee")?.let {
            emp = (intent?.getSerializableExtra("Employee") as? Employee)!!
           editName.setText(emp.name)
            editAddress.setText(emp.address)
            editNumber.setText(emp.number)
            editName.isEnabled = false
            button_save.text = "Update"
            isEdit = true

        }

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editName.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                var name = editName.text.toString()
                var address = editAddress.text.toString()
                var number = editNumber.text.toString()

                val employee = Employee(name, address,number)

                replyIntent.putExtra(EXTRA_REPLY, employee)
                replyIntent.putExtra(EDIT_EMP,isEdit)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
    companion object {
        const val EXTRA_REPLY = "com.akshayteli.simpleroompersistancedemo.wordlistsql.REPLY"
        const val EDIT_EMP = "com.akshayteli.simpleroompersistancedemo.wordlistsql.edit"
        const val DELETE_EMP = "com.akshayteli.simpleroompersistancedemo.wordlistsql.delete"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (!isEdit)
            return false;
        menuInflater.inflate(R.menu.menu_crud, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delte_single -> {
                val replyIntent = Intent()
                replyIntent.putExtra(DELETE_EMP,edit_name.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}


