package com.greate43.sk.infosysassement.service.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.greate43.sk.infosysassement.service.model.Fact
import com.greate43.sk.infosysassement.service.model.Rows

@Database(entities = [Fact::class, Rows::class], version = 1, exportSchema = false)
abstract class FactsDatabase : RoomDatabase() {
    abstract fun factsDao(): FactsDao

    companion object {
        private const val DATABASE_NAME = "facts_db"

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FactsDatabase? = null

        fun getDatabase(context: Context): FactsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FactsDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
