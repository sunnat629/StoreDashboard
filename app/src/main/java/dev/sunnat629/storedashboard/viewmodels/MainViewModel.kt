package dev.sunnat629.storedashboard.viewmodels

import androidx.lifecycle.LiveData
import dev.sunnat629.storedashboard.models.apis.ApiDataSource
import dev.sunnat629.storedashboard.models.entities.Books
import dev.sunnat629.storedashboard.models.entities.Users
import dev.sunnat629.storedashboard.models.networks.NetworkResult
import kotlinx.coroutines.launch
import lt.ito.devicetracker.NonNullMediatorLiveData
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val apiDataSource: ApiDataSource
) : AppViewModel() {

    private var _allBooks = NonNullMediatorLiveData<List<Books>>()
    val allBooks: LiveData<List<Books>>
        get() = _allBooks

    private var _userDetails = NonNullMediatorLiveData<Users>()
    val userDetails: LiveData<Users>
        get() = _userDetails

    private var _errorMessage = NonNullMediatorLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    init {
        getAllBooks()
        getUserDetails()
    }

    private fun getAllBooks(){
        scope.launch {
            when (val result = apiDataSource.getBooks()) {
                is NetworkResult.Success -> _allBooks.postValue(result.data)
                is NetworkResult.Error -> _errorMessage.postValue(result.exception)
            }
        }
    }

    private fun getUserDetails(){
        scope.launch {
            when (val result = apiDataSource.getUser()) {
                is NetworkResult.Success -> _userDetails.postValue(result.data)
                is NetworkResult.Error -> _errorMessage.postValue(result.exception)
            }
        }
    }
}