package dev.sunnat629.storedashboard.ui.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import dev.sunnat629.storedashboard.R
import dev.sunnat629.storedashboard.models.entities.Books
import dev.sunnat629.storedashboard.utils.AppConstants.BOOK_POSITION
import dev.sunnat629.storedashboard.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.book_details.*
import javax.inject.Inject


class ContentActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    private lateinit var singleBook: Books
    private var stockValue = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        setContentView(R.layout.activity_content)

        intent.extras?.let {
            singleBook = it.getParcelable(BOOK_POSITION)!!
        }
        setToolbar()
        setObserver()
        initButtons()
    }

    private fun initButtons() {
        details_update.setOnClickListener {
            viewModel.notification.value = stockValue
        }
    }

    private fun setObserver() {
        Picasso.get()
            .load(singleBook.coverImage)
            .placeholder(R.drawable.user)
            .into(book_cover)

        book_name.text = singleBook.bookName
        book_price.text = "\$ ${singleBook.price}"
        stock_value.setText(stockValue.toString())
        in_stock_value.text = singleBook.inStock.toString()
        pending_value.text = singleBook.requestPending.toString()

        stock_value.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if (it.isEmpty()) {
                        stockValue = 0
                        stock_value.append(stockValue.toString())
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    if (it.isNotEmpty()) {
                        stockValue = s.toString().toInt()
                    }
                }
            }
        })
    }

    fun addValue(view: View) {
        stockValue++
        stock_value.setText(stockValue.toString())
    }

    fun reduceValue(view: View) {
        if (stockValue > 0) stockValue--
        stock_value.setText(stockValue.toString())
    }

    private fun setToolbar() {
        setSupportActionBar(book_details_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}