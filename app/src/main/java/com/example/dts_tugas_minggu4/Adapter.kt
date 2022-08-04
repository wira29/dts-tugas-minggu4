package com.example.dts_tugas_minggu4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class Adapter(private var dataset: Array<String>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val button: Button

        init {
            button = view.findViewById(R.id.item)
        }
    }

    fun listUpdate(array: Array<String>){
        dataset = array
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.button.text = dataset[position]
    }

    override fun getItemCount() = dataset.size
}