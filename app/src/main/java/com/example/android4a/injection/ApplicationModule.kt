package com.example.android4a.injection

import android.content.Context
import androidx.room.Room
import com.example.android4a.data.local.AppDatabase
import com.example.android4a.data.local.DatabaseDao
import com.example.android4a.data.repository.UserRepository
import com.example.android4a.domain.usecase.CreateUserUseCase
import com.example.android4a.domain.usecase.GetUserUseCase
import com.example.android4a.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import com.example.android4a.presentation.main.CreateViewModel
import com.example.android4a.presentation.main.ViewModel2
import org.koin.core.module.Module
import org.koin.dsl.module

val presentationModule : Module = module {
    factory { MainViewModel(get()) }
    factory { CreateViewModel(get()) }
    factory { ViewModel2(get()) }
    }


val domainModule :Module = module {

    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get()) }

}

val dataModule : Module = module {
    single {UserRepository(get())}
    single{ createDataBase(androidContext())}
    }


fun createDataBase(context: Context): DatabaseDao{
        val appDatabase : AppDatabase  = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
        return appDatabase.databaseDao()
    }





