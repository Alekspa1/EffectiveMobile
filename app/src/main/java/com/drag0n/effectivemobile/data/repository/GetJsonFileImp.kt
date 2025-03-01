package com.drag0n.effectivemobile.data.repository

import android.content.Context
import com.drag0n.effectivemobile.domain.repository.GetJsonFileRepository
import com.drag0n.effectivemobile.model.JsonFileOffers
import com.google.gson.Gson
import java.io.InputStream



class GetJsonFileImp (private val context: Context): GetJsonFileRepository {
    override fun readJsonFromAssets(): String {
        val inputStream: InputStream = context.assets.open("offers.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charsets.UTF_8)
    }

    override fun parseJsonToOffer(): JsonFileOffers {
        val jsonString = readJsonFromAssets()
        val gson = Gson()
        return gson.fromJson(jsonString, JsonFileOffers::class.java)
    }
}

