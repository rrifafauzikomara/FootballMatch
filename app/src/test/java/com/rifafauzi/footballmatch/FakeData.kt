package com.rifafauzi.footballmatch

import com.rifafauzi.footballmatch.model.leagues.Leagues
import com.rifafauzi.footballmatch.model.match.Match
import com.rifafauzi.footballmatch.model.teams.Team

/**
 * Created by rrifafauzikomara on 2019-11-28.
 */

object FakeData {

    fun getDummyListLeagues() : List<Leagues> {
        return listOf(
            Leagues(
                "4406",
                "Argentinian Primera Division",
                "2015-02-13",
                "https://www.thesportsdb.com/images/media/league/banner/7x4xmv1567241754.jpg",
                "Soccer",
                "The Primera División (Spanish pronunciation: ; English: First Division), named Superliga Argentina (English: Argentine Superleague) since the 2017–18 season, is a professional football league in Argentina, organised by the homonymous entity, that is administrated independently and has its own statute.",
                "https://www.thesportsdb.com/images/media/league/badge/me5dez1517699947.png",
                "Argentina",
                "https://www.thesportsdb.com/images/media/league/trophy/xrvqvx1422244125.png"
            ),
            Leagues(
                "4500",
                "Copa Argentina",
                "0000-00-00",
                "https://www.thesportsdb.com/images/media/league/badge/mekufm1549460132.png",
                "Soccer",
                "The Copa Argentina (English: Argentine Cup), officially known as the \\\"Copa Total Argentina\\\" due to sponsorship reasons, is an official football cup competition organized by the Argentine Football Association (AFA), with the aim of qualifying one club to the Copa Libertadores.",
                "https://www.thesportsdb.com/images/media/league/badge/mekufm1549460132.png",
                "Argentina",
                null
            ),
            Leagues(
                "4356",
                "Australian A-League",
                "2013-10-11",
                "https://www.thesportsdb.com/images/media/league/banner/9s0bc11547476621.jpg",
                "Soccer",
                "The A-League is a professional men's soccer league, run by Football Federation Australia (FFA). At the top of the Australian league system, it is the country's primary competition for the sport",
                "https://www.thesportsdb.com/images/media/league/badge/sfhanl1547383730.png",
                "Australia",
                "https://www.thesportsdb.com/images/media/league/trophy/uxssyx1422266419.png"
            ),
            Leagues(
                "4338",
                "Belgian Jupiler League",
                "2012-07-28",
                "https://www.thesportsdb.com/images/media/league/banner/urswyw1424489964.jpg",
                "Soccer",
                "The Belgian Pro League (officially known as Jupiler Pro League ) is the top league competition for association football clubs in Belgium. Contested by 16 clubs, it operates on a system of promotion and relegation with the Belgian Second Division",
                "https://www.thesportsdb.com/images/media/league/badge/8y3jti1564838854.png",
                "Belgium",
                "https://www.thesportsdb.com/images/media/league/trophy/tvuvwy1422267731.png"
            ),Leagues(
                "4404",
                "Brazilian Brasileirao Serie B",
                null,
                null,
                "Soccer",
                "The Campeonato Brasileiro Série B, commonly referred to as the Brasileirão Série B or simply Série B is the second tier of the Brazilian football league system.",
                "https://www.thesportsdb.com/images/media/league/badge/0206v41534575321.png",
                "Brazil",
                "https://www.thesportsdb.com/images/media/league/trophy/uxxrxw1422277703.png"
            )
        )
    }

    fun getDummyListMatch() : List<Match> {
        return listOf(
            Match(
                "611682",
                "Racing Club",
                "Godoy Cruz",
                "2019-09-01",
                "3",
                "1",
                "Argentinian Primera Division",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                "135170",
                "135162",
                "Soccer"
            ),
            Match(
                "611466",
                "Godoy Cruz",
                "Estudiantes de la Plata",
                "2019-08-25",
                "3",
                "3",
                "Argentinian Primera Division",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                "135162",
                "135160",
                "Soccer"
            ),
            Match(
                "611474",
                "River Plate",
                "Talleres de Cordoba",
                "2019-08-25",
                "0",
                "1",
                "Argentinian Primera Division",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                "135171",
                "136674",
                "Soccer"
            ),
            Match(
                "611366",
                "Atletico Tucuman",
                "Godoy Cruz",
                "2019-08-19",
                "1",
                "1",
                "Argentinian Primera Division",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                "135681",
                "135162",
                "Soccer"
            ),
            Match(
                "611367",
                "Colon",
                "Gimnasia LP",
                "2019-08-19",
                "1",
                "2",
                "Argentinian Primera Division",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                "135157",
                "135161",
                "Soccer"
            )
        )
    }

    fun getDummyListTeam() : List<Team> {
        return listOf(
            Team(
                "135170",
                "https://www.thesportsdb.com/images/media/team/badge/jixh121504720274.png"
            ),
            Team(
                "135162",
                "https://www.thesportsdb.com/images/media/team/badge/d3c0ds1517768584.png"
            )
        )
    }

}