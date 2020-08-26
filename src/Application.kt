package com.github.rcd27

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*

/**
 * TODO:
 * 2) Роутинг c архитектурой и моками репозиториев
 * 3) БД CRUD вместо моков
 */

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

  install(ContentNegotiation) {
    gson {
    }
  }

  routing {

    post("/create/examination") {
      val request = call.receiveOrNull<CreateMedicalExaminationRequest>() ?: error("Bad request")
      val uuid = UUID.randomUUID().toString()

      DBMock.createdExamination = MedicalExamination(
          id = uuid,
          name = request.name,
          clinics = emptyList()
      )

      call.respond(
          CreateMedicalExaminationResponse(
              id = DBMock.createdExamination!!.id,
              name = DBMock.createdExamination!!.name
          )
      )
    }

    post("/status/examination") {
      val request = call.receive<MedicalExaminationStatusRequest>()
      if (DBMock.createdExamination?.id == request.id) {
        call.respond(
            DBMock.createdExamination!!
        )
      } else {
        call.respond(HttpStatusCode.NotFound, "No such medical examination")
      }
    }
  }
}

data class CreateMedicalExaminationRequest(val name: String)
data class CreateMedicalExaminationResponse(
    val id: String,
    val name: String
)

data class MedicalExaminationStatusRequest(val id: String)

data class MedicalExamination(
    val id: String,
    val name: String,
    val clinics: List<Clinic>
)

data class Clinic(val id: String)

