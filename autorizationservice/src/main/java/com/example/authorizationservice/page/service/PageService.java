package com.example.authorizationservice.page.service;


import com.example.authorizationservice.cell.merging.Merging;
import com.example.authorizationservice.page.DTO.CellMergingRequest;
import com.example.authorizationservice.page.DTO.PageDTO;
import com.example.authorizationservice.page.domain.CellObject;
import com.example.authorizationservice.page.domain.Data;
import com.example.authorizationservice.page.domain.Page;
import com.example.authorizationservice.page.rep.PageRep;
import com.example.authorizationservice.user.web.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
@NoArgsConstructor
public class PageService {

    private  Merging merging;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private  PageRep pageRep;

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

    public Page getPage(Long pageId){ return pageRep.findByPageId(pageId);}

    public Page createPage(PageDTO pageDTO){

        Page page = new Page(pageDTO.getData());

        return pageRep.save(page);
    }

    public Page mergingCell(CellMergingRequest cellMergingRequest){

        Page page = pageRep.findByPageId(cellMergingRequest.getPageId());
        if(page == null) throw new EntityNotFoundException("Ошибка: page с таким id не найден: " + cellMergingRequest.getPageId());

        page = checkingCell(page,cellMergingRequest.getKeys() );

        return pageRep.save(page);
    }

    public Page checkingCell(Page page, List<String> keysMerging){

        List<CellObject> cellObjects = page.getData().getCellObjects();
        List<Object> objects = new ArrayList<>();
        List<String> keys = new ArrayList<>();
        String keyMerging = null;
        boolean flag = false;
        for(CellObject cellObject : cellObjects){
            String key = cellObject.getKey();
            keys.add(key);

            if (keysMerging.contains(key)) {
                if(cellObject.getData() != null){
                    if(!flag){
                        keyMerging = key;
                        flag = true;
                    }else throw new IllegalArgumentException("Выбранная ячейка имеет собственные данные: "+ cellObject.getKey());
                }
                objects.add(cellObject.getData());
            }
        }

        if(merging.canMerge(merging.fillMatrix(merging.createMatrix(keys), keysMerging))){

            int index = 0;
            List<CellObject> newCellObjects = new ArrayList<>();

            for(CellObject cellObject : cellObjects){
                if (cellObject.getData() == null && keysMerging.contains(cellObject.getKey())){
                    cellObject.setOwnKey(cellObject.getKey());
                    cellObject.setKey(keyMerging);
                    newCellObjects.add(cellObject);
                }else newCellObjects.add(cellObject);

            }
            Data data = new Data();
            data.setCellObjects(newCellObjects);
            page.setData(data);
            return page;
        }else throw new IllegalArgumentException("ошибка проерки возможности объединения ячеек");
    }

    public Page ungroupingCell(CellMergingRequest cellMergingRequest){

        Page page = pageRep.findByPageId(cellMergingRequest.getPageId());
        if(page == null) throw new EntityNotFoundException("Ошибка: page с таким id не найден: " + cellMergingRequest.getPageId());

        page = checkUngroupingCell(page,cellMergingRequest.getKeys() );

        return pageRep.save(page);
    }

    public Page checkUngroupingCell(Page page, List<String> keysMerging){

        List<CellObject> cellObjects = page.getData().getCellObjects();
        List<CellObject> objects = new ArrayList<>();

        for(CellObject cellObject : cellObjects){
            String key = cellObject.getKey();
            //keys.add(key);

            if (keysMerging.contains(key)){

                if (cellObject.getOwnKey() != null){


                cellObject.setKey(cellObject.getOwnKey());
                cellObject.setOwnKey(null);
                objects.add(cellObject);

            }else  { // Тут уточнить как именно лучше разъединять ячейки Нужнали обработка ошибок если хотя бы одна ячейка
                    // выбрана ошибочно ?
                    // разгруппировать те, что можно отсоединить, остальные игнорить?
                    //throw new EntityNotFoundException("Ошибка: не возможно разгрупировать ячейку с id: " + key +" у ячейки отсутствует ownKey");
                    logger.info("Ошибка: не возможно разгрупировать ячейку с id: " + key +" у ячейки отсутствует ownKey");
                    objects.add(cellObject);
                }

            }else objects.add(cellObject);
        }

        Data data = new Data();
        data.setCellObjects(objects);
        page.setData(data);

        return page;
    }



}
