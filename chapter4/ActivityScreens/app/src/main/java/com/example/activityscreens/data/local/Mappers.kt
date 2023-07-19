package com.example.activityscreens.data.local

import com.example.activityscreens.data.remote.response.UserDetails

object UserToCachedUserMapper {
    fun map(user: UserDetails): CachedUser {
        return CachedUser(
            id = user.id,
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            avatar = user.avatar
        )
    }
}

object CachedUserToUserMapper {
    fun map(cachedUser: CachedUser): UserDetails {
        return UserDetails(
            id = cachedUser.id,
            email = cachedUser.email,
            firstName = cachedUser.firstName,
            lastName = cachedUser.lastName,
            avatar = cachedUser.avatar
        )
    }
}
