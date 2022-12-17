package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    var listOfTasks= mutableListOf<String>()
    lateinit var adapter: TasksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val OnLongClickListener=object : TasksAdapter.OnLongClickListener{
            override fun onLongItemClicked(position: Int) {
                listOfTasks.removeAt(position)
                adapter.notifyDataSetChanged()
                writeItem()
            }

        }
        //listOfTasks.add("Do homework")
        //listOfTasks.add("Do the dishes")

        loadItems()
        //reference to the recyclerview in the layout
        val rvItem = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        // Creates the adapter for the recycler view passing in the sample user data
        adapter = TasksAdapter(listOfTasks, OnLongClickListener)
        // Attach the adapter to the recyclerview to populate items
        rvItem.adapter=adapter
        // Set layout manager to position the items
        rvItem.layoutManager=LinearLayoutManager(this)

        //If the button was clicked, get the reference to the button
        findViewById<Button>(R.id.button).setOnClickListener{
            //get text inputted into "@+id/editTextTextPersonName"
            var taskInputted=findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
            //add string to list
            listOfTasks.add(taskInputted)

            //notify adapter of the change in listOfTasks
            adapter.notifyItemInserted(listOfTasks.size-1)
            //reset the text field to empty
            findViewById<EditText>(R.id.editTextTextPersonName).setText("")
            writeItem()
        }
    }
    //Get the file
    fun getItemsFile(): File {
        //every line is a single task
        return File(filesDir, "tasks.txt")
    }

    //read every line inside the file
    fun loadItems(){
        try{
            listOfTasks=FileUtils.readLines(getItemsFile(), Charset.defaultCharset())
        }
        catch(ioException: IOException){
            ioException.printStackTrace()
        }
    }

    //save item by writing into the data file
    fun writeItem(){
        try{
            FileUtils.writeLines(getItemsFile(), listOfTasks)
        }
        catch(ioException: IOException){
            ioException.printStackTrace()
        }



    }

}