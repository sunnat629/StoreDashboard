package dev.sunnat629.storedashboard.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.sunnat629.storedashboard.R


/**
 * FooterViewHolder.kt
 * This is a RecyclerView ViewHolder and show the footer view of the recyclerview
 *
 * @param itemView is the View of the RecyclerView
 * */
class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo() {}

    companion object {
        fun create(parent: ViewGroup): HeaderViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.see_all, parent, false)
            return HeaderViewHolder(view)
        }
    }
}