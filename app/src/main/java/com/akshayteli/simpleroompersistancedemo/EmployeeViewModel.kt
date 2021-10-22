package com.akshayteli.simpleroompersistancedemo

import androidx.lifecycle.*
import com.akshayteli.simpleroompersistancedemo.model.Employee
import com.akshayteli.simpleroompersistancedemo.room.EmployeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Created by Akshay Teli on 13,May,2021
 */
class EmployeeViewModel (private val repository: EmployeeRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allEmployee: LiveData<List<Employee>> = repository.allEmployees.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: Employee) = viewModelScope.launch {
        repository.insert(word)
    }

    fun update(word: Employee) = viewModelScope.launch {
        repository.update(word)
    }

    fun delete(word: String) = viewModelScope.launch {
        repository.delete(word)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

}

class WordViewModelFactory(private val repository: EmployeeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmployeeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}