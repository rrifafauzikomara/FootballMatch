package com.rifafauzi.footballmatch.ui.leagues

import androidx.lifecycle.ViewModel
import com.rifafauzi.footballmatch.data.repository.Repository
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-10-29.
 */
 
class LeaguesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val listLeagues = repository.getAllLeagues()
}