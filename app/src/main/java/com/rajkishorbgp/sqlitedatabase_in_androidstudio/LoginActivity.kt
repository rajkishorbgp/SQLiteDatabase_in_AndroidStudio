package com.rajkishorbgp.sqlitedatabase_in_androidstudio

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity()
{

    private lateinit var log_email:EditText
    private lateinit var log_password:EditText
    private lateinit var databaseHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        log_email = findViewById(R.id.log_email)
        log_password = findViewById(R.id.log_password)
        databaseHelper = DatabaseHelper(applicationContext)
    }

    fun loginUser(view: View)
    {
        val email = log_email.text
        val password = log_password.text
        val flag =databaseHelper.login(email.toString(),password.toString())

        if (flag){

            val intent = Intent(this,ProfileActivity::class.java)
            intent.putExtra("userEmail",email.toString())
            startActivity(intent)
            finish()

        }else{
            Toast.makeText(this,"Email and Password does not match",Toast.LENGTH_SHORT).show()
        }
    }
}