
package com.example.appmarket.room.di

import android.content.Context
import androidx.room.Room
import com.example.appmarket.room.AppDataBase
import com.example.data.accounts.sources.room.AccountsDao
import com.example.data.cart.sources.room.CartDao
import com.example.data.orders.sources.room.OrdersDao
import com.example.data.orders.sources.room.OrdersItemsDao
import com.example.data.product.sources.room.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

class AccountRoomModule {


    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context,
    ): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            DB_NAME
        )
            .build()
    }


    @Provides
    fun providesAccountsDao (dataBase: AppDataBase): AccountsDao {
        return dataBase.getAccountDao()
    }


    @Provides
    fun providesProductsDao (dataBase: AppDataBase): ProductsDao {
        return dataBase.getProductDao()
    }


    @Provides
    fun providesCartDao (database: AppDataBase): CartDao{
        return database.getCartDao()
    }


    @Provides
    fun providesOrdersDao (database: AppDataBase) : OrdersDao {
        return database.getOrdersDao()
    }


    @Provides
    fun providesOrdersItemsDao (dataBase: AppDataBase): OrdersItemsDao{
        return dataBase.getOrdersItemsDao()
    }




    private companion object{
        const val DB_NAME = "database.db"
    }

}

