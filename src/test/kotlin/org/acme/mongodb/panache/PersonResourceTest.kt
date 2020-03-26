package org.acme.mongodb.panache

import io.quarkus.mongodb.panache.MongoEntity
import io.quarkus.mongodb.panache.PanacheMongoEntityBase
import io.quarkus.mongodb.panache.PanacheMongoRepository
import io.quarkus.test.junit.QuarkusTest
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonProperty
import org.junit.jupiter.api.Test
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@MongoEntity(collection = "person")
data class Person @BsonCreator constructor(
  @BsonProperty("first_name") var name: String
) : PanacheMongoEntityBase()

@ApplicationScoped
class PersonRepository : PanacheMongoRepository<Person>

@QuarkusTest
class PersonResourceTest {

  @Inject
  lateinit var personRepository: PersonRepository

  @Test
  fun testHelloEndpoint() {
    personRepository.deleteAll()
    personRepository.persist(Person("toto"))
    personRepository.listAll() // <-- failed here
  }
}
