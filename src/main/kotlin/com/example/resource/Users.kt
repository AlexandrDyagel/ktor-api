package com.example.resource

import io.ktor.resources.*

@Resource("/users")
class Users {

    @Resource("{uid}")
    class Uid(val parent: Users = Users(), val uid: Long)
}