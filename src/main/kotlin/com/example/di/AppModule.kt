package com.example.di

import com.example.data.repository.ChannelRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.ChannelRepository
import com.example.domain.repository.UserRepository
import org.koin.dsl.module

val appModule = module {
    single<UserRepository> { UserRepositoryImpl() }
    single<ChannelRepository> { ChannelRepositoryImpl() }
}