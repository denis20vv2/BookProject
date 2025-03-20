package com.example.authorizationservice.page.DTO;

import com.example.authorizationservice.cell.validationСheck.ValidData;
import com.example.authorizationservice.page.domain.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.IOException;

@Getter
@Setter
//@Schema(description = "Ожидаемый Json Data")
public class PageDTO {

    @Schema(
            description = "Пример JSON-объекта",
            example =  "{\"data\": {" +
                    "\"cellObjects\": [" +
                    "  {" +
                    "    \"Key\": \"C1:1\"," +
                    "    \"ownKey\": \"null\"," +
                    "    \"data\": {" +
                    "      \"Button\": {" +
                    "        \"id\": \"btn_1\"," +
                    "        \"label\": \"button\"," +
                    "        \"imageUrl\": null," +
                    "        \"action\": {" +
                    "          \"type\": \"navigate\"," +
                    "          \"url\": \"https://example.com\"," +
                    "          \"style\": {" +
                    "            \"width\": \"200px\"," +
                    "            \"height\": \"50px\"," +
                    "            \"backgroundColor\": \"#007bff\"," +
                    "            \"textColor\": \"#ffffff\"," +
                    "            \"borderRadius\": \"8px\"" +
                    "          }" +
                    "        }" +
                    "      }" +
                    "    }," +
                    "    \"key\": \"C1:1\"" +
                    "  }" +
                    "]," +
                    "}}"
    )
    @ValidData(message = "Invalid data")
    private Data data;

}


