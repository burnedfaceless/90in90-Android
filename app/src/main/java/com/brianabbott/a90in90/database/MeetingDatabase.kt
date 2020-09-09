package com.brianabbott.a90in90.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities= [Meeting::class], version = 1)
abstract class MeetingDatabase : RoomDatabase() {
    abstract fun meetingDAO() : MeetingsDAO
}