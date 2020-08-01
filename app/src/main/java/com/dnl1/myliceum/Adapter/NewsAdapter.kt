package com.dnl1.myliceum.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dnl1.myliceum.Model.News
import com.dnl1.myliceum.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_list_view.view.*

class NewsAdapter(private val context: Context, private val movieList: MutableList<News>): RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.news_list_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(movieList[position].image_thumbnail).into(holder.image)
        holder.news_title.text = movieList[position].title
        holder.news_short_description.text = movieList[position].short_description


        holder.itemView.setOnClickListener { view: View ->
            var bundle = bundleOf("selected_news_id" to movieList[position].id)
            view.findNavController().navigate(R.id.action_newsFragment_to_newsDetailFragment, bundle)
            //Toast.makeText(context, movieList[position].title, Toast.LENGTH_SHORT).show()
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image : ImageView
        var news_title : TextView
        var news_short_description : TextView

        init {
            image = itemView.news_list_thumbnail
            news_title = itemView.news_list_title
            news_short_description = itemView.news_list_short_description
        }


    }
}