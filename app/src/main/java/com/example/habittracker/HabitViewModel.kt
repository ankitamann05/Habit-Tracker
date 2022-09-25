package com.example.habittracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitViewModel (application: Application) : AndroidViewModel(application) {

    val allHabit : LiveData<List<Habit>>
    val repository : HabitRepository

    init{
        val dao= HabitDatabase.getDatabase(application).getHabitDao()
        repository= HabitRepository(dao)
        allHabit= repository.allHabit
    }

    fun deleteHabit (habit : Habit) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(habit)
    }

    fun updateHabit (habit : Habit) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(habit)
    }

    fun addHabit (habit : Habit) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(habit)
    }


}