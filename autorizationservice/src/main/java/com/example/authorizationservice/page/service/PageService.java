package com.example.authorizationservice.page.service;

import com.example.authorizationservice.page.DTO.PageDTO;
import com.example.authorizationservice.page.domain.Page;
import com.example.authorizationservice.page.rep.PageRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PageService {

    private final PageRep pageRep;

    public Page getPage(Long pageId){

        return pageRep.findByPageId(pageId);
    }

    public Page createPage(PageDTO pageDTO){

        Page page = new Page(pageDTO.getData());

        return pageRep.save(page);
    }


}
