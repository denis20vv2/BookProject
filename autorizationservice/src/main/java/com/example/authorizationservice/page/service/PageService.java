package com.example.authorizationservice.page.service;


import com.example.authorizationservice.page.DTO.PageDTO;
import com.example.authorizationservice.page.domain.Data;
import com.example.authorizationservice.page.domain.Page;
import com.example.authorizationservice.page.rep.PageRep;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PageService {

    @Autowired
    private final PageRep pageRep;

    @Autowired
    private ObjectMapper objectMapper;

    /*public Data getPageData(Long pageId) {
        // Получаем данные страницы как строку JSON
        String jsonData = pageRep.getPageDataByPageId(pageId);

        try {
            // Преобразуем строку JSON в объект Data
            return objectMapper.readValue(jsonData, Data.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при десериализации данных: " + e.getMessage());
        }
    }*/

    public Page getPage(Long pageId){

        return pageRep.findByPageId(pageId);
    }

    public Page createPage(PageDTO pageDTO){

        //cellService.CreateObject(pageDTO);

        Page page = new Page(pageDTO.getData());

        return pageRep.save(page);
    }

}
