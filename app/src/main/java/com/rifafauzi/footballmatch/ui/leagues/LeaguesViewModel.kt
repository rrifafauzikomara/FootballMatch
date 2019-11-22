package com.rifafauzi.footballmatch.ui.leagues

import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.repository.LeaguesRepository
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */
 
class LeaguesViewModel @Inject constructor(private val repository: LeaguesRepository) : BaseViewModel() {}