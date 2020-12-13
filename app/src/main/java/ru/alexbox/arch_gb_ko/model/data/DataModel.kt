package ru.alexbox.arch_gb_ko.model.data
import com.google.gson.annotations.SerializedName
class DataModel (
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)
