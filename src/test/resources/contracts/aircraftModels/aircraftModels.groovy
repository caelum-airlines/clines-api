package contracts.aircraftModels

import org.springframework.cloud.contract.spec.Contract

final BASE_URL = "aircraft-model"

final NON_EXISTING_AIRCRAFT_MODEL_CODE = "AX123AC"
final DEFAULT_AIRCRAFT_MODEL_DESCRIPTION = "Boeing 737"
final DEFAULT_AIRCRAFT_MODEL = [
        "description" : DEFAULT_AIRCRAFT_MODEL_DESCRIPTION
]

[
        Contract.make {
            name "should return 201 when register a new aircraft model"
            request {
                method POST()

                url BASE_URL

                headers {
                    contentType applicationJson()
                }

                body(
                         "description": NON_EXISTING_AIRCRAFT_MODEL_CODE
                )
            }

            response {
                status CREATED()

                headers {
                    header(location(), $(producer("/${fromRequest().url()}/${NON_EXISTING_AIRCRAFT_MODEL_CODE}"),
                            consumer("/${fromRequest().url()}/${fromRequest().body('$.code')}")))
                }
            }
        },
]