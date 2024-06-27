package com.example.mathongoandroiassignmenrt.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FavoriteRecipeDataModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FavouritesRoomDataBase : RoomDatabase() {
    abstract fun favoriteRecipeDao(): FavoriteRecipeDao

    companion object {
        @Volatile
        private var INSTANCE: FavouritesRoomDataBase? = null

        fun getDatabase(context: Context): FavouritesRoomDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouritesRoomDataBase::class.java,
                    "recipes_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
