package com.rajkishorbgp.sqlitedatabase_in_androidstudio

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class EditProfileActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val editName = findViewById<EditText>(R.id.editName)
        val userEmailNotEditable = findViewById<TextView>(R.id.userEmailNotEditable)
        val editGender = findViewById<TextView>(R.id.editGender)
        val updateButton = findViewById<TextView>(R.id.updateButton)

        val userClass = intent.getSerializableExtra("userDetails") as UserClass
        val name: String? = userClass.name
        val email: String = userClass.email
        val gender: String = userClass.gender
        editName.setText(name)
        userEmailNotEditable.text = email
        editGender.text = gender

        updateButton.setOnClickListener {
            val databaseHelper = DatabaseHelper(this)
            val flag =databaseHelper.updateUserData(editName.text.toString(),userEmailNotEditable.text.toString(),editGender.text.toString())
            if (flag){
                startActivity(Intent(this,ProfileActivity::class.java))
                finish()
                Toast.makeText(this,"Updating your details successfully",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}