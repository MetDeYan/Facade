package com.intexsoft.apifacadeservice.controllers;

import com.intexsoft.apifacadeservice.consumers.MarketingConsumerFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * The Marketing service controller.
 */
@Slf4j
@RestController
@RequestMapping("/api/marketing")
@RequiredArgsConstructor
@PreAuthorize("hasRole(@roleConfig.ekAdmin)")
public class MarketingController {

    private final MarketingConsumerFeign marketingConsumerFeign;

    /**
     * API method to get file with all groups of technologies
     *
     * @return result file with all technologies groups
     */
    @GetMapping(value = "/groups")
    public ResponseEntity<ByteArrayResource> getAllTechnologies() {
        return marketingConsumerFeign.getAllTechnologies();
    }

    /**
     * API method to get file by <em>id</em> group of technology
     *
     * @param id the id of the technology group to search
     * @return result file with group of technology
     */
    @GetMapping(value = "/groups/{id}")
    public ResponseEntity<ByteArrayResource> getOneGroup(@PathVariable("id") String id) {
        return marketingConsumerFeign.getOneTechnologyGroup(id);
    }
}
