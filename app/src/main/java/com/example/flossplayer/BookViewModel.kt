package com.example.flossplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel: ViewModel() {
    private val selectedBook = MutableLiveData<Book>()
    private val bookList = MutableLiveData<BookList>()

    var hasSeenSelection = false

    fun setSelectedBook(book: Book){
        hasSeenSelection = false
        selectedBook.value = book
    }

    fun getSelectedBook() : LiveData<Book> {
        return selectedBook
    }

    fun setBook(list: BookList){
        this.bookList.value = list
    }

    fun getBooks() : LiveData<BookList> {
        return bookList
    }
}