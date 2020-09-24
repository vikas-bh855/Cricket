package com.sportsinteractive.cricket.adapter

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sportsinteractive.cricket.model.Players
import com.sportsinteractive.cricket.databinding.ItemPlayersBinding


class PlayerAdapter : ListAdapter<Players, PlayerAdapter.CricketViewHolder>(
    CricketDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CricketViewHolder {
        return CricketViewHolder(
            ItemPlayersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CricketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CricketViewHolder(private val itemPlayersBinding: ItemPlayersBinding) :
        RecyclerView.ViewHolder(itemPlayersBinding.root) {
        fun bind(
            player: Players
        ) {
            itemPlayersBinding.apply {
                players = player
                ValueAnimator.ofFloat(0.8f, 1f, 0.8f).apply {
                    addUpdateListener {
                        tvSpecialStatus.scaleX = it.animatedValue as Float
                        tvSpecialStatus.scaleY = it.animatedValue as Float
                    }
                    duration = 10000
                    repeatCount = 1000
                }.start()

            }
        }

    }

    class CricketDiffUtil : DiffUtil.ItemCallback<Players>() {
        override fun areItemsTheSame(oldItem: Players, newItem: Players): Boolean {
            return oldItem.Name_Full == newItem.Name_Full
        }

        override fun areContentsTheSame(oldItem: Players, newItem: Players): Boolean {
            return oldItem == newItem
        }
    }
}
