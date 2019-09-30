package dev.sunnat629.storedashboard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.sunnat629.storedashboard.R
import kotlinx.android.synthetic.main.notice_fragment.*

class NoticeFragment : BaseFragment() {

    companion object {
        fun newInstance() = NoticeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.notice_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        message.text = resources.getString(R.string.nothing)
    }
}
