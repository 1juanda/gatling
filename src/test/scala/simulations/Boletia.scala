package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class Boletia extends Simulation {

  val httpProtocol = http
    .baseUrl("https://web-api.plupets.com")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val scn = scenario("Load Test - Boletia")
    .exec(
      http("GET Maximus Cupones")
        .get("/events-core/api/v1/events/simple-info/maximus-cupones")
        .check(status.is(200))
    )

  setUp(
    scn.inject(
      atOnceUsers(10), // Inicio con 10 usuarios
      incrementUsersPerSec(20) // Incrementa 20 usuarios por minuto
        .times(5) // Durante 5 minutos (5 incrementos)
        .eachLevelLasting(1.minute) // Cada incremento dura 1 minuto
        .separatedByRampsLasting(0.seconds) // Sin tiempo de rampa entre niveles
    )
  ).protocols(httpProtocol)
}
