package com.example.simpletodo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Creates the adapter for the recyclerview to recieve all of the specific tasks from the user
 *
 */
class TasksAdapter(val tasksList: List<String>, val clicker: OnLongClickListener):
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    interface OnLongClickListener{
        fun onLongItemClicked(position: Int)
    }
    //generates the view for every specific item in the recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }
    //Populates the data into each item through ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*
        // Get the data model based on position
        val contact: Contact = mContacts.get(position)
        // Set item views based on your views and data model
        val textView = viewHolder.nameTextView
        textView.setText(contact.name)
        val button = viewHolder.messageButton
        button.text = if (contact.isOnline) "Message" else "Offline"
        button.isEnabled = contact.isOnline
         */
        // Get the data model based on position
        val item: String=tasksList.get(position)
        // Set text view to the selected string item
        holder.textView.text=item

    }

    override fun getItemCount(): Int {
        return tasksList.size
    }


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Stores references to the layout view
        val textView: TextView
        init{
            textView=itemView.findViewById(android.R.id.text1)
            itemView.setOnLongClickListener{
                clicker.onLongItemClicked(adapterPosition)
                true
            }
        }
    }
}