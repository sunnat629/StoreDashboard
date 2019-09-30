package dev.sunnat629.storedashboard.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import dev.sunnat629.storedashboard.R
import dev.sunnat629.storedashboard.models.entities.Books
import dev.sunnat629.storedashboard.utils.AppConstants.BOOK_POSITION
import dev.sunnat629.storedashboard.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.book_details.*
import javax.inject.Inject

class ContentActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    private lateinit var Books: Observer<Books>
    private lateinit var notificationObserver: Observer<List<Books>>

    private var bookPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        setContentView(R.layout.activity_content)

        intent.extras?.let {
            bookPosition = it.getInt(BOOK_POSITION)

        }
        setToolbar()
        setObserver()
    }

    private fun setObserver() {
        notificationObserver = Observer {
            val book = it[bookPosition]
            Picasso.get()
                .load(book.coverImage)
                .placeholder(R.drawable.user)
                .into(book_cover)

            book_name.text = book.bookName
            book_price.text = "\$ ${book.price}"
            stock_value.hint = book.virtualStock
            //todo
            in_stock_value.text = book.inStock.toString()
            pending_value.text = book.requestPending.toString()
        }
    }

    private fun setToolbar() {
    }
}