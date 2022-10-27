package com.arief.news.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.arief.news.R
import com.arief.news.databinding.RvNewsBinding
import com.arief.news.model.News
import com.arief.news.ui.business.BusinessFragment
import com.arief.news.ui.business.BusinessFragmentDirections
import com.arief.news.utils.DateHelper
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(private var items: List<News>, private val fragment: Fragment) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var allItems: List<News> = listOf()
    private val dateHelper = DateHelper()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvNewsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(val binding: RvNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News) {

            binding.date.text = dateHelper.changeFormatTime(item.publishedAt)
            binding.source.text = item.source.name
            if(!item.urlToImage.isNullOrEmpty()) {
                Glide.with(fragment)
                    .load(item.urlToImage)
                    .error(R.drawable.no_image)
                    .into(binding.poster)
            }
            binding.title.text = item.title
            binding.description.text = item.description

            binding.mainCard.setOnClickListener {
                fragment.findNavController().navigate(BusinessFragmentDirections.actionBusinessFragmentToDetailFragment(item))
            }
            binding.executePendingBindings()
        }
    }

    fun filterData(search: String){
        if(search.isEmpty()){
            this.items = this.allItems
        }else {
            val temp = this.allItems.filter { it.title.contains(search, true) }
            this.items = temp
        }
        notifyDataSetChanged()
    }

    fun setData(data: List<News>) {
        this.items = data
        allItems = data
        notifyDataSetChanged()
    }
}