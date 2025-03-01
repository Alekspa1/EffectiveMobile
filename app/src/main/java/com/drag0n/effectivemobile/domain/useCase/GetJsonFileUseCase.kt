package com.drag0n.effectivemobile.domain.useCase

import android.content.Context
import com.drag0n.effectivemobile.domain.repository.GetJsonFileRepository
import com.drag0n.effectivemobile.model.JsonFileOffers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetJsonFileUseCase @Inject constructor (private val getJson: GetJsonFileRepository){

    fun exum(): JsonFileOffers{
        return getJson.parseJsonToOffer()

    }
}