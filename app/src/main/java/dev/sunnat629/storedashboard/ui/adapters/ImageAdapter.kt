package dev.sunnat629.storedashboard.ui.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.sunnat629.storedashboard.R
import dev.sunnat629.storedashboard.models.entities.Books

class ImageAdapter(
    private val context: Context,
    private val bookList: List<Books>
) : RecyclerView.Adapter<BookViewHolder>() {

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindTo(context, bookList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return when (viewType) {
            // This layout will show, if data fetched successfully
            R.layout.book_list_items -> BookViewHolder.create(parent)

            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.book_list_items
    }

    override fun getItemCount(): Int = bookList.size
}