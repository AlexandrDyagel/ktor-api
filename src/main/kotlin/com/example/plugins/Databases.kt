package com.example.plugins

import com.example.data.tables.ChannelTable
import com.example.data.tables.UserTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

// by using with(environment.config) we can get access to the application.conf configuration file
fun Application.configureDatabases() /* = with(environment.config) */ {
    val dataSource = Database.connect(configureDataSource(false))

    transaction(dataSource) {
        SchemaUtils.create(
            UserTable, ChannelTable
        )
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }

fun configureDataSource(embedded: Boolean): HikariDataSource = HikariDataSource(HikariConfig().apply {
    poolName = "Database pool"
    addDataSourceProperty("cachePrepStmts", "true")
    addDataSourceProperty("prepStmtCacheSize", "250")
    addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
    maximumPoolSize = 20

    if (embedded) {
        username = "root"
        password = ""
        driverClassName = "org.h2.Driver"
        jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
    } else {
        Class.forName("org.postgresql.Driver")
        username = System.getenv("POSTGRES_USER") //"test" //
        password = System.getenv("POSTGRES_PASSWORD") //"test" //
        driverClassName = "org.postgresql.Driver"
        jdbcUrl = System.getenv("POSTGRES_URL") //"jdbc:postgresql://localhost:5432/db_name" //
    }
})
