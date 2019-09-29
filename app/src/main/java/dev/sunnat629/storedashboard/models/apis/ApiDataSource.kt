package dev.sunnat629.storedashboard.models.apis

import dev.sunnat629.storedashboard.models.entities.Books
import dev.sunnat629.storedashboard.models.entities.Users
import dev.sunnat629.storedashboard.models.networks.NetworkResult

interface ApiDataSource {

    suspend fun getBooks(): NetworkResult<List<Books>>

    suspend fun getUser(): NetworkResult<Users>
}