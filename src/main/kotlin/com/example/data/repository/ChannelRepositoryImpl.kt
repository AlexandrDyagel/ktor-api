package com.example.data.repository

import com.example.data.tables.ChannelTable
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
            ChannelTable.selectAll().map(ResultRow::toChannelDTO)
        }
    }

    override suspend fun findByUid(uid: Long): ChannelDTO? {
        return dbQuery {
            ChannelTable.selectAll().where { ChannelTable.uid.eq(uid) }.singleOrNull()?.toChannelDTO()
        }
    }

    override suspend fun findByOwner(ownerUid: Long): List<ChannelDTO> {
        return dbQuery {
            ChannelTable.selectAll().where { ChannelTable.ownerUid.eq(ownerUid) }.map(ResultRow::toChannelDTO)
        }
    }

    override suspend fun create(channel: ChannelDTO): ChannelDTO? {
        return dbQuery {
            ChannelTable.insertReturning { table ->
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
            ChannelTable.deleteWhere { ChannelTable.uid.eq(uid) } == 1
        }
    }

}

private fun ResultRow.toChannelDTO(): ChannelDTO {
    return ChannelDTO(
        uid = this[ChannelTable.uid],
        title = this[ChannelTable.title],
        description = this[ChannelTable.description],
        username = this[ChannelTable.username],
        image = this[ChannelTable.image],
        inviteLink = this[ChannelTable.inviteLink],
        ownerUid = this[ChannelTable.ownerUid]
    )
}

