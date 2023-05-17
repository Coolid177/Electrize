package com.example.electrize.dataStructures

class Address(addressLine1: String, addressLine2: String = "", city: String, postalCode: String){
    //Address
    var _addressLine1: String = addressLine1;
    //Bus number
    var _addressLine2: String = addressLine2;
    var _city: String = city;
    var _postalCode: String = postalCode;
}