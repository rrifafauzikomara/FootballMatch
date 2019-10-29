package com.rifafauzi.footballmatch.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by rrifafauzikomara on 2019-10-18.
 */

@Entity(tableName = "leagues")
data class Leagues(

    @PrimaryKey
    @ColumnInfo(name = "idLeague")
    @SerializedName("idLeague")
    val idLeague: String,

    @ColumnInfo(name = "strLeague")
    @SerializedName("strLeague")
    val strLeague: String,

    @ColumnInfo(name = "dateFirstEvent")
    @SerializedName("dateFirstEvent")
	val dateFirstEvent: String,

    @ColumnInfo(name = "strBanner")
    @SerializedName("strBanner")
	val strBanner: String,

    @ColumnInfo(name = "strSport")
    @SerializedName("strSport")
	val strSport: String,

    @ColumnInfo(name = "strDescriptionEN")
    @SerializedName("strDescriptionEN")
	val strDescriptionEN: String,

    @ColumnInfo(name = "strBadge")
    @SerializedName("strBadge")
	val strBadge: String,

    @ColumnInfo(name = "strCountry")
    @SerializedName("strCountry")
	val strCountry: String
)
