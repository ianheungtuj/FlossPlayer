package com.example.flossplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class BookPlayerFragment : Fragment() {

    private lateinit var bookViewModel: BookViewModel
    //private lateinit var bookfragment: BookFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookViewModel = ViewModelProvider(requireActivity())[BookViewModel::class.java]

        bookViewModel.getSelectedBook().observe(viewLifecycleOwner, Observer { book ->
            val bookFragment = childFragmentManager.findFragmentById(R.id.bookFragment) as BookFragment?
            bookFragment?.setBook(book)

        })
    }
}