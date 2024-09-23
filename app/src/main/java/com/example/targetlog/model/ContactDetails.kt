package com.example.targetlog.model

sealed interface ContactDetails {
    val id: String
    val contactId: String
    val accountType: String?
    val dataType: DetailsType
    val displayName: String?

    enum class DetailsType {
        Name,
        Phone,
        Email,
        WebSite
    }

    // I have omitted (for readaility) the 4 data classes with specific properties
}