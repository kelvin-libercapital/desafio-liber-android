package br.com.libercapital.deathbattle.data

import br.com.libercapital.deathbattle.BuildConfig
import br.com.libercapital.deathbattle.data.remote.datasource.SuperHeroDataSource
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WebServiceClient {

    var webService: SuperHeroDataSource

    init {
        webService = createSuperHeroWebService(
            BuildConfig.WS_BASE_URL.plus(BuildConfig.WS_API_KEY + "/")
        )
    }

    private fun createSuperHeroWebService(
        uri: String,
        gson: Gson = Gson(),
        timeout: Long = 300L
    ) = createRetrofitAccess(uri, timeout, Interceptor { chain ->
        var request = chain.request()
        val originalHttpUrl = request.url
        val url = originalHttpUrl.newBuilder().build()
        val builder = request.newBuilder()

        builder.addHeader("Content-Type", "application/json")
        builder.url(url)

        request = builder.build()
        chain.proceed(request)
    }, gson).create(SuperHeroDataSource::class.java)

    private fun createRetrofitAccess(
        uri: String,
        timeout: Long,
        requestInterceptor: Interceptor,
        gson: Gson = Gson()
    ) = Retrofit.Builder().baseUrl(uri).client(
        setupInterceptors(requestInterceptor, timeout).build()
    ).addConverterFactory(
        GsonConverterFactory.create(gson)
    ).build()

    private fun setupInterceptors(
        requestInterceptor: Interceptor,
        timeout: Long
    ) = OkHttpClient.Builder().apply {

        val loggingInterceptor = HttpLoggingInterceptor()

        loggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE

        addInterceptor(loggingInterceptor)
        addInterceptor(requestInterceptor)
        connectTimeout(timeout, TimeUnit.SECONDS)
        readTimeout(timeout, TimeUnit.SECONDS)
    }

}