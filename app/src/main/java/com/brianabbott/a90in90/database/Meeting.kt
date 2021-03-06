package com.brianabbott.a90in90.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName="meetings")
data class Meeting (
   @PrimaryKey(autoGenerate = true)
   var id: Int,

   var  date: String,

   var name: String
)