package com.tolstoy.diary
package controllers

import entries.Entry
import services.EntryService

import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.HttpRoutes
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import slick.jdbc.H2Profile
import zio.Task
import zio.interop.catz._
import zio.interop.catz.implicits._

import scala.util.chaining.scalaUtilChainingOps

object EntryController {
  object ioz extends Http4sDsl[Task]
  import ioz._
  private implicit val entryDecoder = jsonOf[Task, Entry]
//  private implicit val entryEncoder =

  def routes(db: H2Profile.backend.DatabaseDef) = HttpRoutes
    .of[Task] {
      case GET -> Root => EntryService.getAllEntries(db) >>= toResponse
      case req @ POST -> Root =>
        for {
          entry <- req.as[Entry]
          id <- EntryService.addEntry(db)(entry)
          resp <- Ok(s"added number of rows: $id")
        } yield resp
    }

  def toResponse(x: Seq[Entry]) = Ok(x.asJson.spaces2)
}
