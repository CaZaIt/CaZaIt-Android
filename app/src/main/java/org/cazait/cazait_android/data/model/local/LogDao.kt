package org.cazait.cazait_android.data.model.local

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//@Dao
//interface LogDao {
//    @Query("SELECT * FROM logs ODER BY id DESC")
//    fun getAll(): List<Log>
//
//    @Insert
//    fun insertAll(vararg logs: Log)
//
//    @Query("DELETE FROM logs")
//    fun nukeTable()
//
//    @Query("SELECT * FROM logs ORDER BY id DESC")
//    fun selectAllLogsCursor(): Cursor
//
//    @Query("SELECT * FROM logs WHERE id = :id")
//    fun selectLogById(id: Long): Cursor?
//}