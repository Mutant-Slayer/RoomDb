package com.example.roomdbdemo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var database: ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = ContactDatabase.getDatabase(this)

        lifecycleScope.launch {
            database.contactDao().insertContact(Contact(0, "Rahul", "1234567890", Date(), 0))
        }

        val getDataBtn = findViewById<Button>(R.id.btnData)
        getDataBtn.setOnClickListener {
            database.contactDao().getAllContacts().observe(this) {
                println(it)
            }
        }
    }
}