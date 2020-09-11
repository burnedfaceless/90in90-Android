package com.brianabbott.a90in90.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Copyright (c) 2020 Brian Abbott
 * Created by Brian Abbott on 9/10/20
 */

@Database(entities = [Meeting::class], version = 1, exportSchema = false)
abstract class MeetingsDatabase : RoomDatabase() {
    abstract val meetingsDao: MeetingsDAO

    companion object{

        @Volatile
        private var INSTANCE: MeetingsDatabase? = null

        fun getInstance(context: Context) : MeetingsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MeetingsDatabase::class.java,
                        "meetings_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }

    }
}