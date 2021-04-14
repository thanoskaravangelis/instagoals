package com.instagoals.hci2021.instagoals

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.addnewgoal.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AddNewGoal: AppCompatActivity() {

    var editTitle : EditText = findViewById<EditText>(R.id.goaltitle)
    var editGoal : EditText = findViewById<EditText>(R.id.goalvalue)
    var spinner : Spinner = findViewById<Spinner>(R.id.spinnerforactivity)
    var dateofgoal : DatePicker = findViewById<DatePicker>(R.id.dategoal)
    var time : TimePicker = findViewById<TimePicker>(R.id.timegoal)
    var submit : Button = findViewById<Button>(R.id.button8)
    var importance : SeekBar = findViewById<SeekBar>(R.id.importance)

    var databaseGoals : DatabaseReference = FirebaseDatabase.getInstance().getReference("Goals")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addnewgoal)


        submit.setOnClickListener{
            AddGoal()
        }

    }

    private fun AddGoal() {
        var category : String = spinnerforactivity.selectedItem.toString()
        var importance : Int = importance.progress
        var status : String = "Active"
        var progress : Int = 0
        var year : Int = dateofgoal.year
        var month : Int = dateofgoal.month
        var day : Int = dateofgoal.dayOfMonth
        val sb = StringBuilder()
        var until_date : String =  sb.append(day).append("-").append(month).append("-").append(year).toString()
        var valueofgoal : Int =  editGoal.text.toString().toInt()
        var title : String = editTitle.text.toString()
        var points : Int = valueofgoal*100;

        var id :String =  databaseGoals.push().key
        val goal = Goal(category,"07-03-2021",importance,points,progress,status,title,until_date,valueofgoal)
        databaseGoals.child(id).setValue(goal)

        Toast.makeText(this,"Goal Added",Toast.LENGTH_LONG).show()
    }
}