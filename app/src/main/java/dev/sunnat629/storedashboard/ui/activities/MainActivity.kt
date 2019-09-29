package dev.sunnat629.storedashboard.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import dev.sunnat629.storedashboard.R
import dev.sunnat629.storedashboard.ui.TabsPagerAdapter
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.tab_items.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val tabsPagerAdapter = TabsPagerAdapter(this, supportFragmentManager)

        view_pager.adapter = tabsPagerAdapter

        tabs.setupWithViewPager(view_pager)

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }
    }
}