package com.wizeline.heroes.dl

import com.wizeline.heroes.data.useCaseImp.GetComicsUsesCaseImp
import com.wizeline.heroes.data.useCaseImp.GetHeroesUsesCaseImp
import com.wizeline.heroes.data.useCaseImp.GetSeriesUsesCaseImp
import com.wizeline.heroes.data.useCaseImp.GetNameStartsWithImp
import com.wizeline.heroes.data.usesCase.GetComicsUsesCase
import com.wizeline.heroes.data.usesCase.GetHeroesUsesCase
import com.wizeline.heroes.data.usesCase.GetSeriesUsesCase
import com.wizeline.heroes.data.usesCase.GetNameStartsWithUsesCase
import com.wizeline.heroes.network.HeroesServices
import com.wizeline.heroes.network.HeroesServicesCorrutina
import com.wizeline.heroes.network.NetworkClient
import com.wizeline.heroes.repository.Repository
import com.wizeline.heroes.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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
    fun getUsesCaseNameStart(repository: Repository): GetNameStartsWithUsesCase {
        return  GetNameStartsWithImp(repository)
    }

}


