package com.rajkishorbgp.sqlitedatabase_in_androidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    private lateinit var reg_userName:EditText
    private lateinit var reg_userEmail:EditText
    private lateinit var reg_userPassword:EditText
    private lateinit var reg_userGender:EditText
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        reg_userName = findViewById(R.id.reg_userName)
        reg_userEmail = findViewById(R.id.reg_userEmail)
        reg_userPassword = findViewById(R.id.reg_userPassword)
        reg_userGender = findViewById(R.id.reg_userGender)

        databaseHelper = DatabaseHelper(applicationContext)
    }

    fun registerUser(view: View) {
        val name=reg_userName.text.toString()
        val email=reg_userEmail.text.toString()
        val password=reg_userPassword.text.toString()
        val gender=reg_userGender.text.toString()

        val flag =databaseHelper.registerUserHelper(name,email, password, gender)
        if (flag){
            reg_userName.text.clear()
            reg_userEmail.text.clear()
            reg_userPassword.text.clear()
            reg_userGender.text.clear()
            Toast.makeText(this,"User register successfully...!!",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Error...!!",Toast.LENGTH_SHORT).show()
        }
    }
}