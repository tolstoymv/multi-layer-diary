package com.tolstoy.diary
package services

import entries.EntryQueries

import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._
import zio.Task
import zio.ZIO
import zio.interop.catz._
import zio.interop.catz.implicits._

object EntryService {
  object ioz extends Http4sDsl[Task]
  import ioz._

  def service(db: H2Profile.backend.DatabaseDef) = HttpRoutes
    .of[Task] { case GET -> Root / "entries" =>
      ZIO.fromFuture(ex => db.run(EntryQueries.entries.result)).flatMap(x => Ok(x.mkString("[", ",", "]")))
    }

  def addEntry = ???

  def getEntriesForDate = ???

}
