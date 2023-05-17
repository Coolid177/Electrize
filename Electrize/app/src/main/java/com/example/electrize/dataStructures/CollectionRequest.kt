package com.example.electrize.dataStructures

import java.util.Date

class CollectionRequest(collectionRequestId: Int, routeId: Int, senderId: Int, receiverId: Int){
    var _collectionRequestId: Int = collectionRequestId;
    var _routeId: Int = routeId;
    var _senderId: Int = senderId;
    var _receiverId: Int = receiverId;
    var _sendDate: Date = Date()
}