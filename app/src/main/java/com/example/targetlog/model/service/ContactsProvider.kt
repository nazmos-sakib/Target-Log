package com.example.targetlog.model.service

import android.provider.ContactsContract
import com.example.targetlog.model.ContactDetails

interface ContactsProvider {
    /**
     * Fetch all the contacts
     */
    suspend fun getContacts(): ContactsContract.Contacts

    /**
     * Read the details of a contact
     *
     * @param contactId the contact id
     */
    suspend fun getContactDetails(contactId: String): List<ContactDetails>

}