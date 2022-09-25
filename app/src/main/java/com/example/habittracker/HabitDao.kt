package com.example.habittracker

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(habit : Habit)

    @Delete
    suspend fun delete(habit : Habit)

    @Query("Select * from habitTable order by id ASC")
    fun getAllHabits(): LiveData<List<Habit>>

    @Update
    suspend fun update(habit: Habit)
}