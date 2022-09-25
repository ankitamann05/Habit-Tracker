package com.example.habittracker

import androidx.lifecycle.LiveData

class HabitRepository(private val habitDao: HabitDao) {

    val allHabit: LiveData<List<Habit>> = habitDao.getAllHabits()

    suspend fun insert(habit: Habit){
        habitDao.insert(habit)
    }

    suspend fun delete(habit: Habit){
        habitDao.delete(habit)
    }

    suspend fun update(habit: Habit){
        habitDao.update(habit)
    }
}