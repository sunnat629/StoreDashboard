package dev.sunnat629.storedashboard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import dev.sunnat629.storedashboard.OnDetailsClickListener
import dev.sunnat629.storedashboard.R
import dev.sunnat629.storedashboard.models.entities.Books
import dev.sunnat629.storedashboard.models.entities.Users
import dev.sunnat629.storedashboard.ui.adapters.BookAdapter
import kotlinx.android.synthetic.main.home_header_layout.*
import kotlinx.android.synthetic.main.horiz_list.*

class HomeFragment : BaseFragment() {

    private lateinit var bookListObserver: Observer<List<Books>>
    private lateinit var networkStateObserver: Observer<String>
    private lateinit var userDetailsObserver: Observer<Users>
    private lateinit var listener: OnDetailsClickListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        initObserver()
    }

    private fun initObserver() {

        userDetailsObserver = Observer {
            h_profile_name.text = it.name
            Picasso.get()
                .load(it.avatarUrl)
                .placeholder(R.drawable.user)
                .into(h_profile_image)

            h_show_details.setOnClickListener {
                listener.goToUserDetails()
            }
        }

        bookListObserver = Observer {
            home_recycler_view.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            home_recycler_view.adapter = BookAdapter(context, it.take(4), true)
            home_recycler_view.recycledViewPool
        }

        networkStateObserver = Observer {
            Toast.makeText(
                context,
                "Please Check Internet Connection",
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModel.userDetails.observe(this, userDetailsObserver)
        viewModel.allBooks.observe(this, bookListObserver)
        viewModel.errorMessage.observe(this, networkStateObserver)
    }

    private fun initAdapter() {

    }

    override fun onDetach() {
        super.onDetach()
        viewModel.userDetails.removeObservers(this) // remove all observers of notification after destroy this activity
        viewModel.allBooks.removeObservers(this) // remove all observers of notification after destroy this activity
        viewModel.errorMessage.removeObservers(this) // remove all observers of notification after destroy this activity
    }

    fun setOnDetailsClickListener(listener: OnDetailsClickListener) {
        this.listener = listener
    }

    companion object {
        fun newInstance(listener: OnDetailsClickListener): HomeFragment {
            val fragment = HomeFragment()
            fragment.setOnDetailsClickListener(listener)
            return fragment
        }
    }
}