package com.rajkishorbgp.sqlitedatabase_in_androidstudio

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.zip.Inflater

class MyAdapter() : BaseAdapter(){
    private lateinit var context: Context
    private lateinit var arrayList: ArrayList<UserClass>

    constructor(context: Context, arrayList: ArrayList<UserClass>):this(){
        this.context=context
        this.arrayList = arrayList
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView

        if (view==null){
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.listview,null)
        }

        val userNameId = view?.findViewById<TextView>(R.id.userName)
        val userEmailId = view?.findViewById<TextView>(R.id.userEmail)
        val userGenderId = view?.findViewById<TextView>(R.id.userGender)

        userNameId?.text = arrayList[position].name
        userEmailId?.text = arrayList[position].email
        userGenderId?.text = arrayList[position].gender

        return view
    }
}