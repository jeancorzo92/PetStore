package com.app.petstore.models

import android.os.Parcel
import android.os.Parcelable

data class Pet(val id: String,
               val category: Category,
               val name: String,
               val photoUrls: List<String>,
               val tags: List<Tag>,
               val status: String): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readParcelable(Category::class.java.classLoader),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.createTypedArrayList(Tag),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(category, flags)
        parcel.writeString(name)
        parcel.writeStringList(photoUrls)
        parcel.writeTypedList(tags)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pet> {
        override fun createFromParcel(parcel: Parcel): Pet {
            return Pet(parcel)
        }

        override fun newArray(size: Int): Array<Pet?> {
            return arrayOfNulls(size)
        }
    }
}