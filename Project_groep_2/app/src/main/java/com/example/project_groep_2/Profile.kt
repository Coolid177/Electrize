package com.example.project_groep_2

class Profile(firstName: String, lastName: String, city: String, houseNumber: String, postalCode: String, streetName: String, email: String, phoneNumber: String, image: String) {
    private var m_firstName: String = firstName
    private var m_lastName: String = lastName
    private var m_city: String = city
    private var m_postalCode: String = postalCode
    private var m_streetName: String = streetName
    private var m_houseNumber: String = houseNumber
    private var m_email: String = email
    private var m_phoneNumber: String = phoneNumber
    private var m_imageId: String = image //I.E.: R.drawable.Profile_picture_man
    private var m_darkTheme: Boolean = false
    private var m_language: String = "Nederlands"
    private var m_id: String = (100000000000..800000000000).random().toString()
    private var m_outgoingRequests: MutableList<Profile> = mutableListOf()
    private var m_incomingRequests: MutableList<Profile> = mutableListOf()
    private var m_friends: MutableList<Profile> = mutableListOf()

    fun addIncomingRequest(newRequest: Profile){
        m_incomingRequests.add(newRequest)
    }

    fun addOutgoingRequest(newRequest: Profile){
        m_outgoingRequests.add(newRequest)
    }

    fun addFriend(newFriend: Profile){
        m_friends.add(newFriend)
    }
    fun getFriends(): List<Profile>{
        return m_friends
    }

    fun getOutgoingRequests(): List<Profile>{
        return m_outgoingRequests
    }

    fun getIncomingRequests(): List<Profile>{
        return m_incomingRequests
    }

    fun getId() : String{
        return m_id
    }

    fun getHouseNumber(): String{
        return m_houseNumber
    }

    fun getDarkTheme(): Boolean{
        return m_darkTheme
    }

    fun getLanguage(): String{
        return m_language
    }

    fun getName() : String{
        return m_firstName
    }

    fun getLastName(): String {
        return m_lastName
    }

    fun getCity(): String {
        return m_city
    }

    fun getPostalCode(): String {
        return m_postalCode
    }

    fun getStreetName(): String{
        return m_streetName
    }

    fun getEmail(): String{
        return m_email
    }

    fun getPhoneNumber(): String{
        return m_phoneNumber
    }

    fun getImageId() : String{
        return m_imageId
    }
}