package com.example.navigation.presentation

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.common_ipl.ActivityRequired
import com.example.navigation.DestinationsProvider
import com.example.navigation.R
import com.example.navigation.databinding.ActivityMainBinding
import com.example.navigation.presentation.navigation.NavComponentRouter
import com.example.navigation.presentation.navigation.RouterHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RouterHolder {


    @Inject
    lateinit var navComponentRouterFactory: NavComponentRouter.Factory

    @Inject
    lateinit var destinationsProvider: DestinationsProvider

    @Inject
    lateinit var activityRequiredSet: Set<@JvmSuppressWildcards ActivityRequired>

    private val viewModel by viewModels<MainViewModel>()

    private val binding by lazy(LazyThreadSafetyMode.NONE){
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navComponentRouter by lazy(LazyThreadSafetyMode.NONE){
        navComponentRouterFactory.create(R.id.fragmentContainer)
    }


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        if(savedInstanceState != null){
            navComponentRouter.onRestoreInstanceState(savedInstanceState)
        }
        navComponentRouter.addDestinationListener {
            updateCartButtonVisibility()
        }
        navComponentRouter.onCreate()
        if(savedInstanceState == null){
            navComponentRouter.switchToStack(destinationsProvider.provideStartDestinationId())
        }
        with(binding){
            observeUsername()
            observeCart()
            setupListeners()
        }
        activityRequiredSet.forEach{
            it.onCreated(this)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return (navComponentRouter.onNavigateUp()) || super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        navComponentRouter.onSavedInstantState(outState)
    }

    override fun onStart() {
        super.onStart()
        activityRequiredSet.forEach{it.onStarted()}
    }

    override fun onStop() {
        super.onStop()
        activityRequiredSet.forEach{it.onStopped()}
    }

    override fun onDestroy() {
        super.onDestroy()
        navComponentRouter.onDestroy()
        activityRequiredSet.forEach{it.onDestroyed()}
    }



    override fun requireRouter(): NavComponentRouter {
        return navComponentRouter
    }


    private fun ActivityMainBinding.observeUsername(){
        viewModel.usernameLiveValue.observe(this@MainActivity){username ->
            usernameTextView.isVisible = username != null
            if (username != null){
                usernameTextView.text = "@$username"
            }
        }
    }

    private fun ActivityMainBinding.observeCart(){
        viewModel.cartLiveValue.observe(this@MainActivity){cartState->
            updateCartButtonVisibility()
            cartCounterTextView.text = cartState.itemsCountDisplayString
        }
    }

    private fun ActivityMainBinding.setupListeners(){
        cartImageView.setOnClickListener {
            viewModel.launchCart()
        }
    }


    private fun updateCartButtonVisibility(){
        val showCarton = viewModel.cartLiveValue.getValue()?.showCartIcon ?: return
        val isAlreadyAtCart = navComponentRouter.hasDestination(destinationsProvider.providerCartDestinationId())
        binding.cartIconContainer.isVisible = showCarton && !isAlreadyAtCart
    }
}