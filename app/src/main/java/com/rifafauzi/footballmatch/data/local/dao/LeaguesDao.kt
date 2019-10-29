package com.rifafauzi.footballmatch.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rifafauzi.footballmatch.data.local.entity.Leagues


/**
 * Created by rrifafauzikomara on 2019-10-29.
 */

@Dao
interface LeaguesDao {

    @Query("SELECT * FROM leagues")
    fun getAllLeagues(): LiveData<List<Leagues>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(nowPlayings: List<Leagues>)

}