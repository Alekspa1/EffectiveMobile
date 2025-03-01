package com.drag0n.effectivemobile.domain

import android.app.Application
import androidx.room.Room
import com.drag0n.effectivemobile.data.repository.GetJsonFileImp
import com.drag0n.effectivemobile.data.room.DataBase
import com.drag0n.effectivemobile.domain.repository.GetJsonFileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Di {

    @Provides
    @Singleton
    fun providesGetJsonFile(context: Application): GetJsonFileRepository {
        return GetJsonFileImp(context)
    }

    @Provides
    @Singleton
    fun provadeDB(context: Application): DataBase {
        return Room.databaseBuilder(
            context,
            DataBase::class.java, "db"
        ).build()
    }
}