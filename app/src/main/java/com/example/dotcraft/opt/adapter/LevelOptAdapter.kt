package com.example.dotcraft.opt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dotcraft.App
import com.example.dotcraft.R
import com.example.dotcraft.level.bean.LevelBean
import com.example.dotcraft.level.strategy.LevelStrategy

class LevelOptAdapter(private val mContext: Context, private val mListener: OnItemListener) :
    RecyclerView.Adapter<LevelOptAdapter.LevelOptViewHolder>() {
    private val levelStrategyList: ArrayList<LevelBean> = ArrayList()

    init {
        //反射
        for (i in 1..9) {
            val className = "com.example.dotcraft.level.strategy.Level$i"
            val clazz = Class.forName(className)
            val level = clazz.newInstance()
            val bean = LevelBean(level as LevelStrategy)
            bean.passed = i <= App.getContext()!!.mLevelPassed
            levelStrategyList.add(bean)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelOptViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_level, parent, false)
        return LevelOptViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelOptViewHolder, position: Int) {
        holder.textView!!.text = (position + 1).toString()
        if (levelStrategyList[position].passed) {
            holder.textView!!.background =
                ContextCompat.getDrawable(mContext, R.drawable.shape_passed_bg)
        } else {
            ContextCompat.getDrawable(mContext, R.drawable.shape_not_passed_bg)
        }
        holder.itemView.setOnClickListener() {
            mListener.onClick(levelStrategyList[position])
        }
    }

    override fun getItemCount(): Int = levelStrategyList.size


    inner class LevelOptViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: AppCompatTextView? = null

        init {
            textView = itemView.findViewById(R.id.tv_level_sequence)
        }
    }

    interface OnItemListener {
        fun onClick(level: LevelBean)
    }

}