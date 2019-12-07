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
        // Here you create tables match
        db.createTable(
            FavoriteMatch.TABLE_FAVORITE,
            true,
            FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatch.ID_EVENT to TEXT + UNIQUE,
            FavoriteMatch.LEAGUE_NAME to TEXT,
            FavoriteMatch.DATE_EVENT to TEXT,
            FavoriteMatch.HOME_TEAM_BADGE to TEXT,
            FavoriteMatch.HOME_TEAM_NAME to TEXT,
            FavoriteMatch.HOME_GOAL_DETAIL to TEXT,
            FavoriteMatch.HOME_SCORE to TEXT,
            FavoriteMatch.AWAY_SCORE to TEXT,
            FavoriteMatch.AWAY_TEAM_BADGE to TEXT,
            FavoriteMatch.AWAY_TEAM_NAME to TEXT,
            FavoriteMatch.AWAY_GOAL_DETAIL to TEXT,
            FavoriteMatch.HOME_LINEUP_GOAL_KEEPER to TEXT,
            FavoriteMatch.AWAY_LINEUP_GOAL_KEEPER to TEXT,
            FavoriteMatch.HOME_LINEUP_DEFENSE to TEXT,
            FavoriteMatch.AWAY_LINEUP_DEFENSE to TEXT,
            FavoriteMatch.HOME_LINEUP_MIDFIELD to TEXT,
            FavoriteMatch.AWAY_LINEUP_MIDFIELD to TEXT,
            FavoriteMatch.HOME_LINEUP_FORWARD to TEXT,
            FavoriteMatch.AWAY_LINEUP_FORWARD to TEXT,
            FavoriteMatch.HOME_LINEUP_SUBSTITUTES to TEXT,
            FavoriteMatch.AWAY_LINEUP_SUBSTITUTES to TEXT,
            FavoriteMatch.TYPE to TEXT
        )

        // Here you create tables team
        db.createTable(
            FavoriteTeam.TABLE_TEAM,
            true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.ID_TEAM to TEXT + UNIQUE,
            FavoriteTeam.TEAM_LOGO to TEXT,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_DESCRIPTION to TEXT,
            FavoriteTeam.LEAGUE_NAME to TEXT,
            FavoriteTeam.STADIUM_NAME to TEXT,
            FavoriteTeam.STADIUM_LOC to TEXT,
            FavoriteTeam.STADIUM_DESC to TEXT,
            FavoriteTeam.STADIUM_CAPACITY to TEXT,
            FavoriteTeam.COUNTRY to TEXT,
            FavoriteTeam.STADIUM_BANNER to TEXT,
            FavoriteTeam.TEAM_JERSEY to TEXT,
            FavoriteTeam.TEAM_BANNER to TEXT,
            FavoriteTeam.SPORT to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteMatch.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: FootballDatabaseOpenHelper
    get() = FootballDatabaseOpenHelper.getInstance(applicationContext)