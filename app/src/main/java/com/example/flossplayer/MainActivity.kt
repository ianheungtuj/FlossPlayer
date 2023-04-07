package com.example.flossplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private val bookList = BookList()
    private var hasTwoContainers = false
    private lateinit var customRecyclerAdapter: CustomRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hasTwoContainers = findViewById<View>(R.id.bookPlayerView) != null

        val bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        bookList.add(Book("Book 1", "Author 1"))
        bookList.add(Book("Book 2", "Author 2"))
        bookList.add(Book("Book 3", "Author 3"))
        bookList.add(Book("Book 4", "Author 4"))
        bookList.add(Book("Book 5", "Author 5"))
        bookList.add(Book("Book 6", "Author 6"))
        bookList.add(Book("Book 7", "Author 7"))
        bookList.add(Book("Book 8", "Author 8"))
        bookList.add(Book("Book 9", "Author 9"))
        bookList.add(Book("Book 10", "Author 10"))

        bookViewModel.setBook(bookList)

        val manager = supportFragmentManager
        var fragment1 = BookListFragment()

        if (savedInstanceState == null) {
            if (hasTwoContainers) {
                if (fragment1 != null) {
                    manager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment1)
                        .commit()
                }
                manager.beginTransaction()
                    .replace(R.id.bookPlayerView, BookPlayerFragment())
                    .commit()
            } else {
                manager.beginTransaction()
                    .replace(R.id.fragmentContainerView, BookListFragment())
                    .commit()
            }
        }
    }
}