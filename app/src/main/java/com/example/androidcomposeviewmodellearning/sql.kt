package com.example.androidcomposeviewmodellearning

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Upsert

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "email") val lastName: String?,
    @ColumnInfo(name = "name") val firstName: String?,
    @ColumnInfo(name = "password") val password: String?
)

@Dao
interface UsersDao {
    // update or create data
    @Upsert

}