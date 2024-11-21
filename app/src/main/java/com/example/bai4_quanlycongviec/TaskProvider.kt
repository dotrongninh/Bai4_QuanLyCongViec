package com.example.bai4_quanlycongviec

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.bai4_quanlycongviec.SQLite.TaskHelper

class TaskProvider : ContentProvider() {
    companion object {

        val CONTENT_URI: Uri = Uri.parse("content://com.example.bai4_quanlycongviec/tasks")
    }
  private lateinit var dbHelper  : TaskHelper
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
       val db = dbHelper.writableDatabase
        val id = db.insert(TaskHelper.TABLE_NAME,null,values)
        return ContentUris.withAppendedId(CONTENT_URI, id)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(): Boolean {
     dbHelper = TaskHelper(context as Context)
        return true

    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        Log.d("TaskContentProvider", "Query received with URI: $uri")
        val db = dbHelper.readableDatabase
        return db.query(
            TaskHelper.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
         }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}