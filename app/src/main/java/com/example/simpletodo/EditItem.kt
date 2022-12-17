package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EditItem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)
        setTitle("Edit Item")
    }
}