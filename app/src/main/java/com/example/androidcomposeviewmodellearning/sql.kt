package com.example.androidcomposeviewmodellearning

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.launch

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "email") val lastName: String?,
    @ColumnInfo(name = "name") val firstName: String?,
    @ColumnInfo(name = "password") val password: String?
)

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: Users)

    @Query("SELECT * FROM users WHERE uid = :userId")
    suspend fun getUserById(userId: Int): Users?

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<Users>

    @Update
    suspend fun updateUser(user: Users)

    @Delete
    suspend fun deleteUser(user: Users)
}

@Database(entities = [Users::class], version = 1, exportSchema = false)


class UsersRepository(private val usersDao: UsersDao) {
    suspend fun insert(user: Users) {
        usersDao.insert(user)
    }

    suspend fun getUserById(userId: Int): Users? {
        return usersDao.getUserById(userId)
    }

    suspend fun getAllUsers(): List<Users> {
        return usersDao.getAllUsers()
    }

    suspend fun updateUser(user: Users) {
        usersDao.updateUser(user)
    }

    suspend fun deleteUser(user: Users) {
        usersDao.deleteUser(user)
    }
}

class UsersViewModel(private val repository: UsersRepository) : ViewModel() {
    fun insert(user: Users) {
        viewModelScope.launch {
            repository.insert(user)
        }
    }
}