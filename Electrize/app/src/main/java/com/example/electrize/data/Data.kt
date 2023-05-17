package com.example.electrize.data

import com.example.electrize.R
import com.example.electrize.dataStructures.Account
import com.example.electrize.dataStructures.Address
import com.example.electrize.dataStructures.BoughtProduct
import com.example.electrize.dataStructures.Brand
import com.example.electrize.dataStructures.Category
import com.example.electrize.dataStructures.CategoryGroup
import com.example.electrize.dataStructures.CollectionRequest
import com.example.electrize.dataStructures.Contact
import com.example.electrize.dataStructures.ContactRequest
import com.example.electrize.dataStructures.Product
import com.example.electrize.dataStructures.Review
import com.example.electrize.dataStructures.Route
import com.example.electrize.dataStructures.RouteType
import com.example.electrize.dataStructures.Store
import com.example.electrize.pages.contacts
import java.util.Date
import java.util.GregorianCalendar
import java.util.function.Predicate

object DataSource{
    var CurrentCollectionRequestId: Int = 50
    /* data */
    val Accounts = listOf<Account>(
        Account(
            accountId = 1,
            firstName = "John",
            lastName = "Smith",
            email = "johnsmith@gmail.com",
            phoneNumber = "+32 497 23 45 67",
            addressId = Address(
                addressLine1 = "Maastrichtersteenweg 2",
                addressLine2 = "",
                city = "Kuringen",
                postalCode = "3511"
            ),
            imageId = R.drawable.profile_picture_man
        ),
        Account(
            accountId = 2,
            firstName = "Amy",
            lastName = "Chen",
            email = "amychen@gmail.com",
            phoneNumber = "+32 478 34 56 78",
            addressId = Address(
                addressLine1 = "Kunstlaan 3",
                addressLine2 = "A",
                city = "Diepenbeek",
                postalCode = "3590"
            ),
            imageId = R.drawable.profile_picture_man
        ),
        Account(
            accountId = 3,
            firstName = "Michael",
            lastName = "Davis",
            email = "michaeldavis@gmail.com",
            phoneNumber = "+32 486 45 67 89",
            addressId = Address(
                addressLine1 = "Martelarenlaan 4",
                addressLine2 = "",
                city = "Hasselt",
                postalCode = "3500"
            ),
            imageId = R.drawable.profile_picture_man
        ),
        Account(
            accountId = 4 ,
            firstName = "Sophia",
            lastName = "Johnson",
            email = "sophiajohnson@gmail.com",
            phoneNumber = "+32 493 56 78 90",
            addressId = Address(
                addressLine1 = "Wijerstraat 5",
                addressLine2 = "",
                city = "Zonhoven",
                postalCode = "3520"
            ),
            imageId = R.drawable.profile_picture_man
        )
    )

    val Brands = listOf<Brand>(
        Brand(
            brandId = 0,
            brandName = "Logitech"
        ),
        Brand(
            brandId = 1,
            brandName = "Samsung"
        ),
        Brand(
            brandId = 2,
            brandName = "LG"
        ),
        Brand(
            brandId = 3,
            brandName = "Sony"
        ),
        Brand(
            brandId = 4,
            brandName = "Panasonic"
        )
    )

    val Products = listOf<Product>(
        Product(
            productId = 0,
            productName = "Panasonic NN-SN966S Microwave",
            rating = 4.5f,
            specList = listOf(
                Pair("Power Output", "1250 watts"),
                Pair("Capacity", "2.2 cubic feet"),
                Pair("Turntable Diameter", "16.5 inches"),
                Pair("Dimensions", "23.9 x 19.4 x 14 inches"),
                Pair("Weight", "35.3 pounds")
            ),
            pricingList = listOf(
                Pair(0, 219.99f),
                Pair(1, 249.99f),
                Pair(2, 229.99f)
            ),
            reviewList = mutableListOf(
                Review(
                    reviewTitle = "Great keyboard for gaming!",
                    reviewContent = "I bought the Logitech G Pro X Keyboard and it's been amazing for gaming. The tactile switches feel great and the RGB lighting is very customizable.",
                    reviewStore = "Amazon",
                    reviewWriterId = 0,
                    reviewRating = 4.5f,
                    reviewDate = Date(2023,5,6)
                ),
                Review(
                    reviewTitle = "Solid sound quality",
                    reviewContent = "I've been using the Samsung Galaxy Buds for a few weeks and they sound great. They're comfortable to wear and the battery life is pretty good too.",
                    reviewStore = "Best Buy",
                    reviewWriterId = 1,
                    reviewRating = 4.0f,
                    reviewDate = Date(2023,7,22)
                ),
                Review(
                    reviewTitle = "Excellent TV",
                    reviewContent = "The LG OLED CX is an excellent TV. The picture quality is amazing and the sound is good too. It's definitely worth the price.",
                    reviewStore = "Costco",
                    reviewWriterId = 2,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,9,15)
                ),
                Review(
                    reviewTitle = "Great camera for vlogging",
                    reviewContent = "The Sony ZV-1 is a great camera for vlogging. The autofocus is very fast and accurate, and the microphone is pretty good too. The only downside is that it's a bit expensive.",
                    reviewStore = "B&H",
                    reviewWriterId = 3,
                    reviewRating = 4.5f,
                    reviewDate = Date(2023,11,1)
                ),
                Review(
                    reviewTitle = "Excellent microwave",
                    reviewContent = "The Panasonic NN-SN966S is an excellent microwave. It heats up food quickly and evenly, and the interface is easy to use. The only thing I don't like is that the door feels a bit flimsy.",
                    reviewStore = "Walmart",
                    reviewWriterId = 4,
                    reviewRating = 4.0f,
                    reviewDate = Date(2023,12,25)
                )
            ),
            productBrandId = 0,
            productCategoryId = 0,
            productImages = listOf(R.drawable.mouse)
        ),
        Product(
            productId = 1,
            productName = "Fitbit Versa 3",
            rating = 4.2f,
            specList = listOf(
                Pair("Display", "1.58 inch AMOLED"),
                Pair("Battery Life", "6+ days"),
                Pair("GPS", "Built-in GPS"),
                Pair("Water Resistance", "Up to 50 meters"),
                Pair("Dimensions", "40 x 40 x 12 mm"),
                Pair("Weight", "30g")
            ),
            pricingList = listOf(
                Pair(3, 229.99f),
                Pair(4, 229.99f),
                Pair(5, 229.99f)
            ),
            reviewList = mutableListOf(
                Review(
                    reviewTitle = "Great fitness tracker",
                    reviewContent = "The Fitbit Versa 3 is a great fitness tracker. The built-in GPS is very accurate and the heart rate monitor works well too. The battery life is impressive and it's very comfortable to wear.",
                    reviewStore = "Amazon",
                    reviewWriterId = 0,
                    reviewRating = 4.5f,
                    reviewDate = Date(2023,5,6)
                ),
                Review(
                    reviewTitle = "Good smartwatch",
                    reviewContent = "The Fitbit Versa 3 is a good smartwatch. It has all the basic features you'd expect like notifications and music control. The app selection is a bit limited though.",
                    reviewStore = "Best Buy",
                    reviewWriterId = 1,
                    reviewRating = 4.0f,
                    reviewDate = Date(2023,7,22)
                ),
                Review(
                    reviewTitle = "Love it!",
                    reviewContent = "I absolutely love my Fitbit Versa 3. It has helped me stay on track with my fitness goals and the sleep tracking is very helpful too. The voice commands are a bit hit or miss though.",
                    reviewStore = "Target",
                    reviewWriterId = 2,
                    reviewRating = 4.5f,
                    reviewDate = Date(2023,9,15)
                )
            ),
            productBrandId = 1,
            productCategoryId = 2,
            productImages = listOf(R.drawable.mouse, R.drawable.mouse,R.drawable.mouse,R.drawable.mouse,R.drawable.mouse,R.drawable.mouse,R.drawable.mouse,R.drawable.mouse,R.drawable.mouse,R.drawable.mouse,R.drawable.mouse)
        ),
        Product(
            productId = 2,
            productName = "Bose QuietComfort 35 II",
            rating = 4.7f,
            specList = listOf(
                Pair("Noise Cancellation", "Active noise cancellation"),
                Pair("Connectivity", "Bluetooth 4.1, NFC"),
                Pair("Battery Life", "20 hours"),
                Pair("Voice Assistant", "Built-in Google Assistant and Amazon Alexa"),
                Pair("Weight", "235g")
            ),
            pricingList = listOf(
                Pair(7, 299.95f),
                Pair(5, 299.99f),
                Pair(4, 299.00f)
            ),
            reviewList = mutableListOf(
                Review(
                    reviewTitle = "Excellent noise cancellation",
                    reviewContent = "The Bose QuietComfort 35 II has excellent noise cancellation. It blocks out most ambient noise and makes it easy to focus on work or relax. The battery life is also great and lasts all day.",
                    reviewStore = "Bose",
                    reviewWriterId = 0,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,5,6)
                ),
                Review(
                    reviewTitle = "Great sound quality",
                    reviewContent = "The sound quality on the Bose QuietComfort 35 II is great. The bass is deep and the treble is clear. The noise cancellation is also very effective and helps me focus on my music.",
                    reviewStore = "Best Buy",
                    reviewWriterId = 1,
                    reviewRating = 4.5f,
                    reviewDate = Date(2023,7,22)
                ),
                Review(
                    reviewTitle = "Good wireless headphones",
                    reviewContent = "The Bose QuietComfort 35 II is a good pair of wireless headphones. The noise cancellation works well and the battery life is decent. The only downside is that they're a bit heavy.",
                    reviewStore = "Amazon",
                    reviewWriterId = 2,
                    reviewRating = 4.0f,
                    reviewDate = Date(2023,9,15)
                ),
                Review(
                    reviewTitle = "Highly recommend",
                    reviewContent = "I highly recommend the Bose QuietComfort 35 II. The noise cancellation is top-notch and the sound quality is excellent. The built-in voice assistant is also a nice touch.",
                    reviewStore = "Bose",
                    reviewWriterId = 3,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,11,1)
                ),
                Review(
                    reviewTitle = "Best noise cancelling headphones",
                    reviewContent = "The Bose QuietComfort 35 II is the best noise cancelling headphones I've used. The noise cancellation is very effective and the sound quality is great too. They're a bit pricey but worth the investment.",
                    reviewStore = "Best Buy",
                    reviewWriterId = 4,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,12,25)
                )
            ),
            productBrandId = 2,
            productCategoryId = 1,
            productImages = listOf(R.drawable.mouse)
        ),
        Product(
            productId = 3,
            productName = "Sony WH-1000XM4 Headphones",
            rating = 4.8f,
            specList = listOf(
                Pair("Noise Cancellation", "Yes"),
                Pair("Bluetooth", "Yes"),
                Pair("Battery Life", "30 hours"),
                Pair("Weight", "254 grams"),
                Pair("Voice Assistant", "Google Assistant, Alexa")
            ),
            pricingList = listOf(
                Pair(0, 349.99f),
                Pair(2, 349.99f),
                Pair(6, 349.99f)
            ),
            reviewList = mutableListOf(
                Review(
                    reviewTitle = "Best headphones ever!",
                    reviewContent = "The Sony WH-1000XM4 headphones are simply amazing. The noise cancellation is top-notch and the sound quality is superb. They're also very comfortable to wear for long periods of time.",
                    reviewStore = "Amazon",
                    reviewWriterId = 0,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023, 5, 6)
                ),
                Review(
                    reviewTitle = "Great for travel",
                    reviewContent = "I recently took a long flight and these headphones were a lifesaver. The noise cancellation really blocks out all the background noise, and the battery lasted the entire flight.",
                    reviewStore = "Best Buy",
                    reviewWriterId = 1,
                    reviewRating = 4.5f,
                    reviewDate = Date(2023, 7, 22)
                ),
                Review(
                    reviewTitle = "Awesome sound quality",
                    reviewContent = "I've been using the Sony WH-1000XM4 headphones for a few weeks and I'm blown away by the sound quality. The bass is punchy and the treble is clear, making for an overall great listening experience.",
                    reviewStore = "Sony",
                    reviewWriterId = 2,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023, 9, 15)
                ),
                Review(
                    reviewTitle = "Comfortable fit",
                    reviewContent = "These headphones are not only great sounding, but also very comfortable to wear. The ear cups are soft and the headband doesn't squeeze too tightly.",
                    reviewStore = "Amazon",
                    reviewWriterId = 3,
                    reviewRating = 4.8f,
                    reviewDate = Date(2023, 11, 1)
                ),
                Review(
                    reviewTitle = "Best investment ever!",
                    reviewContent = "I hesitated to spend so much on headphones, but these were worth every penny. The noise cancellation is so good that I can focus on work without being distracted, and the battery life lasts all day.",
                    reviewStore = "Best Buy",
                    reviewWriterId = 4,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023, 12, 25)
                )
            ),
            productBrandId = 1,
            productCategoryId = 1,
            productImages = listOf(R.drawable.mouse)
        ),
        Product(
            productId = 4,
            productName = "Bose QuietComfort 35 II Wireless Headphones",
            rating = 4.8f,
            specList = listOf(
                Pair("Noise Cancellation", "Active"),
                Pair("Connectivity", "Wireless Bluetooth"),
                Pair("Battery Life", "20 hours"),
                Pair("Weight", "8.3 ounces"),
                Pair("Colors", "Black, Silver")
            ),
            pricingList = listOf(
                Pair(0, 299.00f),
                Pair(2, 349.00f),
                Pair(1, 349.00f),
                Pair(3, 299.99f)
            ),
            reviewList = mutableListOf(
                Review(
                    reviewTitle = "Great noise cancelling headphones",
                    reviewContent = "The Bose QuietComfort 35 II headphones are amazing for noise cancelling. They make working in noisy environments so much easier. The sound quality is great too.",
                    reviewStore = "Amazon",
                    reviewWriterId = 0,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,5,6)
                ),
                Review(
                    reviewTitle = "Solid build and great sound",
                    reviewContent = "I've been using the Bose QuietComfort 35 II headphones for a few months now and they're great. The build quality is very solid and they sound amazing. The only downside is that they're a bit heavy.",
                    reviewStore = "Best Buy",
                    reviewWriterId = 1,
                    reviewRating = 4.5f,
                    reviewDate = Date(2023,7,22)
                ),
                Review(
                    reviewTitle = "Great headphones for travel",
                    reviewContent = "I bought the Bose QuietComfort 35 II headphones for a long flight and they were amazing. The noise cancelling is very effective and the battery life lasted the entire flight. They're also very comfortable to wear.",
                    reviewStore = "Target",
                    reviewWriterId = 2,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,9,15)
                ),
                Review(
                    reviewTitle = "Amazing headphones",
                    reviewContent = "The Bose QuietComfort 35 II headphones are amazing. The noise cancelling is the best I've ever experienced and the sound quality is top notch. They're also very comfortable to wear for long periods of time.",
                    reviewStore = "Bose",
                    reviewWriterId = 3,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,11,1)
                ),
                Review(
                    reviewTitle = "Best headphones I've ever owned",
                    reviewContent = "The Bose QuietComfort 35 II headphones are hands down the best headphones I've ever owned. The noise cancelling is incredible and the sound quality is amazing. They're also very comfortable to wear for extended periods of time.",
                    reviewStore = "Amazon",
                    reviewWriterId = 4,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,12,25)
                )
            ),
            productBrandId = 2,
            productCategoryId = 3,
            productImages = listOf(R.drawable.mouse)
        ),
        Product(
            productId = 5,
            productName = "Hasselt City Guide Book",
            rating = 4.8f,
            specList = listOf(
                Pair("Author", "Sarah Johnson"),
                Pair("Pages", "200"),
                Pair("Language", "English"),
                Pair("Publisher", "Lonely Planet"),
                Pair("Dimensions", "5 x 0.7 x 7.8 inches"),
                Pair("Weight", "10.4 ounces")
            ),
            pricingList = listOf(
                Pair(3, 19.99f),
                Pair(7, 16.99f),
                Pair(6, 18.99f)
            ),
            reviewList = mutableListOf(
                Review(
                    reviewTitle = "Great Guide Book",
                    reviewContent = "I recently traveled to Hasselt and this guide book was very helpful. It provided a lot of useful information about the city and helped me plan my trip. I highly recommend it!",
                    reviewStore = "Amazon",
                    reviewWriterId = 0,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,5,6)
                ),
                Review(
                    reviewTitle = "Very informative",
                    reviewContent = "I bought this guide book before my trip to Hasselt and it was very informative. It had a lot of useful information about the city's history, culture, and attractions. It was also well organized and easy to navigate.",
                    reviewStore = "Barnes & Noble",
                    reviewWriterId = 1,
                    reviewRating = 4.5f,
                    reviewDate = Date(2023,6,12)
                ),
                Review(
                    reviewTitle = "A must-have for travelers",
                    reviewContent = "If you're planning a trip to Hasselt, you need this guide book. It's packed with useful information and tips for exploring the city. The maps and recommendations were especially helpful. Highly recommended!",
                    reviewStore = "Lonely Planet",
                    reviewWriterId = 2,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,7,21)
                ),
                Review(
                    reviewTitle = "Helpful and informative",
                    reviewContent = "I bought this guide book for a friend who was traveling to Hasselt and they found it very helpful. It had a lot of great recommendations for things to do and see in the city, and the information about the history and culture was very interesting.",
                    reviewStore = "Amazon",
                    reviewWriterId = 3,
                    reviewRating = 4.0f,
                    reviewDate = Date(2023,9,5)
                ),
                Review(
                    reviewTitle = "Great resource for exploring Hasselt",
                    reviewContent = "This guide book was a great resource for exploring Hasselt. It had detailed information about the city's landmarks and attractions, as well as recommendations for restaurants and cafes. The maps and directions were also very helpful.",
                    reviewStore = "Barnes & Noble",
                    reviewWriterId = 4,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,10,15)
                )
            ),
            productBrandId = 1,
            productCategoryId = 3,
            productImages = listOf(R.drawable.mouse)
        ),
        Product(
            productId = 6,
            productName = "Sennheiser HD 660 S Headphones",
            rating = 4.8f,
            specList = listOf(
                Pair("Type", "Over-ear, open-back"),
                Pair("Frequency response", "10Hz - 41kHz"),
                Pair("Impedance", "150 ohms"),
                Pair("Sound pressure level (SPL)", "104 dB"),
                Pair("Cable length", "3m")
            ),
            pricingList = listOf(
                Pair(3, 499.95f),
                Pair(2, 499.95f),
                Pair(1, 499.99f),
                Pair(6, 499.95f)
            ),
            reviewList = mutableListOf(
                Review(
                    reviewTitle = "Excellent sound quality",
                    reviewContent = "I recently bought the Sennheiser HD 660 S headphones and I'm blown away by the sound quality. The open-back design creates a natural soundstage and the detail and clarity is amazing. They're also very comfortable to wear for long listening sessions.",
                    reviewStore = "Amazon",
                    reviewWriterId = 0,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023, 5, 6)
                ),
                Review(
                    reviewTitle = "Best headphones I've ever owned",
                    reviewContent = "I've owned many pairs of headphones over the years, but the Sennheiser HD 660 S are by far the best. The sound quality is incredible and they're so comfortable that I can wear them for hours without any discomfort. They're expensive, but worth every penny.",
                    reviewStore = "B&H",
                    reviewWriterId = 1,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023, 6, 12)
                ),
                Review(
                    reviewTitle = "Great for critical listening",
                    reviewContent = "If you're looking for headphones for critical listening or mastering music, the Sennheiser HD 660 S are a great choice. They're very neutral and accurate, and allow you to hear every detail in your music. They're also well-built and comfortable to wear.",
                    reviewStore = "Best Buy",
                    reviewWriterId = 2,
                    reviewRating = 4.5f,
                    reviewDate = Date(2023, 7, 19)
                ),
                Review(
                    reviewTitle = "Amazing headphones for audiophiles",
                    reviewContent = "As an audiophile, I'm always looking for headphones that can reproduce music accurately and faithfully. The Sennheiser HD 660 S are some of the best headphones I've ever heard. They're incredibly detailed and transparent, and the open-back design creates a huge soundstage. Highly recommended!",
                    reviewStore = "Newegg",
                    reviewWriterId = 3,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023, 8, 25)
                ),
                Review(
                    reviewTitle = "Great for classical music",
                    reviewContent = "I listen to a lot of classical music and the Sennheiser HD 660 S headphones are perfect for it. The soundstage is wide and natural, and the detail and clarity is superb. They're also very comfortable to wear for long listening sessions. Highly recommended for classical music lovers.",
                    reviewStore = "Amazon",
                    reviewWriterId = 4,
                    reviewRating = 4.8f,
                    reviewDate = Date(2023, 9, 30)
                )
            ),
            productBrandId = 1,
            productCategoryId = 3,
            productImages = listOf(R.drawable.mouse)
        ),
        Product(
            productId = 7,
            productName = "Samsung Galaxy S21",
            rating = 4.7f,
            specList = listOf(
                Pair("Display", "6.2 inches, Dynamic AMOLED 2X, 120Hz"),
                Pair("Processor", "Exynos 2100 (5 nm) - Global"),
                Pair("RAM", "8 GB"),
                Pair("Storage", "128 GB"),
                Pair("Battery", "4000 mAh"),
                Pair("Dimensions", "151.7 x 71.2 x 7.9 mm (5.97 x 2.80 x 0.31 in)"),
                Pair("Weight", "171 g (6.03 oz)")
            ),
            pricingList = listOf(
                Pair(0, 799.99f),
                Pair(1, 799.99f),
                Pair(2, 799.99f)
            ),
            reviewList = mutableListOf(
                Review(
                    reviewTitle = "Best smartphone I've ever owned",
                    reviewContent = "The Samsung Galaxy S21 is hands down the best smartphone I've ever owned. The camera is amazing and the display is stunning. I highly recommend this phone to anyone looking for a top-of-the-line device.",
                    reviewStore = "Amazon",
                    reviewWriterId = 0,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,5,6)
                ),
                Review(
                    reviewTitle = "Great phone, but not without its flaws",
                    reviewContent = "I've been using the Samsung Galaxy S21 for a few weeks now and it's a great phone overall. The camera is fantastic and the battery life is pretty good. However, the fingerprint scanner can be a bit finicky at times and the phone can get pretty warm when using it for extended periods of time.",
                    reviewStore = "Best Buy",
                    reviewWriterId = 1,
                    reviewRating = 4.5f,
                    reviewDate = Date(2023,7,22)
                ),
                Review(
                    reviewTitle = "Excellent device",
                    reviewContent = "The Samsung Galaxy S21 is an excellent device. The performance is top notch and the camera is absolutely amazing. It's a bit expensive, but definitely worth the price.",
                    reviewStore = "Walmart",
                    reviewWriterId = 2,
                    reviewRating = 4.7f,
                    reviewDate = Date(2023,9,15)
                )
            ),
            productBrandId = 1,
            productCategoryId = 1,
            productImages = listOf(R.drawable.mouse)
        ),
        Product(
            productId = 8,
            productName = "Samsung Galaxy Tab S7+",
            rating = 4.7f,
            specList = listOf(
                Pair("Display", "12.4 inches Super AMOLED"),
                Pair("Resolution", "2800 x 1752 pixels"),
                Pair("Processor", "Qualcomm Snapdragon 865 Plus"),
                Pair("RAM", "6 GB or 8 GB"),
                Pair("Storage", "128 GB or 256 GB"),
                Pair("Battery", "10,090 mAh"),
                Pair("Dimensions", "285 x 185 x 5.7 mm"),
                Pair("Weight", "575 g")
            ),
            pricingList = listOf(
                Pair(5, 849.99f),
                Pair(6, 949.99f),
                Pair(7, 849.99f)
            ),
            reviewList = mutableListOf(
                Review(
                    reviewTitle = "Great tablet for work and play",
                    reviewContent = "The Samsung Galaxy Tab S7+ is an amazing tablet. The screen is big and beautiful, and it's great for watching movies and playing games. The S Pen is very responsive and it's perfect for taking notes and drawing. The battery life is also very good.",
                    reviewStore = "Amazon",
                    reviewWriterId = 0,
                    reviewRating = 4.8f,
                    reviewDate = Date(2023, 5, 6)
                ),
                Review(
                    reviewTitle = "Excellent tablet for productivity",
                    reviewContent = "The Samsung Galaxy Tab S7+ is a great tablet for productivity. It's fast and responsive, and the S Pen is very useful for taking notes and sketching out ideas. The keyboard case is also very good and it makes typing much easier.",
                    reviewStore = "Best Buy",
                    reviewWriterId = 1,
                    reviewRating = 4.5f,
                    reviewDate = Date(2023, 5, 8)
                ),
                Review(
                    reviewTitle = "Best tablet I've ever owned",
                    reviewContent = "The Samsung Galaxy Tab S7+ is the best tablet I've ever owned. The screen is beautiful, the speakers sound great, and the battery life is amazing. The S Pen is also very useful for taking notes and drawing. It's definitely worth the price.",
                    reviewStore = "Samsung",
                    reviewWriterId = 2,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023, 5, 12)
                )
            ),
            productBrandId = 1,
            productCategoryId = 1,
            productImages = listOf(R.drawable.mouse)
        ),
        Product(
            productId = 9,
            productName = "Bose QuietComfort 35 II Wireless Headphones",
            rating = 4.8f,
            specList = listOf(
                Pair("Noise Cancellation", "Yes"),
                Pair("Battery Life", "Up to 20 hours"),
                Pair("Wireless Range", "30 feet"),
                Pair("Weight", "8.3 ounces"),
                Pair("Dimensions", "7.1 x 6.7 x 3.2 inches")
            ),
            pricingList = listOf(
                Pair(5, 299.00f),
                Pair(2, 349.99f),
                Pair(1, 349.00f)
            ),
            reviewList = mutableListOf(
                Review(
                    reviewTitle = "Best headphones I've ever owned",
                    reviewContent = "These headphones are amazing! The noise cancellation is top-notch and the sound quality is incredible. They're also very comfortable to wear for long periods of time.",
                    reviewStore = "Amazon",
                    reviewWriterId = 0,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,5,6)
                ),
                Review(
                    reviewTitle = "Great for travel",
                    reviewContent = "I bought these headphones for a long flight and they were a lifesaver. The noise cancellation blocked out all the engine noise and the battery life lasted the entire flight.",
                    reviewStore = "Best Buy",
                    reviewWriterId = 1,
                    reviewRating = 4.5f,
                    reviewDate = Date(2023,7,22)
                ),
                Review(
                    reviewTitle = "Great for conference calls",
                    reviewContent = "I use these headphones for work and they're perfect for conference calls. The noise cancellation blocks out background noise and the microphone is very clear.",
                    reviewStore = "Bose",
                    reviewWriterId = 2,
                    reviewRating = 4.8f,
                    reviewDate = Date(2023,9,15)
                ),
                Review(
                    reviewTitle = "Good headphones, but not for working out",
                    reviewContent = "I bought these headphones for working out, but they're not really designed for that. They get a bit uncomfortable when you start to sweat and the noise cancellation can be dangerous if you're outside.",
                    reviewStore = "Amazon",
                    reviewWriterId = 3,
                    reviewRating = 3.5f,
                    reviewDate = Date(2023,11,1)
                ),
                Review(
                    reviewTitle = "Excellent headphones",
                    reviewContent = "These headphones are amazing! The noise cancellation is incredible and the sound quality is top-notch. They're also very comfortable to wear for long periods of time.",
                    reviewStore = "Best Buy",
                    reviewWriterId = 4,
                    reviewRating = 5.0f,
                    reviewDate = Date(2023,12,25)
                )
            ),
            productBrandId = 2,
            productCategoryId = 4,
            productImages = listOf(R.drawable.mouse)
        ),
        Product(
            productId = 10,
            productName = "Apple iPhone 13 Pro",
            rating = 4.8f,
            specList = listOf(
                Pair("Display", "6.1-inch Super Retina XDR display"),
                Pair("Processor", "A15 Bionic chip with 6-core CPU"),
                Pair("Storage", "128GB/256GB/512GB/1TB"),
                Pair("Camera", "Triple 12MP camera system with 2x optical zoom"),
                Pair("Video", "ProRes video recording"),
                Pair("Battery Life", "Up to 22 hours of talk time"),
                Pair("Weight", "204 grams"),
                Pair("Dimensions", "146.7 x 71.5 x 7.65 mm")
            ),
            pricingList = listOf(
                Pair(3, 999.00f),
                Pair(2, 969.99f),
                Pair(1, 999.99f)
            ),
            reviewList = mutableListOf(
                Review(
                    reviewTitle = "Amazing Camera and Display",
                    reviewContent = "I recently upgraded to the iPhone 13 Pro and I'm loving it so far. The camera takes amazing photos and videos, and the display is crystal clear. Definitely worth the upgrade!",
                    reviewStore = "Apple Store",
                    reviewWriterId = 0,
                    reviewRating = 5.0f,
                    reviewDate = Date(2022, 4, 25)
                ),
                Review(
                    reviewTitle = "Fast and Reliable",
                    reviewContent = "I've had the iPhone 13 Pro for a few weeks now and it's been fast and reliable. The battery life is great and the camera takes amazing photos. Overall, I'm very satisfied with my purchase.",
                    reviewStore = "Amazon",
                    reviewWriterId = 1,
                    reviewRating = 4.5f,
                    reviewDate = Date(2022, 5, 10)
                ),
                Review(
                    reviewTitle = "Expensive but Worth It",
                    reviewContent = "The iPhone 13 Pro is definitely on the expensive side, but it's worth it for the features and performance. The camera is amazing and the ProRes video recording is a game changer for video production.",
                    reviewStore = "Best Buy",
                    reviewWriterId = 2,
                    reviewRating = 4.8f,
                    reviewDate = Date(2022, 6, 2)
                )
            ),
            productBrandId = 1,
            productCategoryId = 1,
            productImages = listOf(R.drawable.mouse)
        )
    )
    var Contacts = mutableListOf<Contact>(
        Contact(0,1),
        Contact(1,1)
    )

    val ContactRequests = mutableListOf<ContactRequest>(
        ContactRequest(
            senderId = 1,
            receiverId = 0
        ),
        ContactRequest(
            senderId = 2,
            receiverId = 0
        ),
        ContactRequest(
            senderId = 3,
            receiverId = 0
        ),
        ContactRequest(
            senderId = 0,
            receiverId = 3
        ),
        ContactRequest(
            senderId = 0,
            receiverId = 2
        ),
        ContactRequest(
            senderId = 0,
            receiverId = 1
        ),
    )

    val Routes: MutableList<Route> = mutableListOf<Route>(
        Route(
            routeId = 0,
            routeType = RouteType.Cheapest,
            routePaymentDate = Date(),
            routeCollectionDate = Date(),
            routeAccountId = 1,
            routeProducts = listOf(
                BoughtProduct(
                    productId = 3,
                    storeId = 2,
                    quantity = 2
                ),
                BoughtProduct(
                    productId = 1,
                    storeId = 1,
                    quantity = 1
                ),
                BoughtProduct(
                    productId = 4,
                    storeId = 0,
                    quantity = 3
                )
            )
        ),
        Route(
            routeId = 1,
            routeType = RouteType.Custom,
            routePaymentDate = Date(),
            routeCollectionDate = Date(),
            routeAccountId = 2,
            routeProducts = listOf(
                BoughtProduct(
                    productId = 2,
                    storeId = 3,
                    quantity = 2
                ),
                BoughtProduct(
                    productId = 4,
                    storeId = 2,
                    quantity = 1
                ),
                BoughtProduct(
                    productId = 1,
                    storeId = 1,
                    quantity = 4
                )
            )
        ),
        Route(
            routeId = 2,
            routeType = RouteType.Shortest,
            routePaymentDate = Date(),
            routeCollectionDate = Date(),
            routeAccountId = 3,
            routeProducts = listOf(
                BoughtProduct(
                    productId = 1,
                    storeId = 0,
                    quantity = 2
                ),
                BoughtProduct(
                    productId = 3,
                    storeId = 1,
                    quantity = 1
                ),
                BoughtProduct(
                    productId = 2,
                    storeId = 3,
                    quantity = 3
                )
            )
        ),
        Route(
            routeId = 3,
            routeType = RouteType.Shortest,
            routePaymentDate = Date(),
            routeCollectionDate = Date(),
            routeAccountId = 4,
            routeProducts = listOf(
                BoughtProduct(
                    productId = 4,
                    storeId = 2,
                    quantity = 2
                ),
                BoughtProduct(
                    productId = 3,
                    storeId = 0,
                    quantity = 1
                ),
                BoughtProduct(
                    productId = 2,
                    storeId = 1,
                    quantity = 3
                )
            )
        ),
        Route(
            routeId = 4,
            routeType = RouteType.Shortest,
            routePaymentDate = Date(),
            routeCollectionDate = Date(),
            routeAccountId = 2,
            routeProducts = listOf(
                BoughtProduct(
                    productId = 4,
                    storeId = 3,
                    quantity = 2
                ),
                BoughtProduct(
                    productId = 5,
                    storeId = 2,
                    quantity = 1
                )
            )
        ),
        Route(
            routeId = 5,
            routeType = RouteType.Shortest,
            routePaymentDate = Date(),
            routeCollectionDate = Date(),
            routeAccountId = 3,
            routeProducts = listOf(
                BoughtProduct(
                    productId = 6,
                    storeId = 2,
                    quantity = 3
                ),
                BoughtProduct(
                    productId = 4,
                    storeId = 1,
                    quantity = 1
                ),
                BoughtProduct(
                    productId = 5,
                    storeId = 3,
                    quantity = 2
                )
            )
        ),
        Route(
            routeId = 6,
            routeType = RouteType.Shortest,
            routePaymentDate = Date(),
            routeCollectionDate = Date(),
            routeAccountId = 4,
            routeProducts = listOf(
                BoughtProduct(
                    productId = 2,
                    storeId = 1,
                    quantity = 3
                ),
                BoughtProduct(
                    productId = 7,
                    storeId = 2,
                    quantity = 1
                ),
                BoughtProduct(
                    productId = 8,
                    storeId = 3,
                    quantity = 2
                )
            )
        )
    )

    var CollectionRequest: MutableList<CollectionRequest> = mutableListOf<CollectionRequest>(
        CollectionRequest(
            collectionRequestId = 0,
            routeId = 0,
            senderId = 1,
            receiverId = 2
        ),
        CollectionRequest(
            collectionRequestId = 1,
            routeId = 1,
            senderId = 2,
            receiverId = 3
        ),
        CollectionRequest(
            collectionRequestId = 2,
            routeId = 2,
            senderId = 3,
            receiverId = 4
        ),
        CollectionRequest(
            collectionRequestId = 3,
            routeId = 3,
            senderId = 4,
            receiverId = 1
        ),
        CollectionRequest(
            collectionRequestId = 4,
            routeId = 2,
            senderId = 0,
            receiverId = 3
        ),
        CollectionRequest(
            collectionRequestId = 5,
            routeId = 3,
            senderId = 1,
            receiverId = 2
        ),
        CollectionRequest(
            collectionRequestId = 6,
            routeId = 4,
            senderId = 2,
            receiverId = 1
        ),
        CollectionRequest(
            collectionRequestId = 7,
            routeId = 5,
            senderId = 3,
            receiverId = 2
        ),
        CollectionRequest(
            collectionRequestId = 8,
            routeId = 6,
            senderId = 4,
            receiverId = 3
        ),
        CollectionRequest(
            collectionRequestId = 9,
            routeId = 5,
            senderId = 1,
            receiverId = 0
        ),
        CollectionRequest(
            collectionRequestId = 10,
            routeId = 6,
            senderId = 2,
            receiverId = 0
        ),
        CollectionRequest(
            collectionRequestId = 11,
            routeId = 4,
            senderId = 3,
            receiverId = 0
        )
    )


    val Stores = listOf<Store>(
        Store(1, "Walmart", Pair(42, -73)),
        Store(2, "Target", Pair(38, -85)),
        Store(3, "Best Buy", Pair(33, -118)),
        Store(4, "Home Depot", Pair(39, -104)),
        Store(5, "Costco", Pair(45, -122)),
        Store(6, "Lowe's", Pair(37, -122)),
        Store(7, "Kroger", Pair(39, -84)),
        Store(8, "Walgreens", Pair(41, -87)),
    )

    val Categories = listOf<Category>(
        Category(categoryId = 0, categoryName = "Wasmachines", categoryGroupId = 0, R.drawable.wasmachines),
        Category(categoryId = 1, categoryName = "Droogkasten", categoryGroupId = 0, R.drawable.droogkasten),
        Category(categoryId = 2, categoryName = "Koelkasten", categoryGroupId = 0, R.drawable.koelkasten),
        Category(categoryId = 3, categoryName = "Diepvriezers", categoryGroupId = 0, R.drawable.diepvriezers),
        Category(categoryId = 4, categoryName = "Vaatwassers", categoryGroupId = 0, R.drawable.vaatwassers),
        Category(categoryId = 5, categoryName = "Fornuizen", categoryGroupId = 0, R.drawable.fornuizen),
        Category(categoryId = 6, categoryName = "Ovens", categoryGroupId = 0, R.drawable.ovens),
        Category(categoryId = 7, categoryName = "Microgolfovens", categoryGroupId = 0, R.drawable.microgolfovens),
        Category(categoryId = 8, categoryName = "Stofzuigers", categoryGroupId = 0, R.drawable.stofzuigers),
        Category(categoryId = 9, categoryName = "Strijken", categoryGroupId = 0, R.drawable.strijken),
        Category(categoryId = 10, categoryName = "Naaien", categoryGroupId = 0, R.drawable.naaien),
        Category(categoryId = 11, categoryName = "Schoonmaken", categoryGroupId = 0, R.drawable.schoonmaken),

        Category(categoryId = 12, categoryName = "Tandenborstel", categoryGroupId = 1, R.drawable.tandenborstel),
        Category(categoryId = 13, categoryName = "Haarverzorging", categoryGroupId = 1, R.drawable.haarverzorging),
        Category(categoryId = 14, categoryName = "Scheren", categoryGroupId = 1, R.drawable.scheren),

        Category(categoryId = 15, categoryName = "Smarthphones", categoryGroupId = 2, R.drawable.smarthphones),
        Category(categoryId = 16, categoryName = "Smartphone bescherming", categoryGroupId = 2, R.drawable.smartphone_bescherming),
        Category(categoryId = 17, categoryName = "Smartphone accessoires", categoryGroupId = 2, R.drawable.smartphone_accesoires),
        Category(categoryId = 18, categoryName = "Vaste telefonie", categoryGroupId = 2, R.drawable.vaste_telefonie),
        Category(categoryId = 19, categoryName = "GSM's", categoryGroupId = 2, R.drawable.gsms),

        Category(categoryId = 20, categoryName = "Laptops, Desktops & Monitoren", categoryGroupId = 3, R.drawable.laptops),
        Category(categoryId = 21, categoryName = "PC Gaming", categoryGroupId = 3, R.drawable.pc_gaming),
        Category(categoryId = 22, categoryName = "Tablets & E-Readers", categoryGroupId = 3, R.drawable.tablets_e_readers),
        Category(categoryId = 23, categoryName = "Printen & Scannen", categoryGroupId = 3, R.drawable.printen_scannen),
        Category(categoryId = 24, categoryName = "Netwerk", categoryGroupId = 3, R.drawable.netwerk),
        Category(categoryId = 25, categoryName = "Randapparatuur", categoryGroupId = 3, R.drawable.randapparatuur),
        Category(categoryId = 26, categoryName = "Geheugen & Opslag", categoryGroupId = 3, R.drawable.geheugen_opslag),

        Category(categoryId = 27, categoryName = "Grafische kaarten", categoryGroupId = 4, R.drawable.grafische_kaarten),
        Category(categoryId = 28, categoryName = "Moederborden", categoryGroupId = 4, R.drawable.moederborden),
        Category(categoryId = 29, categoryName = "Opslag", categoryGroupId = 4, R.drawable.opslag),
        Category(categoryId = 30, categoryName = "Processoren", categoryGroupId = 4, R.drawable.processoren),
        Category(categoryId = 31, categoryName = "Geheugen", categoryGroupId = 4, R.drawable.geheugen),
        Category(categoryId = 32, categoryName = "Behuizing", categoryGroupId = 4, R.drawable.behuizing),
        Category(categoryId = 33, categoryName = "Koeling", categoryGroupId = 4, R.drawable.koeling),
        Category(categoryId = 34, categoryName = "Overige onderdelen", categoryGroupId = 4, R.drawable.overige_onderdelen),

        Category(categoryId = 35, categoryName = "Televisies", categoryGroupId = 5, R.drawable.televisies),
        Category(categoryId = 36, categoryName = "Hoofdtelefoons & Oortjes", categoryGroupId = 5, R.drawable.hoofdtelefoons_ortjes),
        Category(categoryId = 37, categoryName = "Speakers", categoryGroupId = 5, R.drawable.speakers),
        Category(categoryId = 38, categoryName = "Randapparatuur TV", categoryGroupId = 5, R.drawable.randapparatuur_tv),
        Category(categoryId = 39, categoryName = "Steunen", categoryGroupId = 5, R.drawable.steunen),
        Category(categoryId = 40, categoryName = "Accessoires", categoryGroupId = 5, R.drawable.accessoires),

        Category(categoryId = 41, categoryName = "Digitale fototoestellen", categoryGroupId = 6, R.drawable.digitale_fototoestellen),
        Category(categoryId = 42, categoryName = "Analoge fototoestellen", categoryGroupId = 6, R.drawable.analoge_fototoestellen),
        Category(categoryId = 43, categoryName = "GoPro", categoryGroupId = 6, R.drawable.gopro),
        Category(categoryId = 44, categoryName = "Toebehoren", categoryGroupId = 6, R.drawable.toebehoren),
        Category(categoryId = 45, categoryName = "Bescherm- en draagtassen", categoryGroupId = 6, R.drawable.bescherm_en_draagtassen),

        Category(categoryId = 46, categoryName = "Wearables", categoryGroupId = 7, R.drawable.wearables),
        Category(categoryId = 47, categoryName = "E-mobiliteit", categoryGroupId = 7, R.drawable.e_mobiliteit),
        Category(categoryId = 48, categoryName = "Spelconsoles", categoryGroupId = 7, R.drawable.spelconsoles),
    )

    val CategoryGroups = listOf<CategoryGroup>(
        CategoryGroup(categoryGroupId = 0, categoryGroupName = "Huishouden & Groot Elektro"),
        CategoryGroup(categoryGroupId = 1, categoryGroupName = "Verzorging & Gezondheid"),
        CategoryGroup(categoryGroupId = 2, categoryGroupName = "Telefonie & Navigatie"),
        CategoryGroup(categoryGroupId = 3, categoryGroupName = "Computer & Tablet"),
        CategoryGroup(categoryGroupId = 4, categoryGroupName = "Computeronderdelen"),
        CategoryGroup(categoryGroupId = 5, categoryGroupName = "Beeld & Geluid"),
        CategoryGroup(categoryGroupId = 6, categoryGroupName = "Foto & Video"),
        CategoryGroup(categoryGroupId = 7, categoryGroupName = "Sport, Gaming & Domotica"),
    )

    /* functions */
    /**
     * Looks for an account in the Account variable
     * @param accountId the Id to be looked for
     * @return if accountId is found Account else null
     */
    fun findAccount(accountId: Int): Account? {
        Accounts.forEach { account->
            if(account._accountId == accountId){
                return account
            }
        }
        return null
    }

    fun findProduct(productId: Int): Product?{
        Products.forEach {product->
            if(product._productId == productId){
                return product
            }
        }
        return null
    }


    fun findStore(storeId: Int): Store?{
        Stores.forEach { store->
            if(store._storeId == storeId){
                return store
            }
        }
        return null
    }

    fun getCategoriesFromGroup(groupId: Int): MutableList<Category>{
        var categories: MutableList<Category> = mutableListOf()
        Categories.forEach { category ->
            if(category._categoryGroup == groupId){
                categories.add(category)
            }
        }
        return categories
    }

    fun requestExists(accountId: Int): Boolean{
        ContactRequests.forEach{
            if(it._receiverId == accountId){
                return true
            }
        }
        return false
    }

    fun removeContactRequest(senderId: Int, receiverId: Int){
        ContactRequests.removeIf { contactRequest -> contactRequest._receiverId == receiverId && contactRequest._senderId == senderId}
    }

    fun acceptContactRequest(senderId: Int, receiverId: Int){
        removeContactRequest(senderId, receiverId)
        Contacts.add(Contact(senderId, receiverId))
    }

    fun getProductsBelongingToCategory(categoryId: Int) : List<Product> {
        var products: MutableList<Product> = mutableListOf()
        Products.forEach { product ->
            if(product._productCategory == categoryId){
                products.add(product)
            }
        }
        return products
    }

    fun deleteContact(firstAccountId: Int, secondAccountId: Int) {
        val iterator = Contacts.iterator()
        while (iterator.hasNext()) {
            val contact = iterator.next()
            if ((contact._firstAccountId == firstAccountId && contact._secondAccountId == secondAccountId) ||
                (contact._firstAccountId == secondAccountId && contact._secondAccountId == firstAccountId)
            ) {
                iterator.remove()
            }
        }
    }

    fun removeCollectionRequest(collectionId: Int){
        CollectionRequest.removeIf {
            it._collectionRequestId == collectionId
        }
    }

    fun findRoute(routeId: Int): Route?{
        Routes.forEach{route ->
            if(route._routeId == routeId){
                return route
            }
        }
        return null
    }

    fun findRouteFromCollectionId(collectionId: Int): Route? {
        CollectionRequest.forEach{
            if(it._collectionRequestId == collectionId){
                return DataSource.findRoute(it._routeId)
            }
        }
        return null
    }

    fun addCollectionRequest(collectionRequest: CollectionRequest){
        CollectionRequest.add(collectionRequest)
        CurrentCollectionRequestId += 1
    }

    fun findFriends(accountId: Int): List<Account?>{
        var friends: MutableList<Account?> = mutableListOf()
        Contacts.forEach {
            if(it._firstAccountId == accountId){
                friends.add(DataSource.findAccount(it._secondAccountId))
            } else if (it._secondAccountId == accountId){
                friends.add(DataSource.findAccount(it._firstAccountId))
            }
        }
        return friends
    }
}


