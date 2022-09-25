package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), HabitClickInterface, HabitClickDeleteInterface, HabitClickDoneInterface{

    // on below line we are creating a variable
    // for our recycler view, exit text, button and viewmodel.
    lateinit var viewModal: HabitViewModel
    lateinit var habitRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var addBack: FloatingActionButton
    lateinit var wtv: TextView
    //var ltb= 0.toLong()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wtv=findViewById(R.id.wtv)
        val mp=MyPreference(this)
        val s=mp.getusername()
        wtv.setText(s)
        //val bt=SystemClock.elapsedRealtime();
        //ltb=bt;
        // on below line we are initializing
        // all our variables.
        habitRV = findViewById(R.id.habitRV)
        addFAB = findViewById(R.id.idFAB)
        addBack = findViewById(R.id.idBack)
        // on below line we are setting layout
        // manager to our recycler view.
        habitRV.layoutManager = LinearLayoutManager(this)

        // on below line we are initializing our adapter class.
        val habitRVAdapter = HabitRVAdapter(this, this, this, this)

        // on below line we are setting
        // adapter to our recycler view.
        habitRV.adapter = habitRVAdapter

        // on below line we are
        // initializing our view modal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(HabitViewModel::class.java)

        // on below line we are calling all notes method
        // from our view modal class to observer the changes on list.
        viewModal.allHabit.observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                habitRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            // adding a click listener for fab button
            // and opening a new intent to add a new note.
            val intent = Intent(this@MainActivity, EditHabitActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        addBack.setOnClickListener{
            val intent = Intent(this@MainActivity , WelcomeActivity::class.java )
            startActivity(intent)
            this.finish()
        }
    }

    override fun onHabitClick(habit: Habit) {
        // opening a new intent and passing a data to it.
        val intent = Intent(this@MainActivity, EditHabitActivity::class.java)
        intent.putExtra("habitType", "Edit")
        intent.putExtra("habitTitle", habit.hName)
        intent.putExtra("habitDescription", habit.hDescription)
        //intent.putExtra("habitStreak", habit.str)
        intent.putExtra("habitID", habit.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDoneIconClick(habit: Habit, holder: HabitRVAdapter.ViewHolder) {

        val ds= habit.timeStamp;
        val d=Integer.parseInt(ds.substring(0,2))
        val m=Integer.parseInt(ds.substring(3,5))
        val y=Integer.parseInt(ds.substring(6,10))

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentD: String = sdf.format(Date())
        val cd=Integer.parseInt(currentD.substring(0,2))
        val cm=Integer.parseInt(currentD.substring(3,5))
        val cy=Integer.parseInt(currentD.substring(6,10))
        habit.timeStamp =currentD
        if (habit.str == 0){
            habit.str=1
        }
        else{
          if((cm==m) && (cy==y) && (cd== (d+1)) ){
                   habit.str++;
          }
          else if((cm==m) && (cy==y) && (cd== d) ){
              val b=true
          }
          else if ((cm == (m+1) ||  cy == (y+1)) && (cd==1 && (d==30 || d==31 || (cm==2 && d==28)))){
              habit.str++;
          }
          else{
              habit.str=0;
          }
        }

        /*var tn = SystemClock.elapsedRealtime()
        //var te = SystemClock.elapsedRealtime() - ltb;
        //ltb=tn
        //if(Integer.parseInt(te.toString()) < 60000 ){
            habit.str++
        }
        else{
            habit.str=0
        }*/
        viewModal.updateHabit(habit)
        holder.streakTV.setText((habit.str).toString())
        Toast.makeText(this, "${habit.hName} Done", Toast.LENGTH_LONG).show()
    }


    override fun onDeleteIconClick(habit: Habit) {
        // in on note click method we are calling delete
        // method from our view modal to delete our not.
        viewModal.deleteHabit(habit)
        // displaying a toast message
        Toast.makeText(this, "${habit.hName} Deleted", Toast.LENGTH_LONG).show()
    }
}
