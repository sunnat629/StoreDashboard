package dev.sunnat629.storedashboard.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.sunnat629.storedashboard.R
import dev.sunnat629.storedashboard.models.entities.Books
import dev.sunnat629.storedashboard.ui.activities.ContentActivity
import dev.sunnat629.storedashboard.utils.AppConstants
import kotlinx.android.synthetic.main.book_list_items.view.*


/**
 * BookViewHolder.kt
 * This is a RecyclerView ViewHolder and show each row when the data fetched successfully
 *
 * @param itemView is the View of the RecyclerView
 * */
class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(context: Context?, singleBook: Books?) {
        singleBook?.let {
            itemView.l_book_name.text = it.bookName
            itemView.l_book_price.text = "\$ ${it.price}"
            itemView.l_book_quantity.text = "Qty ${it.inStock}"

            Picasso.get()
                .load(it.coverImage)
                .placeholder(R.drawable.user)
                .into(itemView.l_book_image)

            itemView.l_update_button.setOnClickListener { _ ->
                val intent = Intent(context, ContentActivity::class.java)
                intent.putExtra(AppConstants.BOOK_POSITION, it)
                context?.startActivity(intent)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): BookViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.book_list_items, parent, false)
            return BookViewHolder(view)
        }
    }
}