package com.risakokato.zerosupport.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.risakokato.zerosupport.R
import com.risakokato.zerosupport.model.entity.BelongingsRoom

class MainAdapter(private var list: List<BelongingsRoom>, private val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    lateinit var listener: OnCheckedChangeListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_today, parent, false))
        vh.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            listener.onChecked(buttonView, isChecked)
        }
        return vh
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkBox.isChecked = list[position].isChecked
        holder.checkBox.text = list[position].belongings
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox = itemView.findViewById<CheckBox>(R.id.check_box_today_item)
    }

    fun setList(new: List<BelongingsRoom>) {
        list = new
    }


    interface OnCheckedChangeListener {
        fun onChecked(buttonView: View, isChecked: Boolean)
    }
}


