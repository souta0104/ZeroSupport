package com.risakokato.zerosupport

import android.app.Application
import androidx.room.Room
import com.risakokato.zerosupport.main.MainContract
import com.risakokato.zerosupport.main.MainFragment
import com.risakokato.zerosupport.main.MainPresenter
import com.risakokato.zerosupport.model.BelongingsRepository
import com.risakokato.zerosupport.model.dao.Database
import io.realm.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {


    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        startKoin {
            androidContext(applicationContext)
            modules(module)
        }
    }

    private val module = module {
        single {
            Room.databaseBuilder(
                    androidContext(),
                    Database::class.java,
                    "zero_support.db"
            ).build().belongingsDao()
        }
        single { BelongingsRepository(get(), CoroutineScope(Dispatchers.Default)) }
        factory { (fragment: MainContract.View, scope: CoroutineScope) -> MainPresenter(fragment, get(), scope) }
        factory { MainFragment() }
    }

}