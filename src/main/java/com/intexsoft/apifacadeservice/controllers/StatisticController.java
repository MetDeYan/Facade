package com.intexsoft.apifacadeservice.controllers;

import com.intexsoft.apifacadeservice.consumers.StatisticConsumerFeign;
import com.intexsoft.apifacadeservice.entity.statistic.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Statistic service controller.
 */
@RestController
@RequestMapping("/api/statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticConsumerFeign statisticConsumerFeign;

    /**
     * Hard send top statistics
     */
    @GetMapping("/top")
    public void hardSendTopStatistics() {
        statisticConsumerFeign.hardSendTopStatistics();
    }

    /**
     * Hard send anti-top statistics
     */
    @GetMapping("/antitop")
    public void hardSendAntiTopStatistics() {
        statisticConsumerFeign.hardSendAntiTopStatistics();
    }

    /**
     * Gets employee technologies statistic by employee id.
     *
     * @param id the employee id
     * @return the employee technologies statistic by employee id
     */
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole(@roleConfig.ekAdmin) and @permissionService.hasPermissions(@permissionConfig.employeeView)")
    public ResponseEntity<List<EmployeeTechnologyStatisticWithTechnologyDto>> getEmployeeTechnology(@PathVariable("id") String id, @RequestParam(required = false) DateRangeDto dateRange) {
        return statisticConsumerFeign.getEmployeeTechnology(id, dateRange);
    }

    /**
     * Search for technologies by name, among technologies that are not selected as ignored.
     *
     * @param technologyTitleDto
     * @return found technologies
     */
    @PostMapping(value = "/search")
    @PreAuthorize("hasRole(@roleConfig.ekAdmin) and @permissionService.hasPermissions(@permissionConfig.ratingExceptions)")
    public ResponseEntity<List<Technology>> getTechnologiesByName(@RequestBody TechnologyTitleDto technologyTitleDto) {
        return statisticConsumerFeign.getTechnologiesByName(technologyTitleDto);
    }

    /**
     * Search technologies by name, among technologies that are marked as ignored.
     *
     * @param technologyTitleDto
     * @param sort
     * @return found technologies
     */
    @PostMapping("/ignored")
    @PreAuthorize("hasRole(@roleConfig.ekAdmin) and @permissionService.hasPermissions(@permissionConfig.ratingExceptions)")
    public ResponseEntity<List<Technology>> getIgnoredTechnologies(@RequestBody TechnologyTitleDto technologyTitleDto, @RequestParam("sort") String sort) {
        return statisticConsumerFeign.getIgnoredTechnologies(technologyTitleDto, sort);
    }

    /**
     * Updating the technology for accounting (not accounting) in the TOP-50 and ANTITOP-50 ratings.
     *
     * @param technology
     * @return id of the changed technology for accounting (not accounting) in the TOP-50 and ANTITOP-50 ratings
     */
    @PutMapping("/ignored")
    @PreAuthorize("hasRole(@roleConfig.ekAdmin) and @permissionService.hasPermissions(@permissionConfig.ratingExceptions)")
    public ResponseEntity<TechnologyIdDto> upgradeIgnoredTechnology(@RequestBody Technology technology) {
        return statisticConsumerFeign.upgradeIgnoredTechnology(technology);
    }
}
