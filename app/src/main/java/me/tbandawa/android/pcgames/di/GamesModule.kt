package me.tbandawa.android.pcgames.di

import kotlinx.coroutines.Dispatchers
import me.tbandawa.android.pcgames.data.api.GamesApi
import me.tbandawa.android.pcgames.data.repo.GamesRepo
import me.tbandawa.android.pcgames.ui.viewmodels.GamesViewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val apiModule = module {
    single { GamesApi() }
}

private val dispatchersModule = module {
    single(named("IODispatcher")) {
        Dispatchers.IO
    }
}

private val repoModule = module {
    single { GamesRepo(get(), get(named("IODispatcher"))) }
}

private val viewModelModule = module {
    single { GamesViewModel(get(), get(named("IODispatcher"))) }
}

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        apiModule,
        repoModule,
        viewModelModule,
        dispatchersModule
    )
}