package dao

import scala.concurrent.Future

import javax.inject.Inject
import models.Company
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import scala.concurrent.ExecutionContext
import slick.jdbc.JdbcProfile

class CompaniesDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  private val Companies = TableQuery[CompaniesTable]

  def all()(implicit ec: ExecutionContext): Future[Seq[Company]] = db.run(Companies.result)

  def insert(c: Company)(implicit ec: ExecutionContext): Future[Unit] = db.run(Companies += c).map { _ => () }

  private class CompaniesTable(tag: Tag) extends Table[Company](tag, "COMPANIES") {

    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def address = column[String]("ADDRESS")

    def * = (id.?, name, address) <> ((Company.apply _).tupled, Company.unapply)
  }
}
