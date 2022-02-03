package com.wizeline.heroes.dil

import com.wizeline.heroes.*
import com.wizeline.heroes.Network.HeroesServices
import com.wizeline.heroes.Network.HeroesServicesCorrutina
import com.wizeline.heroes.Network.NetworkClient
import com.wizeline.heroes.Repository.Repository
import com.wizeline.heroes.Repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

@Module
@InstallIn(SingletonComponent::class)
object HeroesModule {

    @Provides
    fun getServicesRX(): HeroesServices {
        return NetworkClient().getClientJavaRX()
    }
    @Provides
    fun getServicesCorrutina(): HeroesServicesCorrutina {
        return NetworkClient().getClientCorrutinas()
    }
    @Provides
    fun getUseCaseComic(repository: Repository): GetComicsUsesCase {
        return GetComicsUsesCaseImp(repository)
    }
    @Provides
    fun getRepository(heroesService: HeroesServices, heroesC:HeroesServicesCorrutina): Repository{
        return RepositoryImpl(heroesService, heroesC)
    }

    @Provides
    fun getUsesCaseSeries(repository: Repository): GetSeriesUsesCase {
        return GetSeriesUsesCaseImp(repository)
    }

    @Provides
    fun getUsesCaseHeroes(repository: Repository): GetHeroesUsesCase {
        return GetHeroesUsesCaseImp(repository)
    }

    @Provides
    fun getUsesCaseNameStart(repository: Repository): GetnameStartsWithUsesCase {
        return  GetnameStartsWithImp(repository)
    }

}


