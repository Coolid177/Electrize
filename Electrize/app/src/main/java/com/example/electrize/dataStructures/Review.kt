package com.example.electrize.dataStructures

import java.util.Date

class Review(reviewTitle: String, reviewContent: String, reviewStore: String, reviewWriterId: Int, reviewRating: Float, reviewDate: Date){
    var _reviewTitle: String = reviewTitle;
    var _reviewContent: String = reviewContent;
    var _reviewStore: String = reviewStore;
    var _reviewWriter: Int = reviewWriterId;
    var _reviewRating: Float = reviewRating;
    var _reviewDate: Date = reviewDate;
}