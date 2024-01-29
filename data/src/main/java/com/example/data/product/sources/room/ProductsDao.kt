package com.example.data.product.sources.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.product.sources.room.entity.ProductDataTuple
import com.example.data.product.sources.room.entity.ProductDiscountTuple
import com.example.data.product.sources.room.entity.ProductUpdateQuantityTuple
import com.example.data.product.sources.room.entity.ProductsRoomEntity

@Dao
interface ProductsDao {

    @Insert(entity = ProductsRoomEntity::class)
    suspend fun createProduct(product: ProductsRoomEntity)



    @Query("SELECT * FROM products")
    suspend fun getAllData(): List<ProductDataTuple>


    @Query("SELECT EXISTS(SELECT 1 FROM products)")
    suspend fun hasTable(): Boolean


   @Query("SELECT * FROM products WHERE productId = :id")
    suspend fun getDataByID(id: Long): ProductDataTuple

   @Query("SELECT priceUsdCentsWithDiscount FROM products WHERE productId = :id")
    suspend fun getPriceWithDiscountByID(id: Long): Int




    @Update(entity = ProductsRoomEntity::class)
    suspend fun updateQuantity(productUpdateQuantityTuple: ProductUpdateQuantityTuple)

    @Delete(entity = ProductsRoomEntity::class)
    suspend fun deleteProductByID(productUpdateQuantityTuple: ProductUpdateQuantityTuple)

    @Query("SELECT productId,priceUsdCents, discountPercentage, priceUsdCentsWithDiscount FROM products")
    suspend fun getAllDiscountsProducts(): List<ProductDiscountTuple>

    @Update(entity = ProductsRoomEntity::class)
    suspend fun initDiscount(discount: ProductDiscountTuple)


    @Query("SELECT productId, priceUsdCents, discountPercentage,priceUsdCentsWithDiscount  FROM products WHERE productId = :productId")
    suspend fun getDiscountPercentageById(productId: Long): ProductDiscountTuple


    @Query("UPDATE products SET priceUsdCentsWithDiscount = (1 - discountPercentage/100) * priceUsdCents WHERE priceUsdCentsWithDiscount")
    suspend fun initDiscountForPrice()




}