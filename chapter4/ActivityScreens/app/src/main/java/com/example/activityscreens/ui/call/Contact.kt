package com.example.activityscreens.ui.call

import com.example.activityscreens.R

data class Contact(
    val name: String,
    val duration: String,
    val date: String,
    val callType: CallType,
    val imageID: Int
)

enum class CallType(val isMissed: Boolean = false) {
    INCOMING,
    OUTGOING
}

class Data {
    companion object {
        var contacts = listOf(
            Contact(
                "Shubham",
                "30min 45sec",
                "10 Mar, 8:25 am",
                CallType.INCOMING,
                R.drawable.img_contact_avatar
            ),
            Contact(
                "Darshan",
                "30min 45sec",
                "10 Mar, 8:25 am",
                CallType.INCOMING,
                R.drawable.img_contact_avatar
            ),
            Contact(
                "Shyam",
                "30min 45sec",
                "10 Mar, 8:25 am",
                CallType.INCOMING,
                R.drawable.img_contact_avatar
            ),
            Contact(
                "Shubham",
                "30min 45sec",
                "10 Mar, 8:25 am",
                CallType.INCOMING,
                R.drawable.img_contact_avatar
            ),
            Contact(
                "Darshan",
                "30min 45sec",
                "10 Mar, 8:25 am",
                CallType.INCOMING,
                R.drawable.img_contact_avatar
            ),
            Contact(
                "Shubham",
                "30min 45sec",
                "10 Mar, 8:25 am",
                CallType.INCOMING,
                R.drawable.img_contact_avatar
            ),
            Contact(
                "Shubham",
                "30min 45sec",
                "10 Mar, 8:25 am",
                CallType.INCOMING,
                R.drawable.img_contact_avatar
            ),
            Contact(
                "Shubham",
                "30min 45sec",
                "10 Mar, 8:25 am",
                CallType.INCOMING,
                R.drawable.img_contact_avatar
            ),
            Contact(
                "Darshan",
                "30min 45sec",
                "10 Mar, 8:25 am",
                CallType.INCOMING,
                R.drawable.img_contact_avatar
            ),
            Contact(
                "Shubham",
                "30min 45sec",
                "10 Mar, 8:25 am",
                CallType.INCOMING,
                R.drawable.img_contact_avatar
            ),
            Contact(
                "Shubham",
                "30min 45sec",
                "10 Mar, 8:25 am",
                CallType.INCOMING,
                R.drawable.img_contact_avatar
            )
        )
    }
}
