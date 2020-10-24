package com.brianabbott.a90in90.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MeetingsDAO {

    @Insert
    fun insert(meeting: Meeting)

    @Update
    fun update(meeting: Meeting)

    @Query("SELECT * FROM meetings ORDER BY date(date)")
    fun getAllMeetings() : LiveData<List<Meeting>>

    @Query("SELECT COUNT(*) FROM meetings")
    fun countMeetings() : LiveData<Int>

    @Query("DELETE FROM meetings")
    fun clear()
}