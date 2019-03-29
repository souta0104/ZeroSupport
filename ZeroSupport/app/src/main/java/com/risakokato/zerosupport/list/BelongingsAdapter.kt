package com.risakokato.zerosupport.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.risakokato.zerosupport.R
import com.risakokato.zerosupport.model.entity.BelongingsRoom

class BelongingsAdapter(private val context: Context?, private var mList: List<BelongingsRoom>) : RecyclerView.Adapter<BelongingsAdapter.ViewHolder>() {

    lateinit var listener: OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_list, parent, false))
        return vh
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.belongings.text = mList[position].belongings
        holder.dateText.text = mList[position].date
        holder.view.setOnLongClickListener {
            listener.setOnLongClickListener(position)
            true
        }
    }

    fun setList(new: List<BelongingsRoom>) {
        mList = new
    }

    interface OnClickListener {
        fun setOnLongClickListener(position: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view = itemView.findViewById<ConstraintLayout>(R.id.container)
        val belongings = itemView.findViewById<TextView>(R.id.belongingsText)
        val dateText = itemView.findViewById<TextView>(R.id.dateText)
    }

}