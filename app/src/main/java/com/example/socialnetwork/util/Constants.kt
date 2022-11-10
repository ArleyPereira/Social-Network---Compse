package com.example.socialnetwork.util

import android.Manifest

class Constants {

    object Permission {
        const val GALLERY_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE
        const val WRITE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE
        const val CAMERA_PERMISSION = Manifest.permission.CAMERA
    }

    object DataStore {
        const val USER_PREFERENCES = "USER_PREFERENCES"
    }

    object Actions {
        const val ACTION_NOT_FOUND = "not_found"
        const val ACTION_INVALID_DATA = "invalid_data"
        const val ACTION_NOT_CONFIRMED = "not_confirmed"
        const val ACTION_REQUIRED_FIELDS = "required_fields"
        const val ACTION_UNSUCCESSFULLY = "unsuccessfully"
    }

    object TypeUpdate {
        const val UPDATE_EMAIL = "email"
        const val UPDATE_PHONE = "phone"
    }

}