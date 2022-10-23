package com.example.socialnetwork.util

import com.google.gson.JsonArray
import com.google.gson.JsonObject

class Associations {

    companion object {

        const val ASSOCIATION_TYPE_USER = "user"
        const val ASSOCIATION_TYPE_PHOTOS = "photos"

        fun generate(vararg args: String): JsonObject {
            val jsonObject = JsonObject()
            val includes = JsonArray()

            args.forEach { parameter ->
                val objeto = JsonObject()
                objeto.addProperty("association", parameter)
                includes.add(objeto)
            }

            jsonObject.add("include", includes)
            return jsonObject
        }
    }

}