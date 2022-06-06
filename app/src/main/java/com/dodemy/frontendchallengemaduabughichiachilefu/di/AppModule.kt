package com.dodemy.frontendchallengemaduabughichiachilefu.di

import com.google.gson.Gson
import com.dodemy.frontendchallengemaduabughichiachilefu.data.HolidayService
import com.dodemy.frontendchallengemaduabughichiachilefu.repository.HolidayRepository
import com.dodemy.frontendchallengemaduabughichiachilefu.repository.HolidayRepositoryImpl
import com.dodemy.frontendchallengemaduabughichiachilefu.util.API_TOKEN
import com.dodemy.frontendchallengemaduabughichiachilefu.util.BASE_URL
import com.dodemy.frontendchallengemaduabughichiachilefu.util.CONNECTION_TIMEOUT
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<Gson> { Gson().newBuilder().setLenient().create() }

    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single {
        OkHttpClient()
            .newBuilder()
            .callTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor = get<HttpLoggingInterceptor>())
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .addHeader(name = "Authorization", value = "Bearer $API_TOKEN")
                        .build()
                )
            }
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
            .create(HolidayService::class.java)
    }

    factory<HolidayRepository> { HolidayRepositoryImpl(api = get(), dispatcher = get()) }

    single { Dispatchers.IO }
}
