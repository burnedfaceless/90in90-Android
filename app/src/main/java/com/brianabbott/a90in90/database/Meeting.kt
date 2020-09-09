package com.brianabbott.a90in90.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="meetings")
data class Meeting (
   @PrimaryKey var id: Int,
   var  date: String,
   var name: String
)