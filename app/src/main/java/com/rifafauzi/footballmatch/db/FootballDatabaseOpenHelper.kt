package com.rifafauzi.footballmatch.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by rrifafauzikomara on 2019-11-26.
 */
 
class FootballDatabaseOpenHelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {

    companion object {
        private var instance: FootballDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FootballDatabaseOpenHelper {
            if (instance == null) {
                instance = FootballDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as FootballDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(
            Favorite.TABLE_FAVORITE,
            true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.ID_EVENT to TEXT + UNIQUE,
            Favorite.LEAGUE_NAME to TEXT,
            Favorite.DATE_EVENT to TEXT,
            Favorite.HOME_TEAM_BADGE to TEXT,
            Favorite.HOME_TEAM_NAME to TEXT,
            Favorite.HOME_GOAL_DETAIL to TEXT,
            Favorite.HOME_SCORE to TEXT,
            Favorite.AWAY_SCORE to TEXT,
            Favorite.AWAY_TEAM_BADGE to TEXT,
            Favorite.AWAY_TEAM_NAME to TEXT,
            Favorite.AWAY_GOAL_DETAIL to TEXT,
            Favorite.HOME_LINEUP_GOAL_KEEPER to TEXT,
            Favorite.AWAY_LINEUP_GOAL_KEEPER to TEXT,
            Favorite.HOME_LINEUP_DEFENSE to TEXT,
            Favorite.AWAY_LINEUP_DEFENSE to TEXT,
            Favorite.HOME_LINEUP_MIDFIELD to TEXT,
            Favorite.AWAY_LINEUP_MIDFIELD to TEXT,
            Favorite.HOME_LINEUP_FORWARD to TEXT,
            Favorite.AWAY_LINEUP_FORWARD to TEXT,
            Favorite.HOME_LINEUP_SUBSTITUTES to TEXT,
            Favorite.AWAY_LINEUP_SUBSTITUTES to TEXT,
            Favorite.TYPE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: FootballDatabaseOpenHelper
    get() = FootballDatabaseOpenHelper.getInstance(applicationContext)