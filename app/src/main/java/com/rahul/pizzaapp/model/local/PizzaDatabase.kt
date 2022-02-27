package com.rahul.pizzaapp.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PizzaEntity::class], version = 1)
abstract class PizzaDatabase:RoomDatabase() {
    abstract fun getMyDao(): PizzaDao

    companion object {

        private var INSTANCE: PizzaDatabase? = null


        /**
         * This function return the database reference
         */
        fun getDatabase(context: Context): PizzaDatabase {
            if (INSTANCE == null) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    PizzaDatabase::class.java,
                    "my_database"
                )

                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()

                return INSTANCE!!
            } else {
                return INSTANCE!!
            }
        }

    }

}