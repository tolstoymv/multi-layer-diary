package com.tolstoy.diary
package entries

import slick.jdbc.H2Profile.api._

import slick.lifted.TableQuery

object EntryQueries {

  val entries = TableQuery[EntryTable]

  val create = entries.schema.create

}
