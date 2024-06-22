package com.talentreef.interviewquestions.takehome.controllers;

import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.takehome.services.WidgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/widgets", produces = MediaType.APPLICATION_JSON_VALUE)
public class WidgetController {
  // Would add better error handling for each endpoint

  private final WidgetService widgetService;

  public WidgetController(WidgetService widgetService) {
    Assert.notNull(widgetService, "widgetService must not be null");
    this.widgetService = widgetService;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping
  public ResponseEntity<List<Widget>> getAllWidgets() {
    return ResponseEntity.ok(widgetService.getAllWidgets());
  }

  // Improving error responses for repeated values or not-found
  // values maybe could improve UX and UI development

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/get/{widgetName}")
  public ResponseEntity getWidget(@PathVariable(value = "widgetName") final String widgetName) {
    Optional<Widget> widget = widgetService.findWidget(widgetName);
    if (widget.isEmpty()) {
      return ResponseEntity.status(404).body("Widget now found");
    }
    return ResponseEntity.status(200).body(widget);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/save")
  public ResponseEntity createWidget(@RequestBody Widget widgetToSave) {
    Widget newWidget = widgetService.createWidget(widgetToSave);
    if (newWidget == null) {
      return ResponseEntity
              .status(500)
              .body("Widget's name already in use");
    }
    return ResponseEntity
            .status(200)
            .body(newWidget);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PutMapping("/update")
  public ResponseEntity updateWidget(@RequestBody Widget widgetToUpdate) {
    Widget updatedWidget = widgetService.updateWidget(widgetToUpdate);
    if (widgetToUpdate == null) {
      return ResponseEntity
              .status(500)
              .body("Widget not found");
    }
    System.out.println("Updated");
    return ResponseEntity
            .status(200)
            .body(updatedWidget);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @DeleteMapping("/delete/{widgetName}")
    public ResponseEntity deleteWidget(@PathVariable(value = "widgetName") final String widgetName) {
      String deletedWidget = widgetService.deleteWidget(widgetName);
      if (deletedWidget == null) {
        return ResponseEntity.status(404).body("Widget now found");
      }
      return ResponseEntity.status(200).body("Widget: " + deletedWidget + " has been deleted");
    }
}
