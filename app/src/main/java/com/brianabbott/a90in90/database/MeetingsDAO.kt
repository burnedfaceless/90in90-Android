package com.brianabbott.a90in90.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MeetingsDAO {

    @Query("SELECT * FROM meetings ORDER BY date")
    fun getAllMeetings() : LiveData<List<Meeting>>

    @Insert
    fun insertMeeting(meeting: Meeting)
}