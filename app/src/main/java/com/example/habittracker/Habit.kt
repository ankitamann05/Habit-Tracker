package com.example.habittracker
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habitTable")
class Habit (@ColumnInfo(name = "Name")val hName :String, @ColumnInfo(name = "Description")val hDescription :String, @ColumnInfo(name = "Timestamp") var timeStamp :String, @ColumnInfo(name = "Streak") var str :Int
 ) {
        @PrimaryKey(autoGenerate = true) var id = 0
    }
