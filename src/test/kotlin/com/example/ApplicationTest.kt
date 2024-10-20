package com.example

import com.example.plugins.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }

        val client = createClient{
            install(ContentNegotiation) {
                json()
            }
        }

        client.get("api/v1/user/1").apply {
            assertEquals(HttpStatusCode.OK, status)
            val body = body<User>()
            assertEquals(1, body.id)
            assertEquals("Alex", body.name)
        }
    }
}
