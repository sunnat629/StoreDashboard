package dev.sunnat629.storedashboard.ui.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.sunnat629.storedashboard.models.entities.Books
import dev.sunnat629.storedashboard.utils.AppConstants.TYPE_FOOTER
import dev.sunnat629.storedashboard.utils.AppConstants.TYPE_ITEM

class BookAdapter(
    private val context: Context?,
    private val bookList: List<Books>,
    private val needShort: Boolean
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookViewHolder) {
            holder.bindTo(context, bookList[position])
        } else if (holder is FooterViewHolder) {
            holder.bindTo()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ITEM -> BookViewHolder.create(parent)
            TYPE_FOOTER -> FooterViewHolder.create(parent)
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (needShort and (position == bookList.size - 1)) TYPE_FOOTER else TYPE_ITEM
    }

    override fun getItemCount(): Int = bookList.size
}