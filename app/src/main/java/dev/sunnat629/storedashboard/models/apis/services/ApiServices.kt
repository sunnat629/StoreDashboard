package dev.sunnat629.storedashboard.models.apis.services

import dev.sunnat629.storedashboard.models.entities.Books
import dev.sunnat629.storedashboard.models.entities.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiServices {

    /**
     * This suspending function returns the response of the {@code Books}
     * */
    @GET("/sunnat629/StoreDashboard/master/contents/booklist.json")
    @Headers("Content-Type: text/plain; charset=utf-8")
    suspend fun getBooks(): Response<List<Books>>

    /**
     * This suspending function returns the response of the {@code Users}
     * */
    @GET("/sunnat629/StoreDashboard/master/contents/user.json")
    @Headers("Content-Type: application/json")
    suspend fun getUser(): Response<Users>
}