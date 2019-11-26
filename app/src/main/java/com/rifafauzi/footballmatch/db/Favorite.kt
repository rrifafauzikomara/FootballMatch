package com.rifafauzi.footballmatch.db

/**
 * Created by rrifafauzikomara on 2019-11-26.
 */
 
data class Favorite(
    val id: Long?,
    val strLeague: String?,
    val dateEvent: String?,

    val strTeamHomeBadge: String?,
    val strHomeTeam: String?,
    val strHomeGoalDetails: String?,
    val intHomeScore: String?,

    val intAwayScore: String?,
    val strTeamAwayBadge: String?,
    val strAwayTeam: String?,
    val strAwayGoalDetails: String?,

    val strHomeLineupGoalkeeper: String?,
    val strAwayLineupGoalkeeper: String?,

    val strHomeLineupDefense: String?,
    val strAwayLineupDefense: String?,

    val strHomeLineupMidfield: String?,
    val strAwayLineupMidfield: String?,

    val strHomeLineupForward: String?,
    val strAwayLineupForward: String?,

    val strHomeLineupSubstitutes: String?,
    val strAwayLineupSubstitutes: String?,

    val type: String?
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val LEAGUE_NAME: String = "LEAGUE_NAME"
        const val DATE_EVENT: String = "DATE_EVENT"

        const val HOME_TEAM_BADGE: String = "HOME_TEAM_BADGE"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val HOME_GOAL_DETAIL: String = "HOME_GOAL_DETAIL"
        const val HOME_SCORE: String = "HOME_SCORE"

        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val AWAY_TEAM_BADGE: String = "AWAY_TEAM_BADGE"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val AWAY_GOAL_DETAIL: String = "AWAY_GOAL_DETAIL"

        const val HOME_LINEUP_GOAL_KEEPER: String = "HOME_LINEUP_GOAL_KEEPER"
        const val AWAY_LINEUP_GOAL_KEEPER: String = "AWAY_LINEUP_GOAL_KEEPER"

        const val HOME_LINEUP_DEFENSE: String = "HOME_LINEUP_DEFENSE"
        const val AWAY_LINEUP_DEFENSE: String = "AWAY_LINEUP_DEFENSE"

        const val HOME_LINEUP_MIDFIELD: String = "HOME_LINEUP_MIDFIELD"
        const val AWAY_LINEUP_MIDFIELD: String = "AWAY_LINEUP_MIDFIELD"

        const val HOME_LINEUP_FORWARD: String = "HOME_LINEUP_FORWARD"
        const val AWAY_LINEUP_FORWARD: String = "AWAY_LINEUP_FORWARD"

        const val HOME_LINEUP_SUBSTITUTES: String = "HOME_LINEUP_SUBSTITUTES"
        const val AWAY_LINEUP_SUBSTITUTES: String = "AWAY_LINEUP_SUBSTITUTES"
        const val TYPE: String = "TYPE"
    }

}