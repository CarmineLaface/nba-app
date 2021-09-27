package it.laface.playerlist.api

import retrofit2.Response

fun getSuccessfulResponse(): Response<PlayerListResponse> {
    val response = PlayerListApi.gson.fromJson(
        successfulResponseJson,
        PlayerListResponse::class.java
    )
    return Response.success(response)
}

private const val successfulResponseJson: String = """
    {
      "_internal": {
        "pubDateTime": "2021-01-30 06:10:33.419 EST",
        "xslt": "NBA/xsl/league/roster/marty_active_players.xsl",
        "xsltForceRecompile": "true",
        "xsltInCache": "false",
        "xsltCompileTimeMillis": "359",
        "xsltTransformTimeMillis": "8904",
        "consolidatedDomKey": "prod__transform__marty_active_players__1937780330436",
        "endToEndTimeMillis": "29060"
      },
      "league": {
        "standard": [
          {
            "firstName": "Precious",
            "lastName": "Achiuwa",
            "temporaryDisplayName": "Achiuwa, Precious",
            "personId": "1630173",
            "teamId": "1610612748",
            "jersey": "5",
            "isActive": true,
            "pos": "F",
            "heightFeet": "6",
            "heightInches": "8",
            "heightMeters": "2.03",
            "weightPounds": "225",
            "weightKilograms": "102.1",
            "dateOfBirthUTC": "1999-09-19",
            "teamSitesOnly": {
              "playerCode": "precious_achiuwa",
              "posFull": "Forward",
              "displayAffiliation": "Memphis/Nigeria",
              "freeAgentCode": ""
            },
            "teams": [
              {
                "teamId": "1610612748",
                "seasonStart": "2020",
                "seasonEnd": "2020"
              }
            ],
            "draft": {
              "teamId": "1610612748",
              "pickNum": "20",
              "roundNum": "1",
              "seasonYear": "2020"
            },
            "nbaDebutYear": "2020",
            "yearsPro": "0",
            "collegeName": "Memphis",
            "lastAffiliation": "Memphis/Nigeria",
            "country": "Nigeria"
          },
          {
            "firstName": "Jaylen",
            "lastName": "Adams",
            "temporaryDisplayName": "Adams, Jaylen",
            "personId": "1629121",
            "teamId": "1610612749",
            "jersey": "6",
            "isActive": true,
            "pos": "G",
            "heightFeet": "6",
            "heightInches": "0",
            "heightMeters": "1.83",
            "weightPounds": "225",
            "weightKilograms": "102.1",
            "dateOfBirthUTC": "1996-05-04",
            "teamSitesOnly": {
              "playerCode": "jaylen_adams",
              "posFull": "Guard",
              "displayAffiliation": "St. Bonaventure/USA",
              "freeAgentCode": ""
            },
            "teams": [
              {
                "teamId": "1610612737",
                "seasonStart": "2018",
                "seasonEnd": "2018"
              },
              {
                "teamId": "1610612757",
                "seasonStart": "2019",
                "seasonEnd": "2019"
              },
              {
                "teamId": "1610612749",
                "seasonStart": "2020",
                "seasonEnd": "2020"
              }
            ],
            "draft": {
              "teamId": "",
              "pickNum": "",
              "roundNum": "",
              "seasonYear": ""
            },
            "nbaDebutYear": "2018",
            "yearsPro": "2",
            "collegeName": "St. Bonaventure",
            "lastAffiliation": "St. Bonaventure/USA",
            "country": "USA"
          },
          {
            "firstName": "Steven",
            "lastName": "Adams",
            "temporaryDisplayName": "Adams, Steven",
            "personId": "203500",
            "teamId": "1610612740",
            "jersey": "12",
            "isActive": true,
            "pos": "C",
            "heightFeet": "6",
            "heightInches": "11",
            "heightMeters": "2.11",
            "weightPounds": "265",
            "weightKilograms": "120.2",
            "dateOfBirthUTC": "1993-07-20",
            "teamSitesOnly": {
              "playerCode": "steven_adams",
              "posFull": "Center",
              "displayAffiliation": "Pittsburgh/New Zealand",
              "freeAgentCode": ""
            },
            "teams": [
              {
                "teamId": "1610612760",
                "seasonStart": "2013",
                "seasonEnd": "2019"
              },
              {
                "teamId": "1610612740",
                "seasonStart": "2020",
                "seasonEnd": "2020"
              }
            ],
            "draft": {
              "teamId": "1610612760",
              "pickNum": "12",
              "roundNum": "1",
              "seasonYear": "2013"
            },
            "nbaDebutYear": "2013",
            "yearsPro": "7",
            "collegeName": "Pittsburgh",
            "lastAffiliation": "Pittsburgh/New Zealand",
            "country": "New Zealand"
          },
          {
            "firstName": "Bam",
            "lastName": "Adebayo",
            "temporaryDisplayName": "Adebayo, Bam",
            "personId": "1628389",
            "teamId": "1610612748",
            "jersey": "13",
            "isActive": true,
            "pos": "C-F",
            "heightFeet": "6",
            "heightInches": "9",
            "heightMeters": "2.06",
            "weightPounds": "255",
            "weightKilograms": "115.7",
            "dateOfBirthUTC": "1997-07-18",
            "teamSitesOnly": {
              "playerCode": "bam_adebayo",
              "posFull": "Center-Forward",
              "displayAffiliation": "Kentucky/USA",
              "freeAgentCode": ""
            },
            "teams": [
              {
                "teamId": "1610612748",
                "seasonStart": "2017",
                "seasonEnd": "2020"
              }
            ],
            "draft": {
              "teamId": "1610612748",
              "pickNum": "14",
              "roundNum": "1",
              "seasonYear": "2017"
            },
            "nbaDebutYear": "2017",
            "yearsPro": "3",
            "collegeName": "Kentucky",
            "lastAffiliation": "Kentucky/USA",
            "country": "USA"
          },
          {
            "firstName": "LaMarcus",
            "lastName": "Aldridge",
            "temporaryDisplayName": "Aldridge, LaMarcus",
            "personId": "200746",
            "teamId": "1610612759",
            "jersey": "12",
            "isActive": true,
            "pos": "C-F",
            "heightFeet": "6",
            "heightInches": "11",
            "heightMeters": "2.11",
            "weightPounds": "250",
            "weightKilograms": "113.4",
            "dateOfBirthUTC": "1985-07-19",
            "teamSitesOnly": {
              "playerCode": "lamarcus_aldridge",
              "posFull": "Center-Forward",
              "displayAffiliation": "Texas/USA",
              "freeAgentCode": ""
            },
            "teams": [
              {
                "teamId": "1610612757",
                "seasonStart": "2006",
                "seasonEnd": "2014"
              },
              {
                "teamId": "1610612759",
                "seasonStart": "2015",
                "seasonEnd": "2020"
              }
            ],
            "draft": {
              "teamId": "1610612741",
              "pickNum": "2",
              "roundNum": "1",
              "seasonYear": "2006"
            },
            "nbaDebutYear": "2006",
            "yearsPro": "14",
            "collegeName": "Texas",
            "lastAffiliation": "Texas/USA",
            "country": "USA"
          },
          {
            "firstName": "Ty-Shon",
            "lastName": "Alexander",
            "temporaryDisplayName": "Alexander, Ty-Shon",
            "personId": "1630234",
            "teamId": "1610612756",
            "jersey": "0",
            "isActive": true,
            "pos": "G",
            "heightFeet": "6",
            "heightInches": "3",
            "heightMeters": "1.9",
            "weightPounds": "195",
            "weightKilograms": "88.5",
            "dateOfBirthUTC": "1998-07-16",
            "teamSitesOnly": {
              "playerCode": "ty-shon_alexander",
              "posFull": "Guard",
              "displayAffiliation": "Creighton/USA",
              "freeAgentCode": ""
            },
            "teams": [
              {
                "teamId": "1610612756",
                "seasonStart": "2020",
                "seasonEnd": "2020"
              }
            ],
            "draft": {
              "teamId": "",
              "pickNum": "",
              "roundNum": "",
              "seasonYear": ""
            },
            "nbaDebutYear": "2020",
            "yearsPro": "0",
            "collegeName": "Creighton",
            "lastAffiliation": "Creighton/USA",
            "country": "USA"
          },
          {
            "firstName": "Nickeil",
            "lastName": "Alexander-Walker",
            "temporaryDisplayName": "Alexander-Walker, Nickeil",
            "personId": "1629638",
            "teamId": "1610612740",
            "jersey": "6",
            "isActive": true,
            "pos": "G",
            "heightFeet": "6",
            "heightInches": "6",
            "heightMeters": "1.98",
            "weightPounds": "205",
            "weightKilograms": "93.0",
            "dateOfBirthUTC": "1998-09-02",
            "teamSitesOnly": {
              "playerCode": "nickeil_alexander-walker",
              "posFull": "Guard",
              "displayAffiliation": "Virginia Tech/Canada",
              "freeAgentCode": ""
            },
            "teams": [
              {
                "teamId": "1610612740",
                "seasonStart": "2019",
                "seasonEnd": "2020"
              }
            ],
            "draft": {
              "teamId": "1610612751",
              "pickNum": "17",
              "roundNum": "1",
              "seasonYear": "2019"
            },
            "nbaDebutYear": "2019",
            "yearsPro": "1",
            "collegeName": "Virginia Tech",
            "lastAffiliation": "Virginia Tech/Canada",
            "country": "Canada"
          },
          {
            "firstName": "Grayson",
            "lastName": "Allen",
            "temporaryDisplayName": "Allen, Grayson",
            "personId": "1628960",
            "teamId": "1610612763",
            "jersey": "3",
            "isActive": true,
            "pos": "G",
            "heightFeet": "6",
            "heightInches": "4",
            "heightMeters": "1.93",
            "weightPounds": "198",
            "weightKilograms": "89.8",
            "dateOfBirthUTC": "1995-10-08",
            "teamSitesOnly": {
              "playerCode": "grayson_allen",
              "posFull": "Guard",
              "displayAffiliation": "Duke/USA",
              "freeAgentCode": ""
            },
            "teams": [
              {
                "teamId": "1610612762",
                "seasonStart": "2018",
                "seasonEnd": "2018"
              },
              {
                "teamId": "1610612763",
                "seasonStart": "2019",
                "seasonEnd": "2020"
              }
            ],
            "draft": {
              "teamId": "1610612762",
              "pickNum": "21",
              "roundNum": "1",
              "seasonYear": "2018"
            },
            "nbaDebutYear": "2018",
            "yearsPro": "2",
            "collegeName": "Duke",
            "lastAffiliation": "Duke/USA",
            "country": "USA"
          }
        ],
        "africa": [],
        "sacramento": [],
        "vegas": [],
        "utah": []
      }
    }
"""
