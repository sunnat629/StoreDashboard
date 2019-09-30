package dev.sunnat629.storedashboard.viewmodels

import androidx.lifecycle.ViewModel
import dev.sunnat629.storedashboard.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel


abstract class AppViewModel : ViewModel() {

     protected val scope = CoroutineScope(Job() + Dispatchers.Main +Dispatchers.IO)

     val notification by lazy { SingleLiveEvent<Int>() }

     override fun onCleared() {
          scope.cancel()
          super.onCleared()
     }
}