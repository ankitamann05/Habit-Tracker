package com.example.habittracker

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitRVAdapter(
    val context: Context,
    val habitClickDeleteInterface: HabitClickDeleteInterface,
    val habitClickInterface: HabitClickInterface,
    val habitClickDoneInterface: HabitClickDoneInterface
) :
    RecyclerView.Adapter<HabitRVAdapter.ViewHolder>() {

    // on below line we are creating a
    // variable for our all notes list.
    private val allHabit = ArrayList<Habit>()

    // on below line we are creating a view holder class.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are creating an initializing all our
        // variables which we have added in layout file.
        val habitTV: TextView = itemView.findViewById<TextView>(R.id.idTVNote)
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
        val doneTV = itemView.findViewById<ImageView>(R.id.idTVDone)
        val streakTV = itemView.findViewById<TextView>(R.id.idTVStreak)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating our layout file for each item of recycler view.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.habit_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data to item of recycler view.

            holder.habitTV.setText(allHabit.get(position).hName)
            holder.dateTV.setText(allHabit.get(position).timeStamp)
            holder.streakTV.setText( (allHabit.get(position).str).toString())

        // on below line we are adding click listener to our delete image view icon.
        holder.deleteIV.setOnClickListener {
            // on below line we are calling a note click
            // interface and we are passing a position to it.
            habitClickDeleteInterface.onDeleteIconClick(allHabit.get(position))
        }

        holder.doneTV.setOnClickListener {

            habitClickDoneInterface.onDoneIconClick(allHabit.get(position) , holder)
        }
        // on below line we are adding click listener
        // to our recycler view item.
        holder.itemView.setOnClickListener {
            // on below line we are calling a note click interface
            // and we are passing a position to it.
            habitClickInterface.onHabitClick(allHabit.get(position))
        }
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our list size.
        return allHabit.size
    }

    // below method is use to update our list of notes.
    fun updateList(newList: List<Habit>) {
        // on below line we are clearing
        // our notes array list
        allHabit.clear()
        // on below line we are adding a
        // new list to our all notes list.
        allHabit.addAll(newList)
        // on below line we are calling notify data
        // change method to notify our adapter.
        notifyDataSetChanged()
    }
}


interface HabitClickDeleteInterface {
    // creating a method for click
    // action on delete image view.
    fun onDeleteIconClick(habit: Habit)
}


interface HabitClickDoneInterface {

    fun onDoneIconClick(habit: Habit , holder: HabitRVAdapter.ViewHolder)
}

interface HabitClickInterface {
    // creating a method for click action
    // on recycler view item for updating it.
    fun onHabitClick(habit: Habit)
}





