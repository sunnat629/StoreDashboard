package dev.sunnat629.storedashboard.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.sunnat629.storedashboard.R
import dev.sunnat629.storedashboard.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}
