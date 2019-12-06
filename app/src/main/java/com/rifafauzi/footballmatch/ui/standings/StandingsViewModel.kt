package com.rifafauzi.footballmatch.ui.standings

import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.repository.standings.StandingsRepository
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
class StandingsViewModel @Inject constructor(private val repository: StandingsRepository) : BaseViewModel()