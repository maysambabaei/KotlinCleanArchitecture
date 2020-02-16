package com.bugeto.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bugeto.data.entities.NewsPublisherData

@Database(entities = arrayOf(NewsPublisherData::class), version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao
}