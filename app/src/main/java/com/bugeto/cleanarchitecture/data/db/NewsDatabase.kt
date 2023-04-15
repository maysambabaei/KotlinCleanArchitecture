package com.bugeto.cleanarchitecture.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bugeto.cleanarchitecture.data.entities.NewsPublisherData

@Database(entities = [NewsPublisherData::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao
}