package com.example.data.cart.sources.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.cart.sources.room.entites.CartDataTuples
import com.example.data.cart.sources.room.entites.CartItemIdTuples
import com.example.data.cart.sources.room.entites.CartRoomEntity

@Dao
interface CartDao {

    @Insert(entity = CartRoomEntity::class)
    suspend fun createProduct(item: CartDataTuples)



    @Update(entity = CartRoomEntity::class)
    suspend fun updateItem(item: CartDataTuples)



    @Query("SELECT * FROM cart")
    suspend fun getAllCartData(): List<CartDataTuples>


    @Query("SELECT EXISTS(SELECT 1 FROM cart)")
    suspend fun hasTable(): Boolean



    @Query ("DELETE FROM cart")
    suspend fun deleteAll()


    @Delete(entity = CartRoomEntity::class)
    suspend fun deleteProductByID(productId: CartItemIdTuples)
}