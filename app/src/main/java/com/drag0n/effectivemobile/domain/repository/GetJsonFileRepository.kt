package com.drag0n.effectivemobile.domain.repository

import android.content.Context
import com.drag0n.effectivemobile.model.JsonFileOffers

interface GetJsonFileRepository {

    fun readJsonFromAssets(): String
    fun parseJsonToOffer(): JsonFileOffers
}