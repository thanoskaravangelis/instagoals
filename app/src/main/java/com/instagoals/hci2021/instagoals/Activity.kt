package com.instagoals.hci2021.instagoals

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.view.GestureDetectorCompat
import android.transition.TransitionManager
import android.util.Log
import android.view.GestureDetector
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.*
import kotlinx.android.synthetic.main.activity.*
import kotlinx.android.synthetic.main.goals.*

class Activity : AppCompatActivity() {

    private lateinit var detector : GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        detector = GestureDetectorCompat(this, GestureListenerActivity())

        val con_layout: ConstraintLayout  = findViewById<ConstraintLayout>(R.id.activities)

        val go_to_new: ImageButton = findViewById<ImageButton>(R.id.button6)
        go_to_new.setOnClickListener{
            val layoutInflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater.inflate(R.layout.addnewactivity,null)

            val popupWindow = PopupWindow(view,LinearLayout.LayoutParams.WRAP_CONTENT,
                    1400)

            val buttonClose = view.findViewById<ImageButton>(R.id.button7)
            buttonClose.setOnClickListener{
                popupWindow.dismiss()
            }

            TransitionManager.beginDelayedTransition(con_layout)
            popupWindow.showAtLocation(con_layout, Gravity.NO_GRAVITY,60,100)

        }


        val menupop: ImageButton = findViewById<ImageButton>(R.id.burgermenu)
        menupop.setOnClickListener{
            val layoutInflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater.inflate(R.layout.burgermenu,null)

            val popupWindow = PopupWindow(view,LinearLayout.LayoutParams.WRAP_CONTENT,
                    1000)

            val buttonClose = view.findViewById<ImageView>(R.id.backtoroot)
            buttonClose.setOnClickListener{
                popupWindow.dismiss()
            }

            TransitionManager.beginDelayedTransition(con_layout)
            popupWindow.showAtLocation(con_layout, Gravity.NO_GRAVITY,370,16)
        }

        val go_to_activity: ImageButton = findViewById<ImageButton>(R.id.button5)
        go_to_activity.setOnClickListener{
            val intent = Intent(this, Activity::class.java)
            startActivity(intent)
        }

        val go_to_goal: ImageButton = findViewById<ImageButton>(R.id.button4)
        go_to_goal.setOnClickListener{
            val intent = Intent(this, Goals::class.java)
            startActivity(intent)
            this.overridePendingTransition(R.layout.in_from_left,R.layout.out_from_right)
        }

        val go_to_home: ImageButton = findViewById<ImageButton>(R.id.button3)
        go_to_home.setOnClickListener{
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
            this.overridePendingTransition(R.layout.in_from_left,R.layout.out_from_right)
        }

        val go_to_profile:ImageButton = findViewById<ImageButton>(R.id.button)
        go_to_profile.setOnClickListener{
            val intent3 = Intent(this, Profile::class.java)
            startActivity(intent3)
            this.overridePendingTransition(R.layout.in_from_right,R.layout.out_from_left)
        }

        val go_to_stats:ImageButton = findViewById<ImageButton>(R.id.button2)
        go_to_stats.setOnClickListener{
            val intent4 = Intent(this, Stats::class.java)
            startActivity(intent4)
            this.overridePendingTransition(R.layout.in_from_left,R.layout.out_from_right)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (detector.onTouchEvent(event)) {
            true
        }else {
            super.onTouchEvent(event)
        }
    }

    internal fun onSwipeRight() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.overridePendingTransition(R.layout.in_from_left, R.layout.out_from_right)
    }

    internal fun onSwipeLeft () {
        val intent2 = Intent(this, Profile::class.java)
        startActivity(intent2)
        this.overridePendingTransition(R.layout.in_from_right, R.layout.out_from_left)
    }

    private fun onSwipeBottom() {
        Toast.makeText(this, "Swipe Left or Right to Navigate", Toast.LENGTH_LONG).show()
    }

    private fun onSwipeTop() {
        Toast.makeText(this, "Swipe Left or Right to Navigate", Toast.LENGTH_LONG).show()
    }

    inner class GestureListenerActivity : GestureDetector.SimpleOnGestureListener() {

        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(touchevent1: MotionEvent?, touchevent2: MotionEvent?,
                             velX: Float, velY: Float): Boolean {

            var diffX = touchevent2?.x?.minus(touchevent1!!.x) ?: 0.0F
            var diffY = touchevent2?.y?.minus(touchevent1!!.y) ?: 0.0F

            return if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) { //right swipe
                        Log.i("swiperight","swipe right detected")
                        this@Activity.onSwipeRight()
                    } else { //left swipe
                        Log.i("swipeleft","swipe left detected")
                        this@Activity.onSwipeLeft()
                    }
                    true
                }
                else {
                    super.onFling(touchevent1, touchevent2, velX, velY)
                }
            } else {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        this@Activity.onSwipeTop()
                    } else {
                        this@Activity.onSwipeBottom()
                    }
                    true
                } else {
                    super.onFling(touchevent1, touchevent2, velX, velY)
                }
            }
        }
    }
}