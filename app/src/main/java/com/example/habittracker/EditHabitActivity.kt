package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class EditHabitActivity : AppCompatActivity() {
    // on below line we are creating
    // variables for our UI components.
    lateinit var habitTitleEdt: EditText
    lateinit var habitEdt: EditText
    lateinit var saveBtn: Button

    // on below line we are creating variable for
    // viewmodal and and integer for our note id.
    lateinit var viewModel: HabitViewModel
    var habitID = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_habit)

        // on below line we are initialing our view modal.
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(HabitViewModel::class.java)

        // on below line we are initializing all our variables.
        habitTitleEdt = findViewById(R.id.idEdtHabitName)
        habitEdt = findViewById(R.id.idEdtHabitDesc)
        saveBtn = findViewById(R.id.idBtn)

        // on below line we are getting data passed via an intent.
        val habitType = intent.getStringExtra("habit Type")
       // var hs = intent.getIntExtra("habitStreak", 0)
        if (habitType.equals("Edit")) {
            // on below line we are setting data to edit text.
            val habitTitle = intent.getStringExtra("habit Title")
            val habitDescription = intent.getStringExtra("habit Description")
            habitID = intent.getIntExtra("habit Id", -1)
            //streak = intent.getIntExtra("habitStreak", 0)
            saveBtn.setText("Update Habit")
            habitTitleEdt.setText(habitTitle)
            habitEdt.setText(habitDescription)
        } else {
            saveBtn.setText("Save Habit")
        }

        // on below line we are adding
        // click listener to our save button.
        saveBtn.setOnClickListener {
            // on below line we are getting
            // title and desc from edit text.
            val habitTitle = habitTitleEdt.text.toString()
            val habitDescription = habitEdt.text.toString()
            // on below line we are checking the type
            // and then saving or updating the data.
            if (habitType.equals("Edit")) {
                if (habitTitle.isNotEmpty() && habitDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd/MM/yyyy")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Habit(habitTitle, habitDescription, currentDateAndTime, 0)
                    updatedNote.id = habitID
                    viewModel.updateHabit(updatedNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (habitTitle.isNotEmpty() && habitDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd/MM/yyyy")
                    val currentDateAndTime: String = sdf.format(Date())
                    // if the string is not empty we are calling a
                    // add note method to add data to our room database.
                    viewModel.addHabit(Habit(habitTitle, habitDescription, currentDateAndTime, 0))
                    Toast.makeText(this, "$habitTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            // opening the new activity on below line
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}
