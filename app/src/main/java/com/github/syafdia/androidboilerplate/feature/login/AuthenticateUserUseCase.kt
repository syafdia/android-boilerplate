package com.github.syafdia.androidboilerplate.feature.login

import com.github.syafdia.androidboilerplate.data.model.User
import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import io.reactivex.Single


class AuthenticateUserUseCase(private val userRepository: UserRepository) {

    fun execute(username: String, password: String): Single<User> {
        return userRepository.authenticate(username, password)
    }
}