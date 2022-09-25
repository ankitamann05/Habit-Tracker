package com.example.habittracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Habit::class), version = 1, exportSchema = false)
abstract class HabitDatabase : RoomDatabase() {


        abstract fun getHabitDao(): HabitDao

        companion object {
            @Volatile
            private var INSTANCE: HabitDatabase? = null
            fun getDatabase(context: Context): HabitDatabase{
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        HabitDatabase::class.java,
                        "habit_database"
                    ).build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
            }
        }
}