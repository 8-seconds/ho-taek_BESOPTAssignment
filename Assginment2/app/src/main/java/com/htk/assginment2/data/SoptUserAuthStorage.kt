package com.htk.assginment2.data

import android.content.Context
import android.content.SharedPreferences

object SoptUserAuthStorage {
    private const val STORAGE_KEY = "user_auth"
    private const val USER_ID = "Id"
    private const val USER_PW = "password"
    private lateinit var sharedPreference : SharedPreferences

    fun initSharedPreference(context : Context) {
            sharedPreference = context.getSharedPreferences(
                "$(context.packageName).$STORAGE_KEY",
                Context.MODE_PRIVATE)
    }

    fun saveUserId(id: String){
        sharedPreference.edit()
            .putString(USER_ID, id)
            .apply()
    }

    fun saveUserPW(pw: String){
        sharedPreference.edit()
            .putString(USER_PW, pw)
            .apply()
    }

    fun getUserId(): String{
        return sharedPreference.getString(USER_ID,"") ?: ""
    }

    fun getUserPw(): String{
        return sharedPreference.getString(USER_PW,"") ?: ""
    }

    fun hasUserData() : Boolean {
        return !sharedPreference.getString(USER_ID,"").isNullOrBlank()
                && !sharedPreference.getString(USER_PW,"").isNullOrBlank()
    }

    fun clearUserData() {
        sharedPreference.edit()
            .clear()
            .apply()
    }
}