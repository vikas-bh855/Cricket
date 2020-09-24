package com.sportsinteractive.cricket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.GsonBuilder
import com.sportsinteractive.cricket.adapter.TeamAdapter
import com.sportsinteractive.cricket.databinding.FragmentSquadBinding
import com.sportsinteractive.cricket.model.Cricket
import com.sportsinteractive.cricket.model.Players
import com.sportsinteractive.cricket.model.STATUS
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

@AndroidEntryPoint
class FragmentSquad : Fragment() {
    private lateinit var binding: FragmentSquadBinding
    private var hashMap: LinkedHashMap<String, ArrayList<Players>> = LinkedHashMap()
    private var listPlayers: ArrayList<Players>? = null
    private var listTeams: ArrayList<String>? = arrayListOf()
    private val viewModel: SquadViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSquadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.liveDataResult.observe(viewLifecycleOwner, Observer<ResultData<Cricket>> {
                when (it.status) {
                    STATUS.LOADING -> { /*********loading**********/}
                    STATUS.ERROR -> { Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show() }
                    STATUS.SUCCESS -> {
                        val cricket = it.cricket as Cricket
                        val teams = JSONObject(cricket.Teams.toString())
                        teams.keys().forEach {
                            try {
                                listPlayers = arrayListOf()
                                val team = JSONObject(teams.get(it).toString())    //fetched team object eg 6
                                val players = team.optJSONObject("Players")//fetched player object from teams
                                players!!.keys().forEach {
                                    val player = GsonBuilder().create().fromJson(players.getJSONObject(it).toString(), Players::class.java)
                                    listPlayers!!.add(player)
                                }
                                hashMap[team.optString("Name_Full")] = listPlayers!!
                                listTeams!!.add(team.optString("Name_Full"))
                            } catch (e: Exception) { }
                        }
                        binding.apply {
                            vpCricket.adapter = TeamAdapter(hashMap).apply { submitList(listTeams!!) }
                            TabLayoutMediator(tbMain, vpCricket, TabLayoutMediator.TabConfigurationStrategy { tab, position -> tab.text = listTeams!![position] }).attach()
                            prBar.visibility = View.GONE
                            if(listTeams!!.size>0)
                            tvTeamHomeAway.text = listTeams!![0].toUpperCase() + "  vs  " + listTeams!![1].toUpperCase()
                        }
                    }
                }
            })
    }
}
