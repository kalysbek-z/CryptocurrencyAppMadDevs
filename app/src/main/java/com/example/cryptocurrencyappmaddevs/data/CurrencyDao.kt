package com.example.cryptocurrencyappmaddevs.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencies")
    fun getAllCurrencies(): Flow<List<CurrencyItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<CurrencyItem>)

    @Query("DELETE FROM currencies")
    suspend fun deleteAllData()
}