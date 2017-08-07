package com.app.petstore.models

import android.os.Parcel
import android.os.Parcelable

data class Tag(val id: Long,
               val name: String) : Parcelable {

    override fun toString(): String {
        return name
    }

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tag> {
        override fun createFromParcel(parcel: Parcel): Tag {
            return Tag(parcel)
        }

        override fun newArray(size: Int): Array<Tag?> {
            return arrayOfNulls(size)
        }
    }
}