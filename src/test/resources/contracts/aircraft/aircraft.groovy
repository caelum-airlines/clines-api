package contracts.aircraft

import org.springframework.cloud.contract.spec.Contract

final BASE_URL = "/aircraft"

final EXISTING_AIRCRAFT_CODE = "BX123AC"
final NON_EXISTING_AIRCRAFT_CODE = "AX123AC"
final DEFAULT_AIRCRAFT_MODEL = "Boeing 737"

final DEFAULT_AIRCRAFT_RESULT = [
        "code" : EXISTING_AIRCRAFT_CODE,
        "model": [
                "description": DEFAULT_AIRCRAFT_MODEL,
                "id"         : 1
        ]
]

[
        Contract.make {

            name "should return a list of all aircraft"

            request {
                url BASE_URL
                method GET()
            }

            response {
                status OK()

                headers {
                    contentType applicationJson()
                }

                body([DEFAULT_AIRCRAFT_RESULT])
            }
        },

        Contract.make {

            name "should show details of the aircraft by code"

            request {
                url "${BASE_URL}/${EXISTING_AIRCRAFT_CODE}"
                method GET()

            }

            response {
                status OK()

                headers {
                    contentType applicationJson()
                }

                body(DEFAULT_AIRCRAFT_RESULT)
            }
        },

        Contract.make {

            name "should return 404 when receive a non-existing aircraft code"

            request {
                url "${BASE_URL}/${NON_EXISTING_AIRCRAFT_CODE}"
                method GET()

            }

            response {
                status NOT_FOUND()
                bodyMatchers {
                    jsonPath('$', byRegex('^$'))
                }
            }
        },

        Contract.make {
            name "should return 201 when register a new aircraft"
            request {
                method POST()

                url BASE_URL

                headers {
                    contentType applicationJson()
                }

                body(
                        "code": NON_EXISTING_AIRCRAFT_CODE,
                        "modelId": 1,
                )
            }

            response {
                status CREATED()

                headers {
                    header(location(), $(producer("${BASE_URL}/${NON_EXISTING_AIRCRAFT_CODE}"), consumer("${BASE_URL}/${fromRequest().body('$.code')}")))
                }
            }
        },

        Contract.make {
            name "should return 409 when try to register an existing aircraft"
            request {
                method POST()
                url BASE_URL

                headers {
                    contentType applicationJson()
                }

                body(
                        "code": EXISTING_AIRCRAFT_CODE,
                        "modelId": 1
                )
            }

            response {
                status CONFLICT()

                body(
                        "errors": [
                                ["message": "Aircraft already exists"]
                        ]
                )
            }
        },

        Contract.make {
            name "should return 400 when try to register a new aircraft with a non existing model id"
            request {
                method POST()
                url BASE_URL

                headers {
                    contentType applicationJson()
                }

                body(
                        "code": NON_EXISTING_AIRCRAFT_CODE,
                        "modelId": 2L
                )
            }

            response {
                status BAD_REQUEST()
                body(
                        "errors": [
                                ["message": "Cannot find aircraft model"]
                        ]
                )
            }
        },

        Contract.make {
            name "should return 400 when try to create a new aircraft without required arguments"

            request {
                method POST()
                url BASE_URL

                headers {
                    contentType applicationJson()
                }

                body(
                        "code": null,
                        "modelId": null
                )
            }

            response {
                status BAD_REQUEST()

                body(
                        "errors": [
                                ["field": "code", "message": "must not be null"],
                                ["field": "modelId", "message": "must not be null"],
                        ]
                )

                bodyMatchers {
                    jsonPath('$.errors', byType {
                        minOccurrence(2)
                    })
                }
            }
        },

        Contract.make {
            name "should return 400 when try to create a new aircraft with blank code"

            request {
                method POST()
                url BASE_URL
                headers {
                    contentType applicationJson()
                }

                body(
                        "code": "",
                        "modelId": 1
                )
            }

            response {
                status BAD_REQUEST()
                body(
                        "errors": [
                                ["field": "code", "message": "must not be blank"]
                        ]
                )
            }
        }
]
