package com.example.flossplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(_bookList: BookList, _callback:
    (Book)->Unit): RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    private val callback = _callback
    private val bookList = _bookList

    inner class MyViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {
        var bookTitleView: TextView? = null
        var bookAuthorView: TextView? = null
        init{
            bookTitleView = layout.findViewById(R.id.bookTitleRecyclerView)
            bookAuthorView = layout.findViewById(R.id.bookAuthorRecyclerView)
            bookTitleView?.setOnClickListener{callback(bookList.get(adapterPosition))}
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = bookList.get(position)
        holder.bookTitleView?.text = book.title
        holder.bookAuthorView?.text = book.author
    }

    override fun getItemCount() = bookList.size()
}