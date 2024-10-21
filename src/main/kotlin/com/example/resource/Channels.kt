package com.example.resource

import io.ktor.resources.*

@Resource("/channels")
class Channels {

    @Resource("{uid}")
    class Uid(val parent: Channels = Channels(), val uid: Long)

    @Resource("owner/{ownerUid}")
    class OwnerUid(val parent: Channels = Channels(), val ownerUid: Long)
}