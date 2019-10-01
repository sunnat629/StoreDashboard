package dev.sunnat629.storedashboard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import dev.sunnat629.storedashboard.R
import dev.sunnat629.storedashboard.models.entities.Books
import dev.sunnat629.storedashboard.ui.adapters.BookAdapter
import kotlinx.android.synthetic.main.stock_fragment.*

class StockFragment : BaseFragment() {

    private lateinit var bookListObserver: Observer<List<Books>>
    private lateinit var networkStateObserver: Observer<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.stock_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        initObserver()
    }

    private fun initObserver() {
        bookListObserver = Observer {
            stock_recycler_view.layoutManager = GridLayoutManager(context, 2)
            stock_recycler_view.adapter = BookAdapter(context, it, false)
            stock_recycler_view.recycledViewPool
        }

        networkStateObserver = Observer {
            Toast.makeText(
                context,
                "Please Check Internet Connection",
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModel.allBooks.observe(this, bookListObserver)
        viewModel.errorMessage.observe(this, networkStateObserver)
    }

    private fun initAdapter() {

    }

    override fun onDetach() {
        super.onDetach()
        viewModel.allBooks.removeObservers(this) // remove all observers of notification after destroy this activity
        viewModel.errorMessage.removeObservers(this) // remove all observers of notification after destroy this activity
    }

    companion object {
        fun newInstance() = StockFragment()
    }
}