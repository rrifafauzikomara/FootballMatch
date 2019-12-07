package com.rifafauzi.footballmatch.db

/**
 * Created by rrifafauzikomara on 2019-12-07.
 */
 
 data class FavoriteTeam(
    val id: Long,
    val idTeam: String,
    val strTeamBadge: String?,
    val strTeam: String?,
    val strDescriptionEN: String?,
    val strLeague: String?,
    val strStadium: String?,
    val strStadiumLocation: String?,
    val strStadiumDescription: String?,
    val intStadiumCapacity: String?,
    val strCountry: String?,
    val strStadiumThumb: String?,
    val strTeamJersey: String?,
    val strTeamBanner: String?,
    val strSport: String?
) {
    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val TEAM_LOGO: String = "TEAM_LOGO"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_DESCRIPTION: String = "TEAM_DESC"
        const val LEAGUE_NAME: String = "LEAGUE_NAME"
        const val STADIUM_NAME: String = "STADIUM_NAME"
        const val STADIUM_LOC: String = "STADIUM_LOC"
        const val STADIUM_DESC: String = "STADIUM_DESC"
        const val STADIUM_CAPACITY: String = "STADIUM_CAPACITY"
        const val COUNTRY: String = "COUNTRY"
        const val STADIUM_BANNER: String = "STADIUM_BANNER"
        const val TEAM_JERSEY: String = "TEAM_JERSEY"
        const val TEAM_BANNER: String = "TEAM_BANNER"
        const val SPORT: String = "SPORT"
    }
}