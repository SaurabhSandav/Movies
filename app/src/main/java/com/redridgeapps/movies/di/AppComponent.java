package com.redridgeapps.movies.di;

import android.app.Application;

import com.redridgeapps.movies.App;
import com.redridgeapps.movies.di.modules.ActivityBuilder;
import com.redridgeapps.movies.di.modules.AppModule;
import com.redridgeapps.movies.di.modules.viewmodel.ViewModelFactoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilder.class,
                AppModule.class,
                ViewModelFactoryModule.class
        }
)
interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
