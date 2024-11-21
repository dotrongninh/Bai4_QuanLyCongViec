package com.example.bai4_quanlycongviec

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai4_quanlycongviec.SQLite.TaskHelper

class AddTaskActivity : AppCompatActivity() {

    private lateinit var taskNameEditText: EditText
    private lateinit var taskDatePicker: DatePicker
    private lateinit var saveTaskButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        taskNameEditText = findViewById(R.id.taskNameEditText)
        taskDatePicker = findViewById(R.id.datePicker)
        saveTaskButton = findViewById(R.id.saveTaskButton)

        saveTaskButton.setOnClickListener {
            val name = taskNameEditText.text.toString()
            val date = "${taskDatePicker.year}-${taskDatePicker.month + 1}-${taskDatePicker.dayOfMonth}"

            val values = ContentValues().apply {
                put(TaskHelper.COLUMN_NAME, name)
                put(TaskHelper.COLUMN_DATE, date)
            }
            contentResolver.insert(TaskProvider.CONTENT_URI, values)
            finish()

    }
}}