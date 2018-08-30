package com.github.syafdia.androidboilerplate.feature.login.domain.usecase

import com.github.syafdia.androidboilerplate.data.model.UserEntity
import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import io.reactivex.Single


class AuthenticateUserUseCase(private val userRepository: UserRepository) {

    fun execute(username: String, password: String): Single<UserEntity> {
        return userRepository.authenticate(username, password)
    }
}