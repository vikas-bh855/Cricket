package com.sportsinteractive.cricket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sportsinteractive.cricket.model.Players
import com.sportsinteractive.cricket.databinding.ItemTeamsBinding


class TeamAdapter(val hashMap: LinkedHashMap<String, ArrayList<Players>>) :
    ListAdapter<String, TeamAdapter.CricketViewHolder>(
        CricketDiffUtil()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CricketViewHolder {
        return CricketViewHolder(
            ItemTeamsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CricketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CricketViewHolder(private val itemPlayersBinding: ItemTeamsBinding) :
        RecyclerView.ViewHolder(itemPlayersBinding.root) {
        fun bind(
            player: String
        ) {
            val playerList = hashMap[player]
            itemPlayersBinding.apply {
                rvPlayersName.apply {
                    adapter = PlayerAdapter()
                        .apply { submitList(playerList!!) }
                }
            }
        }

    }

    class CricketDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
