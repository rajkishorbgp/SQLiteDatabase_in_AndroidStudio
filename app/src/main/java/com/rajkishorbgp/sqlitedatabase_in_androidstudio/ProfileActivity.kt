package com.rajkishorbgp.sqlitedatabase_in_androidstudio

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class ProfileActivity : AppCompatActivity() {
    private lateinit var userName:TextView
    private lateinit var userEmail:TextView
    private lateinit var userGender:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        userName = findViewById(R.id.userName)
        userEmail = findViewById(R.id.userEmail)
        userGender = findViewById(R.id.userGender)
        val email = intent.getStringExtra("userEmail")
        val userClass = DatabaseHelper(this).getUserDetails(email.toString())
        userName.text = userClass.name
        userEmail.text = userClass.email
        userGender.text = userClass.gender

        findViewById<Button>(R.id.userLogOut).setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        findViewById<ImageView>(R.id.profileEdit).setOnClickListener {
            val intent = Intent(this,EditProfileActivity::class.java)
            intent.putExtra("userDetails",userClass)
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.profileDelete).setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Profile")
            builder.setMessage("Are you sure you want to delete your profile ?")
            builder.setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->

            })
            builder.setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->
                val databaseHelper = DatabaseHelper(this)
                val flag = databaseHelper.deleteUserDetails(userClass.email)
                if (flag){
                    Toast.makeText(this,"Profile Delete successfully",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                }
            })

            builder.show()
        }
    }

    fun seeAllDetails(view: View) {
        startActivity(Intent(this,ListDetailsActivity::class.java))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}