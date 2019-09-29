package dev.sunnat629.storedashboard.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import dev.sunnat629.storedashboard.R
import dev.sunnat629.storedashboard.viewmodels.MainViewModel
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.allBooks.observe(this, Observer {
            Timber.tag("ASDF").d(it.toString())
        })

        viewModel.errorMessage.observe(this, Observer {
            Timber.tag("ASDF").e(it.toString())
        })

        viewModel.userDetails.observe(this, Observer {
            Timber.tag("ASDF").d(it.toString())
        })
    }
}
