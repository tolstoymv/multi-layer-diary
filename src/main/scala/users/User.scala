package com.tolstoy.diary
package users

final case class User (id: Long, name: Option[String], telegramId: String, phoneNumber: Option[String])
