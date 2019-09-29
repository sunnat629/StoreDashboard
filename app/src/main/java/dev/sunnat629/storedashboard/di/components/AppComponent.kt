package dev.sunnat629.storedashboard.di.components

import dev.sunnat629.storedashboard.ui.activities.MainActivity

/**
 * AppComponent.kt
 * This interface will contains all the inject functions are for activities and viewModels
 * */
interface AppComponent {

    fun inject(activity: MainActivity)
}