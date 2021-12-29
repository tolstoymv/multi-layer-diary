package com.tolstoy.diary
package controllers

import entries.Entry
import services.EntryService

import io.circe.Decoder
import io.circe.Encoder
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.HttpRoutes
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.joda.time.Instant
import slick.jdbc.H2Profile
import zio.Task
import zio.interop.catz._
import zio.interop.catz.implicits._

import scala.util.Try
import scala.util.chaining.scalaUtilChainingOps

object EntryController {
  object ioz extends Http4sDsl[Task]
  import ioz._

  implicit val encodeInstant: Encoder[Instant] = Encoder.encodeString.contramap[Instant](_.toString)
  // encodeInstant: Encoder[Instant] = io.circe.Encoder$$anon$1@47ac255f

  implicit val decodeInstant: Decoder[Instant] = Decoder.decodeString.emapTry { str =>
    Try(Instant.parse(str))
  }
  private implicit val entryDecoder = jsonOf[Task, Entry]

  def routes(db: H2Profile.backend.DatabaseDef) = HttpRoutes
    .of[Task] {
      case GET -> Root => EntryService.getAllEntries(db) >>= toResponse
      case GET -> Root / "prev-day" => EntryService.getEntriesForDate(db) >>= toResponse
      case req @ POST -> Root =>
        for {
          entry <- req.as[Entry]
          id <- EntryService.addEntry(db)(entry)
          resp <- Ok(s"added number of rows: $id")
        } yield resp
    }

  def toResponse(x: Seq[Entry]) = Ok(x.asJson.spaces2)
}
