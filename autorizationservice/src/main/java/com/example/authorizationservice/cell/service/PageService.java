package com.example.authorizationservice.cell.service;

import com.example.authorizationservice.cell.DTO.PageDTO;
import com.example.authorizationservice.cell.domain.Page;
import com.example.authorizationservice.cell.rep.PageRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PageService {

    private final PageRep pageRep;

    public Page getPage(Long pageId){

        return pageRep.findByPageId(pageId);
    }

    public Page createpage(PageDTO pageDTO){

        Page page = new Page(pageDTO.getData());

        return pageRep.save(page);
    }


}
