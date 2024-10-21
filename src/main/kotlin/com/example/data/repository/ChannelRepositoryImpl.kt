package com.example.data.repository

import com.example.data.tables.ChannelsTable
import com.example.domain.model.ChannelDTO
import com.example.domain.repository.ChannelRepository
import com.example.plugins.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertReturning
import org.jetbrains.exposed.sql.selectAll

class ChannelRepositoryImpl : ChannelRepository {

    override suspend fun findAll(): List<ChannelDTO> {
        return dbQuery {
            ChannelsTable.selectAll().map(ResultRow::toChannelDTO)
        }
    }

    override suspend fun findByUid(uid: Long): ChannelDTO? {
        return dbQuery {
            ChannelsTable.selectAll().where { ChannelsTable.uid.eq(uid) }.singleOrNull()?.toChannelDTO()
        }
    }

    override suspend fun findByOwner(ownerUid: Long): List<ChannelDTO> {
        return dbQuery {
            ChannelsTable.selectAll().where { ChannelsTable.ownerUid.eq(ownerUid) }.map(ResultRow::toChannelDTO)
        }
    }

    override suspend fun create(channel: ChannelDTO): ChannelDTO? {
        return dbQuery {
            ChannelsTable.insertReturning { table ->
                table[uid] = channel.uid
                table[title] = channel.title
                table[description] = channel.description
                table[username] = channel.username
                table[image] = "https://t.me/i/userpic/160/${channel.username}.jpg"
                table[inviteLink] = channel.inviteLink
                table[ownerUid] = channel.ownerUid
            }.singleOrNull()?.toChannelDTO()
        }
    }

    override suspend fun delete(uid: Long): Boolean {
        return dbQuery {
            ChannelsTable.deleteWhere { ChannelsTable.uid.eq(uid) } == 1
        }
    }

}

private fun ResultRow.toChannelDTO(): ChannelDTO {
    return ChannelDTO(
        uid = this[ChannelsTable.uid],
        title = this[ChannelsTable.title],
        description = this[ChannelsTable.description],
        username = this[ChannelsTable.username],
        image = this[ChannelsTable.image],
        inviteLink = this[ChannelsTable.inviteLink],
        ownerUid = this[ChannelsTable.ownerUid]
    )
}

