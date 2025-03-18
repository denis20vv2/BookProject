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
public class PageDTO {

   /* @NotNull(message = "data не задан")
    @Schema(
            description = "Пример JSON-объекта",
            example = "{\n" +
                    "  \"data\": [\n" +
                    "    {\n" +
                    "      \"data\": [\n" +
                    "        {\n" +
                    "          \"Key\": \"C1:1\",\n" +
                    "          \"data\": {\n" +
                    "            \"type\": \"button\",\n" +
                    "            \"id\": \"btn_1\",\n" +
                    "            \"label\": \"button\",\n" +
                    "            \"imageUrl\": null,\n" +
                    "            \"action\": {\n" +
                    "              \"type\": \"navigate\",\n" +
                    "              \"url\": \"https://example.com\",\n" +
                    "              \"style\": {\n" +
                    "                \"width\": \"200px\",\n" +
                    "                \"height\": \"50px\",\n" +
                    "                \"backgroundColor\": \"#007bff\",\n" +
                    "                \"textColor\": \"#ffffff\",\n" +
                    "                \"borderRadius\": \"8px\"\n" +
                    "              }\n" +
                    "            }\n" +
                    "          }\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"Key\": \"C1:2\",\n" +
                    "          \"data\": {\n" +
                    "            \"table\": {\n" +
                    "              \"columns\": [\n" +
                    "                {\n" +
                    "                  \"key\": \"id\",\n" +
                    "                  \"label\": \"ID\",\n" +
                    "                  \"type\": \"number\",\n" +
                    "                  \"filterable\": true,\n" +
                    "                  \"sortable\": true\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"key\": \"name\",\n" +
                    "                  \"label\": \"Имя\",\n" +
                    "                  \"type\": \"string\",\n" +
                    "                  \"filterable\": true,\n" +
                    "                  \"sortable\": true,\n" +
                    "                  \"searchable\": true\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"key\": \"age\",\n" +
                    "                  \"label\": \"Возраст\",\n" +
                    "                  \"type\": \"number\",\n" +
                    "                  \"filterable\": true,\n" +
                    "                  \"sortable\": true\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"key\": \"role\",\n" +
                    "                  \"label\": \"Роль\",\n" +
                    "                  \"type\": \"string\",\n" +
                    "                  \"filterable\": true,\n" +
                    "                  \"sortable\": true,\n" +
                    "                  \"searchable\": true\n" +
                    "                }\n" +
                    "              ],\n" +
                    "              \"rows\": [\n" +
                    "                {\n" +
                    "                  \"id\": 1,\n" +
                    "                  \"name\": \"Алексей\",\n" +
                    "                  \"age\": 29,\n" +
                    "                  \"role\": \"Администратор\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"id\": 2,\n" +
                    "                  \"name\": \"Мария\",\n" +
                    "                  \"age\": 24,\n" +
                    "                  \"role\": \"Пользователь\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"id\": 3,\n" +
                    "                  \"name\": \"Иван\",\n" +
                    "                  \"age\": 35,\n" +
                    "                  \"role\": \"Модератор\"\n" +
                    "                }\n" +
                    "              ],\n" +
                    "              \"pagination\": {\n" +
                    "                \"currentPage\": 1,\n" +
                    "                \"totalPages\": 10,\n" +
                    "                \"pageSize\": 10,\n" +
                    "                \"totalItems\": 100\n" +
                    "              },\n" +
                    "              \"filters\": {\n" +
                    "                \"name\": \"\",\n" +
                    "                \"age\": null,\n" +
                    "                \"role\": \"\"\n" +
                    "              },\n" +
                    "              \"search\": \"\"\n" +
                    "            }\n" +
                    "          }\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"Key\": \"C1:3\",\n" +
                    "          \"data\": {\n" +
                    "            \"link\": {\n" +
                    "              \"text\": \"Подробнее\",\n" +
                    "              \"url\": \"https://example.com/details\",\n" +
                    "              \"dynamicParams\": {\n" +
                    "                \"id\": \"{row_id}\"\n" +
                    "              },\n" +
                    "              \"target\": \"_blank\",\n" +
                    "              \"icon\": {\n" +
                    "                \"type\": \"image\",\n" +
                    "                \"src\": \"https://example.com/icon.png\",\n" +
                    "                \"alt\": \"Иконка\"\n" +
                    "              },\n" +
                    "              \"styles\": {\n" +
                    "                \"color\": \"#007bff\",\n" +
                    "                \"textDecoration\": \"underline\",\n" +
                    "                \"fontSize\": \"14px\"\n" +
                    "              }\n" +
                    "            }\n" +
                    "          }\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"Key\": \"C2:1\",\n" +
                    "          \"data\": {\n" +
                    "            \"checkbox\": {\n" +
                    "              \"id\": \"chk_1\",\n" +
                    "              \"label\": \"Выбрать\",\n" +
                    "              \"checked\": false,\n" +
                    "              \"disabled\": false,\n" +
                    "              \"indeterminate\": false,\n" +
                    "              \"styles\": {\n" +
                    "                \"size\": \"medium\",\n" +
                    "                \"color\": \"#007bff\"\n" +
                    "              }\n" +
                    "            }\n" +
                    "          }\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"Key\": \"C2:2\",\n" +
                    "          \"data\": {\n" +
                    "            \"form\": {\n" +
                    "              \"fields\": [\n" +
                    "                {\n" +
                    "                  \"id\": \"name\",\n" +
                    "                  \"label\": \"Имя\",\n" +
                    "                  \"type\": \"text\",\n" +
                    "                  \"placeholder\": \"Введите имя\",\n" +
                    "                  \"required\": true,\n" +
                    "                  \"maxLength\": 50,\n" +
                    "                  \"validation\": {\n" +
                    "                    \"regex\": \"^[А-Яа-яA-Za-z\\\\s]+$\",\n" +
                    "                    \"errorMessage\": \"Допустимы только буквы\"\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"id\": \"phone\",\n" +
                    "                  \"label\": \"Телефон\",\n" +
                    "                  \"type\": \"tel\",\n" +
                    "                  \"placeholder\": \"+7 (___) ___-__-__\",\n" +
                    "                  \"required\": true,\n" +
                    "                  \"mask\": \"+7 (999) 999-99-99\",\n" +
                    "                  \"validation\": {\n" +
                    "                    \"regex\": \"^\\\\+7 \\\\(\\\\d{3}\\\\) \\\\d{3}-\\\\d{2}-\\\\d{2}$\",\n" +
                    "                    \"errorMessage\": \"Введите корректный номер телефона\"\n" +
                    "                  }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"id\": \"email\",\n" +
                    "                  \"label\": \"Email\",\n" +
                    "                  \"type\": \"email\",\n" +
                    "                  \"placeholder\": \"example@mail.com\",\n" +
                    "                  \"required\": true,\n" +
                    "                  \"validation\": {\n" +
                    "                    \"regex\": \"^[\\\\w.-]+@[\\\\w.-]+\\\\.[a-zA-Z]{2,6}$\",\n" +
                    "                    \"errorMessage\": \"Введите корректный email\"\n" +
                    "                  }\n" +
                    "                }\n" +
                    "              ],\n" +
                    "              \"submit\": {\n" +
                    "                \"text\": \"Отправить\",\n" +
                    "                \"action\": \"https://example.com/submit\",\n" +
                    "                \"method\": \"POST\"\n" +
                    "              }\n" +
                    "            }\n" +
                    "          }\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"Key\": \"C2:3\",\n" +
                    "          \"data\": {\n" +
                    "            \"dropdown\": {\n" +
                    "              \"id\": \"user_role\",\n" +
                    "              \"label\": \"Выберите роль\",\n" +
                    "              \"placeholder\": \"Начните ввод...\",\n" +
                    "              \"searchable\": true,\n" +
                    "              \"allowCustomInput\": true,\n" +
                    "              \"multiple\": false,\n" +
                    "              \"options\": [\n" +
                    "                {\n" +
                    "                  \"id\": \"admin\",\n" +
                    "                  \"label\": \"Администратор\",\n" +
                    "                  \"value\": \"admin\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"id\": \"moderator\",\n" +
                    "                  \"label\": \"Модератор\",\n" +
                    "                  \"value\": \"moderator\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                  \"id\": \"user\",\n" +
                    "                  \"label\": \"Пользователь\",\n" +
                    "                  \"value\": \"user\"\n" +
                    "                }\n" +
                    "              ],\n" +
                    "              \"selected\": null,\n" +
                    "              \"validation\": {\n" +
                    "                \"required\": true,\n" +
                    "                \"errorMessage\": \"Выберите значение из списка\"\n" +
                    "              },\n" +
                    "              \"styles\": {\n" +
                    "                \"width\": \"100%\",\n" +
                    "                \"borderRadius\": \"8px\",\n" +
                    "                \"backgroundColor\": \"#ffffff\",\n" +
                    "                \"borderColor\": \"#ccc\"\n" +
                    "              }\n" +
                    "            }\n" +
                    "          }\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}"
    )
    private Data data;*/


    @NotNull(message = "data не задан")
    @Schema(
            description = "Пример JSON-объекта",
            example = "{\n" +
                    "  \"cellObjects\": [\n" +
                    "    {\n" +
                    "      \"Key\": \"C1:1\",\n" +
                    "      \"data\": {\n" +
                    "        \"Button\": {\n" +
                    "          \"id\": \"btn_1\",\n" +
                    "          \"label\": \"button\",\n" +
                    "          \"imageUrl\": null,\n" +
                    "          \"action\": {\n" +
                    "            \"type\": \"navigate\",\n" +
                    "            \"url\": \"https://example.com\",\n" +
                    "            \"style\": {\n" +
                    "              \"width\": \"200px\",\n" +
                    "              \"height\": \"50px\",\n" +
                    "              \"backgroundColor\": \"#007bff\",\n" +
                    "              \"textColor\": \"#ffffff\",\n" +
                    "              \"borderRadius\": \"8px\"\n" +
                    "            }\n" +
                    "          }\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}"
    )
    @ValidData(message = "Invalid data")
    private Data data;

}


