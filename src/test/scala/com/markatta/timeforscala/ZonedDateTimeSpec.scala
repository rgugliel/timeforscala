/*
 * Copyright 2015 Johan Andrén
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.markatta.timeforscala

import java.time.ZoneId

class ZonedDateTimeSpec extends BaseSpec {

  "the zoned date time enrichment" should {

    "create instances" in {
      val instance = ZonedDateTime(LocalDate(2015, 1, 1), LocalTime(20, 30, 10), ZoneId.of("GMT"))

      instance.localDate shouldEqual LocalDate(2015, 1, 1)
      instance.localTime shouldEqual LocalTime(20, 30, 10)
      instance.zone shouldEqual ZoneId.of("GMT")
    }

    "unapply" in {
      val result = ZonedDateTime(LocalDate(2015, 1, 1), LocalTime(20, 30, 10), ZoneId.of("GMT")) match {
        case ZonedDateTime(datetime, zone) => (datetime, zone)
      }
      result should equal (LocalDateTime(2015, 1, 1, 20, 30, 10), ZoneId.of("GMT"))
    }


    "add a period" in {
      val result = ZonedDateTime(LocalDate(2015, 1, 1), LocalTime(20, 30, 5), ZoneId.of("GMT")) + Days(2)
      result shouldEqual ZonedDateTime(LocalDate(2015, 1, 3), LocalTime(20, 30, 5), ZoneId.of("GMT"))
    }

    "subtract a period" in {
      val result = ZonedDateTime(LocalDate(2015, 1, 1), LocalTime(20, 30, 5), ZoneId.of("GMT")) - Days(2)
      result shouldEqual ZonedDateTime(LocalDate(2014, 12, 30), LocalTime(20, 30, 5), ZoneId.of("GMT"))
    }

    "add a duration" in {
      val result = ZonedDateTime(LocalDate(2015, 1, 1), LocalTime(20, 30, 5), ZoneId.of("GMT")) + Hours(2)
      result shouldEqual ZonedDateTime(LocalDate(2015, 1, 1), LocalTime(22, 30, 5), ZoneId.of("GMT"))
    }

    "subtract a duration" in {
      val result = ZonedDateTime(LocalDate(2015, 1, 1), LocalTime(20, 30, 5), ZoneId.of("GMT")) - Hours(2)
      result shouldEqual ZonedDateTime(LocalDate(2015, 1, 1), LocalTime(18, 30, 5), ZoneId.of("GMT"))
    }

    "calculate the duration between two zoned date times" in {
      val a = ZonedDateTime(LocalDate(2015, 1, 1), LocalTime(20, 30, 10), ZoneId.of("GMT"))
      val b = ZonedDateTime(LocalDate(2015, 1, 1), LocalTime(20, 50, 10), ZoneId.of("GMT"))
      val result = a - b
      result.minutes shouldEqual 20
    }


  }

}
