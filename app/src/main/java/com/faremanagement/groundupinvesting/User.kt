package com.faremanagement.groundupinvesting

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(@PrimaryKey @ColumnInfo(name = "email") val email: String,
                @ColumnInfo(name = "firstName") val firstName: String,
                @ColumnInfo(name = "lastName") val lastName: String) {

}