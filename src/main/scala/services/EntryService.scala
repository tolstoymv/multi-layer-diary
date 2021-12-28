package com.tolstoy.diary
package services

import entries.EntryQueries

import com.tolstoy.diary.entries.Entry
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

  def getAllEntries(db: H2Profile.backend.DatabaseDef) =
    ZIO.fromFuture(ex => db.run(EntryQueries.entries.result))

  def addEntry(db: H2Profile.backend.DatabaseDef)(entry: Entry): Task[Int] =
    ZIO.fromFuture(ex => db.run(EntryQueries.add(entry)))

  def getEntriesForDate = ???

}
