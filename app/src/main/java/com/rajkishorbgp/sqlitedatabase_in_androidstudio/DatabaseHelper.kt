package com.rajkishorbgp.sqlitedatabase_in_androidstudio

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(private val context: Context) :
    SQLiteOpenHelper(context, "rajkishor", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_QUERY = "CREATE TABLE register(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT, Gender TEXT)"
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database schema upgrades here (if needed)
        db.execSQL("DROP TABLE IF EXISTS register")
        onCreate(db)
    }

    fun registerUserHelper(name: String, email: String, password: String, gender: String): Boolean {
        val sqLiteDatabase = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("email", email)
        contentValues.put("password", password)
        contentValues.put("Gender", gender)

        val l = sqLiteDatabase.insert("register", null, contentValues)
        sqLiteDatabase.close()

        return l != -1L
    }

    fun login(email: String, password: String): Boolean {
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery("SELECT * FROM register WHERE email=? AND password=?", arrayOf(email, password))

        val result = cursor.moveToFirst()
        cursor.close()

        return result
    }

    fun getUserDetails(email1: String): UserClass {
        lateinit var userClass:UserClass
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery("SELECT * FROM register WHERE email='$email1'",
            null
        )

        userClass = if (cursor.moveToFirst()) {
            // Corrected the column index retrieval
            val name = cursor.getString(1)
            val email = cursor.getString(2)
            val gender = cursor.getString(4)
            cursor.close()
            UserClass(name, email, gender)
        } else {
            UserClass("no", "null", "null")
        }
        return userClass
    }

    fun getAllUserDetails(): ArrayList<UserClass> {
        val arrayList = ArrayList<UserClass>()

        try {
            val sqLiteDatabase = this.readableDatabase
            val cursor = sqLiteDatabase.rawQuery("SELECT * FROM register", null)

            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(1)
                    val email = cursor.getString(2)
                    val gender = cursor.getString(4)
                    arrayList.add(UserClass(name, email, gender))
                } while (cursor.moveToNext())
            }
            cursor.close()
        } catch (e: Exception) {
            Toast.makeText(context,"Error $e",Toast.LENGTH_SHORT).show()
        }
        return arrayList
    }

    fun updateUserData(name:String, email: String, gender: String):Boolean{
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name",name)
        contentValues.put("Gender",gender)
        val l = sqLiteDatabase.update("register", contentValues, "email=?", arrayOf(email))
        return l>0
    }

    fun deleteUserDetails(email: String):Boolean{
        val databaseHelper = this.writableDatabase
        val l =databaseHelper.delete("register","email=?", arrayOf(email))
        return l>0
    }
}