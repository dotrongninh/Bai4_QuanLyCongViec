package com.example.bai4_quanlycongviec

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai4_quanlycongviec.SQLite.Task
import com.example.bai4_quanlycongviec.SQLite.TaskHelper

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var addButton: Button
    private val tasks = ArrayList<Task>()
    private lateinit var adapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        listView = findViewById(R.id.taskListView)
        addButton=findViewById(R.id.addButton)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter
        addButton.setOnClickListener {
            val intent =Intent(this,AddTaskActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        tasks.clear()
        adapter.clear()

        val cursor = contentResolver.query(TaskProvider.CONTENT_URI, null, null, null, null)
        if (cursor!=null) Log.d("aaa","cursor not null")

        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow(TaskHelper.COLUMN_ID))
                val name = it.getString(it.getColumnIndexOrThrow(TaskHelper.COLUMN_NAME))
                val date = it.getString(it.getColumnIndexOrThrow(TaskHelper.COLUMN_DATE))
                tasks.add(Task(id, name, date))
                adapter.add("Tên: $name - Năm sinh: $date")
            }
        }
        adapter.notifyDataSetChanged()
    }
}