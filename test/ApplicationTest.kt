package com.github.rcd27

import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertTrue

class ApplicationTest {

  @Test
  fun `create examination`() {

    withTestApplication({ module(testing = true) }) {
      val call = handleRequest(HttpMethod.Post, "/create/examination") {
        addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        setBody(Gson().toJson(
            CreateMedicalExaminationRequest("Тестовое обследование")
        ))
      }

      assertTrue { call.requestHandled }
    }
  }
}
