package com.example.guillermo.marvelgalleryscratch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import android.util.Log


class RecyclerPViewAdapter(val recyclerview: RecyclerView, var items: List<PokemonPreview> = listOf()) : RecyclerView.Adapter<PokemonViewHolder>() {
/*    var onLoadMoreListener:OnLoadMoreListener? = null
    init {
        val layoutManager = recyclerview.layoutManager as LinearLayoutManager
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                Log.d("onScrolled", items.size.toString())
                if (lastVisiblePosition == items.size - 1) {
                    Log.d("Coso", items.size.toString())
                    onLoadMoreListener?.onLoadMore();

                }
            }
        })
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_cardview, parent,  false)
        return PokemonViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(items[position])
    }




}