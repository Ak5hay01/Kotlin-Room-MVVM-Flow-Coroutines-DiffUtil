package com.akshayteli.simpleroompersistancedemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akshayteli.simpleroompersistancedemo.model.Employee

/**
 * Created by Akshay Teli on 13,May,2021
 */
class EmployeeListAdapter(private val cellClickListener: CellClickListener) : ListAdapter<Employee, EmployeeListAdapter.EmployeeViewHolder>(EMPLOYEE_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(current)
        }

    }

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): EmployeeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)

                return EmployeeViewHolder(view)
            }
        }

    }

    interface CellClickListener {
        fun onCellClickListener(data: Employee)
    }

    companion object {
        private val EMPLOYEE_COMPARATOR = object : DiffUtil.ItemCallback<Employee>() {
            override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}