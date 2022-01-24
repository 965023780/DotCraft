package com.example.dotcraft.rank.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dotcraft.R
import com.example.dotcraft.rank.bean.RankBean
import com.example.dotcraft.util.SharedPreferencesUtil

class RankAdapter(private val mContext: Context) :
    RecyclerView.Adapter<RankAdapter.RankOptViewHolder>() {
    private var rankStrategyList: List<RankBean>? = null

    init {
        rankStrategyList = SharedPreferencesUtil.instance.getRank()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankOptViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_rank, parent, false)
        return RankOptViewHolder(view)
    }

    override fun onBindViewHolder(holder: RankOptViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder.tvId?.text = mContext.getString(R.string.rank_id)
                holder.tvType?.text = mContext.getString(R.string.rank_type)
                holder.tvTime?.text = mContext.getString(R.string.rank_time)
            }
            else -> {
                holder.tvId?.text = rankStrategyList?.get(position - 1)?.id.toString()
                holder.tvType?.text = rankStrategyList?.get(position - 1)?.type
                holder.tvTime?.text = rankStrategyList?.get(position - 1)?.time.toString() + "s"
            }
        }
    }

    override fun getItemCount(): Int = rankStrategyList?.size?.plus(1) ?: 1


    inner class RankOptViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvId: AppCompatTextView? = null
        var tvType: AppCompatTextView? = null
        var tvTime: AppCompatTextView? = null

        init {
            tvId = itemView.findViewById(R.id.tv_rank_id)
            tvType = itemView.findViewById(R.id.tv_rank_type)
            tvTime = itemView.findViewById(R.id.tv_rank_time)
        }
    }

}