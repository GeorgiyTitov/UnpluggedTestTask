package com.gt.domain.product

data class ProductData(
    val color: String?,
    val capacity: String?,
    val capacityGb: Int?,
    val price: Double?,
    val generation: String?,
    val year: Int?,
    val cpuModel: String?,
    val hardDiskSize: String?,
    val strapColour: String?,
    val caseSize: String?,
    val beatsColor: String?,
    val description: String?,
    val iPadCapacity: String?,
    val screenSize: Double?,
    val iPadGeneration: String?,
){
    override fun toString(): String = """
        color:           $color
        capacity:        $capacity
        capacityGb:      $capacityGb
        price:           $price
        generation:      $generation
        year:            $year
        cpuModel:        $cpuModel
        hardDiskSize:    $hardDiskSize
        strapColour:     $strapColour
        caseSize:        $caseSize
        beatsColor:      $beatsColor
        description:     $description
        iPadCapacity:    $iPadCapacity
        screenSize:      $screenSize
        iPadGeneration:  $iPadGeneration
    """.trimIndent()
}
