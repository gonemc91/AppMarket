package com.example.data.accounts

import com.example.common.AuthException
import com.example.common.Container
import com.example.common.flow.LazyFlowSubjectFactory
import com.example.data.AccountsDataRepository
import com.example.data.accounts.entities.AccountDataEntity
import com.example.data.accounts.entities.SignUpDataEntity
import com.example.data.accounts.sources.AccountsDataSource
import com.example.data.settings.SettingDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RealAccountsDataRepository @Inject constructor(
    private val accountsDataSource: AccountsDataSource,
    private val settingsDataSource: SettingDataSource,
    coroutineScope: CoroutineScope,
    lazyFlowSubjectFactory: LazyFlowSubjectFactory
) : AccountsDataRepository {

    private val accountLazyFlowSubject = lazyFlowSubjectFactory.create {
        accountsDataSource.getAccount()
    }

    init {
        coroutineScope.launch {
            settingsDataSource.listenToken().collect{
                if(it != null){
                    accountLazyFlowSubject.newAsyncLoad(silently = true)
                }else{
                    accountLazyFlowSubject.updatedWith(Container.Error(AuthException()))
                }
            }
        }

    }

    override fun getAccount(): Flow<Container<AccountDataEntity>> {
        return  accountLazyFlowSubject.listen()
    }

    override suspend fun setAccountUsername(username: String) {
        val newAccount = accountsDataSource.setAccountUsername(username)
        accountLazyFlowSubject.updatedWith(Container.Success(newAccount))
    }

    override suspend fun signIn(email: String, password: String): String {
        return accountsDataSource.signIn(email, password)
    }

    override suspend fun signUp(singUpData: SignUpDataEntity) {
        accountsDataSource.signUp(singUpData)
    }

    override fun reload() {
        accountLazyFlowSubject.newAsyncLoad()
    }
}