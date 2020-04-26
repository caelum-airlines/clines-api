import org.springframework.cloud.contract.spec.Contract

final BASE_URL = "airports"
final EXISTING_AIRPORT_CODE = "gru"
final NON_EXISTING_AIRPORT_CODE = "cgh"

final GRU_AIRPORT =  [
        "code": "GRU",
        "location": [
                "country": "Brazil",
                "state": "SP",
                "city": "Guarulhos",
        ]
]

[
        Contract.make {
            name "should return an airport if exists"

            request {
                method GET()

                url "${BASE_URL}/${EXISTING_AIRPORT_CODE}"
            }

            response {
                status OK()

                headers {
                    contentType applicationJson()
                }

                body(GRU_AIRPORT)
            }
        },

        Contract.make {

            name "should return 404 when airport not exists"

            request {
                method GET()

                url "${BASE_URL}/${NON_EXISTING_AIRPORT_CODE}"
            }

            response {
                status NOT_FOUND()

                bodyMatchers {
                    jsonPath('$', byRegex('^$'))
                }
            }
        },

        Contract.make {
            name "should return a list of existing airports"

            request {
                method GET()
                url BASE_URL
            }

            response {
                status OK()
                headers {
                    contentType applicationJson()
                }

                body([GRU_AIRPORT])
            }
        },

        Contract.make {
            name "should register a new airport"

            request {
                method POST()

                url BASE_URL

                headers {
                    contentType applicationJson()
                }

                body(
                        "code": "cgh",
                        "locationId": 2
                )
            }

            response {
                status CREATED()

                headers {
                    header(location(), "/${BASE_URL}/${NON_EXISTING_AIRPORT_CODE.toUpperCase()}")
                }

                bodyMatchers {
                    jsonPath('$', byRegex('^$'))
                }
            }
        },

        Contract.make {
            name "should return 409 when already exists airport"

            request {
                method POST()

                url BASE_URL

                headers {
                    contentType applicationJson()
                }

                body(
                        "code": "gru",
                        "locationId": 2
                )
            }

            response {
                status CONFLICT()

                headers {
                    contentType applicationJson()
                }

                body(
                        "errors": [
                                "message": "Airport already exists"
                        ]
                )
            }
        },

        Contract.make {
            name "should return 400 when location not exists"

            request {
                method POST()

                url BASE_URL

                headers {
                    contentType applicationJson()
                }

                body(
                        "code": "cgh",
                        "locationId": 3
                )
            }

            response {
                status BAD_REQUEST()

                headers {
                    contentType applicationJson()
                }

                body(
                        "errors": [
                                "message": "Invalid location"
                        ]
                )
            }
        },


        Contract.make {
            name "should return 400 when try to register new airport without code and location"

            request {
                method POST()

                url BASE_URL

                headers {
                    contentType applicationJson()
                }

                body(
                        "code": null,
                        "locationId": null
                )
            }

            response {
                status BAD_REQUEST()

                headers {
                    contentType applicationJson()
                }

                body(
                        "errors": [
                                ["field":"code", "message": "must not be blank"],
                                ["field":"locationId", "message": "must not be null"],
                        ]
                )
            }
        },

        Contract.make {
            name "should return 400 when try to register a new airport with a negative locationId"

            request {
                method POST()

                url BASE_URL

                headers {
                    contentType applicationJson()
                }

                body(
                        "code": "cgh",
                        "locationId": -2
                )
            }

            response {
                status BAD_REQUEST()

                headers {
                    contentType applicationJson()
                }

                body(
                        "errors": [
                                ["message": "must be greater than 0", "field": "locationId"]
                        ]
                )
            }
        },
]