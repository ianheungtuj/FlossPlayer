package com.example.flossplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookListFragment : Fragment() {
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // We store the data in a LiveData object, that is part of the ViewModel
        bookViewModel = ViewModelProvider(requireActivity())[BookViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view as RecyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 1)
            bookViewModel.getBooks().observe(requireActivity()) { it ->
                adapter = CustomRecyclerAdapter(it) {
                    bookViewModel.setSelectedBook(it)
                }
            }
        }
    }
}

class CustomRecyclerAdapter(private val bookList: BookList, private val callback:
    (Book)->Unit): RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>(){

    inner class MyViewHolder (val textView: TextView) : RecyclerView.ViewHolder(textView){
        init {
            textView.setOnClickListener { callback(bookList.get(adapterPosition))}
        }
    }

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int) =
        MyViewHolder(TextView(parent.context).apply {
        })

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.textView.setText(bookList.get(position).title + " " + bookList.get(position).author)


    override fun getItemCount() = bookList.size()
}