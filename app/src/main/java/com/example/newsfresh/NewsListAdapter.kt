package com.example.newsfresh
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
      val viewHolder=NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        view.findViewById<Button>(R.id.shareButton).setOnClickListener {
            listener.onShareButtonClicked(items[viewHolder.adapterPosition])
        }
        return  viewHolder
    }

    override fun getItemCount(): Int {
    return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
     val currentItem=items[position]
        holder.titleView.text=currentItem.title
        holder.author.text= currentItem.source.toString()
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }


    fun updateNews(updateNews:ArrayList<News>){
        items.clear()
        items.addAll(updateNews)
        notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val titleView=itemView.findViewById<TextView>(R.id.title)
    val image=itemView.findViewById<ImageView>(R.id.image)
    val author=itemView.findViewById<TextView>(R.id.source)
}
interface NewsItemClicked{
    fun onItemClicked(item:News)
    fun onShareButtonClicked(item: News)
}