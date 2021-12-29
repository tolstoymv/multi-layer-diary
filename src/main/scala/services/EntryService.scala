package com.tolstoy.diary
package services

import entries.Entry
import entries.EntryQueries

import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._
import com.github.tototoshi.slick.H2JodaSupport._
import com.tolstoy.diary.entries.EntryTable
import org.joda.time.Instant
import zio.Task
import zio.ZIO

object EntryService {

  def getAllEntries(db: H2Profile.backend.DatabaseDef) =
    ZIO.fromFuture(ex => db.run(EntryQueries.entries.result))

  def addEntry(db: H2Profile.backend.DatabaseDef)(entry: Entry): Task[Int] =
    ZIO.fromFuture(ex => db.run(EntryQueries.add(entry)))

  def getEntriesForDate(db: H2Profile.backend.DatabaseDef): Task[Seq[EntryTable#TableElementType]] = {
    val prevDay = EntryQueries.entries
      .filter(_.date > Instant.now().minus(60 * 60 * 24 * 2))
      .filter(_.date < Instant.now().minus(60 * 60 * 24))
      .result
    ZIO.fromFuture(ex => db.run(prevDay))
  }

}
