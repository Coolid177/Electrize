package com.example.electrize.dataStructures

class Account(accountId: Int, firstName: String, lastName: String, email: String, phoneNumber: String, addressId: Address, imageId: Int){
    var _accountId: Int = accountId;
    var _firstName: String = firstName;
    var _lastName: String = lastName;
    var _email: String = email;
    var _phoneNumber: String = phoneNumber;
    var _addressId: Address = addressId;
    var _imageId: Int = imageId;
}