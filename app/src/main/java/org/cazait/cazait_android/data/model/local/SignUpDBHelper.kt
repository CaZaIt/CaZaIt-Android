package org.cazait.cazait_android.data.model.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SignUpDBHelper(context: Context?) :
    SQLiteOpenHelper(context, "Login.db", null, 1) {
    override fun onCreate(MyDB: SQLiteDatabase) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)")
    }

    override fun onUpgrade(MyDB: SQLiteDatabase, i: Int, i1: Int) {
        MyDB.execSQL("drop Table if exists users")
    }

    fun insertData(username: String?, password: String?): Boolean {
        val myDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val result = myDB.insert("users", null, contentValues)
        return result != -1L
    }

    fun checkUserName(userName: String): Boolean {
        val myDB = this.writableDatabase
        var res = true
        val cursor = myDB.rawQuery("Select * from users where username = ?", arrayOf(userName))
        if (cursor.count <= 0) res = false
        return res
    }

    fun checkUserPass(userName: String, password: String): Boolean {
        val myDB = this.writableDatabase
        var res = true
        val cursor = myDB.rawQuery(
            "Select * from users where username = ? and password = ?",
            arrayOf(userName, password)
        )
        if (cursor.count <= 0) res = false
        return res
    }

    companion object {
        const val DBNAME = "Login.db"
    }
}
