package dao

import slick.jdbc.JdbcProfile
import play.api.db.slick.HasDatabaseConfigProvider
import models._

trait Tables extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  class UsersTable(tag: Tag) extends Table[User](tag, "USERS") {
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def age = column[Int]("AGE")
    def companyId = column[Option[Int]]("COMPANY_ID")
    def * = (id.?, name, age, companyId) <> ((User.apply _).tupled, User.unapply)
  }

  class CompaniesTable(tag: Tag) extends Table[Company](tag, "COMPANIES") {
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def address = column[String]("ADDRESS")
    def * = (id.?, name, address) <> ((Company.apply _).tupled, Company.unapply)
  }

  val Users = TableQuery[UsersTable]
  val Companies = TableQuery[CompaniesTable]
}