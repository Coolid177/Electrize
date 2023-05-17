package com.example.project_groep_2

import java.util.Date

class RetrievalrequestData(profile: Profile, typeOfRequest: typeOfRequest, dateTime: Date) {
    private var m_profile: Profile = profile
    private var m_typeOfRequest: typeOfRequest = typeOfRequest
    private var m_sendTime: Date = dateTime

    fun getProfile() : Profile{
        return m_profile
    }

    fun getTypeOfRequest() : typeOfRequest{
        return m_typeOfRequest
    }

    fun getSendTime() : Date{
        return m_sendTime
    }

}