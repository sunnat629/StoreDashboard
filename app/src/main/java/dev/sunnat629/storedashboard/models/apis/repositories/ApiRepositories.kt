package dev.sunnat629.storedashboard.models.apis.repositories

import dev.sunnat629.storedashboard.models.apis.ApiDataSource
import dev.sunnat629.storedashboard.models.apis.services.ApiServices
import dev.sunnat629.storedashboard.models.entities.Books
import dev.sunnat629.storedashboard.models.entities.Users
import dev.sunnat629.storedashboard.models.networks.NetworkResult
import javax.inject.Inject

/**
 * ApiRepositories.kt
 * This repository class contains all the suspending functions regarding endpoints responses.
 *
 * @param apiServices is an injected {@linkplain ApiServices service}
 * */
class ApiRepositories @Inject constructor(private val apiServices: ApiServices) :
    BaseRepository(),
    ApiDataSource {

    /**
     * This suspending function returns the json which contains the book data
     * */
    override suspend fun getBooks(): NetworkResult<List<Books>> {
        return safeApiCall(call = { apiServices.getBooks() })
    }

    /**
     * This suspending function returns the json which contains the user data
     * */
    override suspend fun getUser(): NetworkResult<Users> {
        return safeApiCall(call = { apiServices.getUser() })
    }
}