package com.zii.berita

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lay_berita.view.*

class NewsAdapter(private val context: Context, private val listnews: List<news>) :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentNews: news? = null
        var currrentPosition: Int = 0

        fun setData(currnews: news, pos: Int) {
            itemView.tvw_Title.text = currnews.title
            itemView.tvw_Desc.text = currnews.desc
            itemView.img_news.setImageResource(currnews.photo)

            this.currentNews = currnews
            this.currrentPosition = pos
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.lay_berita, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listnews.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val datanews = listnews[position]
        holder.setData(datanews, position)
        holder.itemView.setOnClickListener() { this.onItemClickCallback.onItemClick(listnews[position]) }
    }
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClick(data: news)
    }
}