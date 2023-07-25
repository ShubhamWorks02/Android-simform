package com.example.activityscreens.data.local

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CachedUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            } // used to get idea of locking

        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            )
                .build()
    }
}

@Dao
interface UserDao {
    @Query("SELECT * FROM cached_users")
    fun getAllUsers(): List<CachedUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<CachedUser>)

    @Query("DELETE FROM cached_users")
    fun deleteAllUsers()
}
