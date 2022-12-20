package com.intexsoft.apifacadeservice.consumers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Consumer to work with marketing requests
 */
@FeignClient(value = "marketing-consumer",
        url = "${marketing.service.url}")
public interface MarketingConsumerFeign {

    /**
     * Method to get result by all technologies groups
     *
     * @return result file
     */
    @GetMapping(value = "/groups")
    ResponseEntity<ByteArrayResource> getAllTechnologies();

    /**
     * Method to get marketing File with group of technology by id
     *
     * @param id id's group of technology to create marketing file
     * @return result file
     */
    @GetMapping(value = "/groups/{id}")
    ResponseEntity<ByteArrayResource> getOneTechnologyGroup(@PathVariable("id") String id);

}
