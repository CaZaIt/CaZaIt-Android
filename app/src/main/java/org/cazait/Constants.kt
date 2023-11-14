package org.cazait

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

const val baseURL = "https://cazait.shop"
const val TOKEN_DATASTORE = "token_datastore"
const val LOGIN_CHECK_DATASTORE = "login_check_datastore"
const val USER_DATASTORE = "user_datastore"
const val CAFE_ITEM_KEY = "CAFE_ITEM_KEY"
const val REQUEST_LOCATION_PERMISSION = 100

val EMAIL = stringPreferencesKey("email")
val USER_ID = longPreferencesKey("user_id")
val ACCESS_TOKEN = stringPreferencesKey("access_token")
val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
val LOGIN_CHECK = booleanPreferencesKey("login_check")
