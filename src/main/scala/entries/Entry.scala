package com.tolstoy.diary
package entries

import java.time.Instant

final case class Entry(text: String, date: Instant, userId: Long, id: Long = 0L) extends Product with Serializable
