package dev.sunnat629.storedashboard.ui

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import dev.sunnat629.storedashboard.R
import dev.sunnat629.storedashboard.ui.activities.MainActivity
import dev.sunnat629.storedashboard.ui.fragments.*

class TabsPagerAdapter(private val context: MainActivity, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment.newInstance(context)
            1 -> StockFragment.newInstance()
            2 -> ProgressFragment.newInstance()
            3 -> AccountFragment.newInstance()
            4 -> NoticeFragment.newInstance()
            else -> HomeFragment.newInstance(context)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 5
    }

    companion object {

        @StringRes
        private val TAB_TITLES =
            intArrayOf(
                R.string.tab_text_1,
                R.string.tab_text_2,
                R.string.tab_text_3,
                R.string.tab_text_4,
                R.string.tab_text_5
            )
    }
}