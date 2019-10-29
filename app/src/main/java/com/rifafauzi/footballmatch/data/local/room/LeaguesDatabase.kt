package com.rifafauzi.footballmatch.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rifafauzi.footballmatch.data.local.dao.LeaguesDao
import com.rifafauzi.footballmatch.data.local.entity.Leagues
import com.rifafauzi.footballmatch.utils.Converters

/**
 * Created by rrifafauzikomara on 2019-10-29.
 */

@Database(entities = [Leagues::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LeaguesDatabase : RoomDatabase() {

    abstract fun getAllLeagues() : LeaguesDao
}