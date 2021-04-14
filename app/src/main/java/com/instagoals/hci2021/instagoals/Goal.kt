package com.instagoals.hci2021.instagoals

import android.content.ActivityNotFoundException


class Goal(category: String, from_date:String,
           importance: Int, points: Int, progress: Int,
           status: String , title: String , until_date: String , value: Int) {

    var Category: String = category
    var From_date: String = from_date
    var Importance: Int = importance
    var Points: Int = points
    var Progress: Int = progress
    var Status: String = status
    var Title: String = title
    var Until_date: String = until_date
    var Value: Int = value

}