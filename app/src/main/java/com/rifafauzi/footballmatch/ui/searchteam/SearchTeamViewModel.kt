package com.rifafauzi.footballmatch.ui.searchteam

import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.repository.teams.TeamsRepository
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
class SearchTeamViewModel @Inject constructor(private val repository: TeamsRepository) : BaseViewModel()