package com.cibertec.exament2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        private var INSTANCE: com.cibertec.exament2.AppDatabase? = null

        fun getInstance(context: Context): com.cibertec.exament2.AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    com.cibertec.exament2.AppDatabase::class.java,
                    "products.db"
                ).build()
            }
            return INSTANCE!!
        }
    }

}