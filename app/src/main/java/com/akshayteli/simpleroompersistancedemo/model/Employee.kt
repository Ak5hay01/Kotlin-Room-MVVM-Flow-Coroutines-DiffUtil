package com.akshayteli.simpleroompersistancedemo.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * Created by Akshay Teli on 13,May,2021
 */


@Entity(tableName = "employee")
data class Employee(@PrimaryKey @ColumnInfo(name = "name") var name: String, var address:String, var number:String ): Serializable
// Class extends Serializable as we need to send object using intents