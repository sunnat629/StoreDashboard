package dev.sunnat629.storedashboard.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import dev.sunnat629.storedashboard.R
import dev.sunnat629.storedashboard.ui.TabsPagerAdapter
import dev.sunnat629.storedashboard.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.tab_items.*
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    lateinit var notificationObserver: Observer<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        setContentView(R.layout.main_activity)
        setToolbar()
        setTabLayouts()
        setObserver()
    }

    private fun setObserver() {
        notificationObserver = Observer { count ->
            tab_layout.getTabAt(4)?.orCreateBadge?.let {
                it.isVisible = true
                it.number = count
            }
        }

        viewModel.notification.observe(this, notificationObserver)
    }

    private fun setToolbar() {
        toolbar.title = resources.getString(R.string.app_name)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toolbar.setTitleTextColor(resources.getColor(R.color.colorPrimary, null))
        } else {
            toolbar.setTitleTextColor(resources.getColor(R.color.colorPrimary))
        }
        setSupportActionBar(toolbar)
    }

    private fun setTabLayouts() {
        val tabsPagerAdapter = TabsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = tabsPagerAdapter
        tab_layout.setupWithViewPager(view_pager)

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 0) {
                    toolbar.visibility = View.VISIBLE
                } else {
                    toolbar.visibility = View.GONE
                }
            }
        })


        tab_layout.getTabAt(0)?.icon = resources.getDrawable(R.drawable.home, null)
        tab_layout.getTabAt(1)?.icon = resources.getDrawable(R.drawable.hashtag, null)
        tab_layout.getTabAt(2)?.icon = resources.getDrawable(R.drawable.trending, null)
        tab_layout.getTabAt(3)?.icon = resources.getDrawable(R.drawable.user, null)
        tab_layout.getTabAt(4)?.icon = resources.getDrawable(R.drawable.ic_notifications, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.notification.removeObservers(this) // remove all observers of notification after destroy this activity
    }
}