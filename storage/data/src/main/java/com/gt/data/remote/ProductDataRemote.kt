package com.gt.data.remote

import com.google.gson.annotations.SerializedName

data class ProductDataRemote(
    @SerializedName("color")
    val color: String?,
    @SerializedName("capacity")
    val capacity: String?,
    @SerializedName("capacity GB")
    val capacityGb: Int?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("generation")
    val generation: String?,
    @SerializedName("year")
    val year: Int?,
    @SerializedName("CPU model")
    val cpuModel: String?,
    @SerializedName("Hard disk size")
    val hardDiskSize: String?,
    @SerializedName("Strap Colour")
    val strapColour: String?,
    @SerializedName("Case Size")
    val caseSize: String?,
    @SerializedName("Color")
    val beatsColor: String?,
    @SerializedName("Description")
    val description: String?,
    @SerializedName("Capacity")
    val iPadCapacity: String?,
    @SerializedName("Screen size")
    val screenSize: Double?,
    @SerializedName("Generation")
    val iPadGeneration: String?
)
