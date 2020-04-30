package contracts.locations

import br.com.caelum.clines.shared.domain.Country
import org.springframework.cloud.contract.spec.Contract

final BASE_URL = "locations"

final EXISTING_LOCATION_ID = 123
final NON_EXISTING_LOCATION_ID = 456

final EXISTING_LOCATION = [
        "id"     : 123,
        "country": Country.BR.name(),
        "state"  : "Rio Grande do Sul",
        "city"   : "Porto Alegre"
]

[
        Contract.make {
            name "should return an location if exists"

            request {
                method GET()

                url "${BASE_URL}/${EXISTING_LOCATION_ID}"
            }

            response {
                status OK()

                headers {
                    contentType applicationJson()
                }

                body(EXISTING_LOCATION)
            }
        },

        Contract.make {
            name "should return 404 when location not exists"

            request {
                method GET()

                url "${BASE_URL}/${NON_EXISTING_LOCATION_ID}"
            }

            response {
                status NOT_FOUND()

                bodyMatchers {
                    jsonPath('$', byRegex('^$'))
                }
            }
        },

        Contract.make {
            name "should return a list of existing locations"

            request {
                method GET()
                url BASE_URL
            }

            response {
                status OK()
                headers {
                    contentType applicationJson()
                }

                body([EXISTING_LOCATION])
            }
        },

        Contract.make {
            name "should register a new location"

            request {
                method POST()
                url BASE_URL

                headers {
                    contentType applicationJson()
                }

                body(
                        "country": "Brazil",
                        "state": "Rio Grande do Sul",
                        "city": "Caxias do Sul"
                )

                response {
                    status CREATED()

                    headers {
                        header(location(), "/${BASE_URL}/${NON_EXISTING_LOCATION_ID}")
                    }

                    bodyMatchers {
                        jsonPath('$', byRegex('^$'))
                    }
                }
            }
        },

        Contract.make {
            name "should return 400 when try to register new location without country, state or city"

            request {
                method POST()
                url BASE_URL

                headers {
                    contentType applicationJson()
                }

                body(
                        "country": null,
                        "state": null,
                        "city": null
                )
            }

            response {
                status BAD_REQUEST()

                headers {
                    contentType applicationJson()
                }

                body(
                        "errors": [
                                ["field": "country", "message": "must not be null/blank"],
                                ["field": "state", "message": "must not be null/blank"],
                                ["field": "city", "message": "must not be null/blank"],
                        ]
                )

            }
        }
]