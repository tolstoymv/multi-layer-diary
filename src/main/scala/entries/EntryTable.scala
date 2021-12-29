package com.tolstoy.diary
package entries

import slick.lifted.Tag
import slick.jdbc.H2Profile.api._
import com.github.tototoshi.slick.H2JodaSupport._
import org.joda.time.Instant

class EntryTable(tag: Tag) extends Table[Entry](tag, "entry") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def text = column[String]("text")
  def date = column[Instant]("date")
  def userId = column[Long]("userId")
  def * = (text, date, userId, id).mapTo[Entry]
}
