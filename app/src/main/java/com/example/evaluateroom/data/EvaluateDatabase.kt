package com.example.evaluateappfinal.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.evaluateroom.model.EvaluateEntity

@Database(entities = [EvaluateEntity::class], version = 1, exportSchema = false)
abstract class EvaluateDatabase : RoomDatabase() {

    abstract fun evaluateDao(): EvaluateDao

    companion object {
        @Volatile
        private var INSTANCE: EvaluateDatabase? = null

        fun getDatabase(context: Context): EvaluateDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EvaluateDatabase::class.java,
                    "evaluate_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}