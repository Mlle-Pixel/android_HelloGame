package fr.epita.hellogame

import retrofit2.Call
import retrofit2.http.GET

interface WSInterface {


    @GET("game/list")
    fun getAllGames() : Call<List<Game>>

//    @GET("datails")
//    fun getDetails() :

}