package com.rajkishorbgp.sqlitedatabase_in_androidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class ListDetailsActivity : AppCompatActivity() {
    private lateinit var arrayList: ArrayList<UserClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_details)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        try {
            val databaseHelper = DatabaseHelper(this)
            arrayList = databaseHelper.getAllUserDetails()

            val listView = findViewById<ListView>(R.id.listview)
            val adapter = MyAdapter(this, arrayList)
            listView.adapter = adapter
        } catch (e: Exception) {
            // Handle the exception (e.g., log it or show a toast message)
            e.printStackTrace()
            Toast.makeText(this, "Error loading data", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
