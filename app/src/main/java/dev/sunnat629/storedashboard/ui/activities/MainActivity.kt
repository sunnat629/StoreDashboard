package dev.sunnat629.storedashboard.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import dev.sunnat629.storedashboard.OnDetailsClickListener
import dev.sunnat629.storedashboard.R
import dev.sunnat629.storedashboard.ui.TabsPagerAdapter
import dev.sunnat629.storedashboard.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.tab_items.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnDetailsClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    private lateinit var networkStateObserver: Observer<String>

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
        networkStateObserver = Observer {
            Toast.makeText(
                applicationContext,
                "Please Check Internet Connection",
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModel.errorMessage.observe(this, networkStateObserver)
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
                if (tab?.position != 4) {
                    tab_layout.getTabAt(4)?.orCreateBadge?.let {
                        it.isVisible = true
                        it.number = 10
                    }
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 4) tab_layout.getTabAt(4)?.removeBadge()
                toolbar.visibility = if (tab.position == 0) View.VISIBLE else View.GONE
            }
        })

        tab_layout.getTabAt(0)?.icon = resources.getDrawable(R.drawable.home, null)
        tab_layout.getTabAt(1)?.icon = resources.getDrawable(R.drawable.hashtag, null)
        tab_layout.getTabAt(2)?.icon = resources.getDrawable(R.drawable.trending, null)
        tab_layout.getTabAt(3)?.icon = resources.getDrawable(R.drawable.user, null)
        tab_layout.getTabAt(4)?.icon = resources.getDrawable(R.drawable.ic_notifications, null)
        tab_layout.getTabAt(4)?.orCreateBadge?.let {
            it.isVisible = true
            it.number = 10
        }
    }

    override fun goToUserDetails() {
        tab_layout.getTabAt(3)?.select()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.errorMessage.removeObservers(this) // remove all observers of notification after destroy this activity
    }
}