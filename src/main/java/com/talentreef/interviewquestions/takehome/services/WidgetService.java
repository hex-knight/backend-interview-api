package com.talentreef.interviewquestions.takehome.services;

import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.takehome.respositories.WidgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class WidgetService {

  private final WidgetRepository widgetRepository;

  @Autowired
  private WidgetService(WidgetRepository widgetRepository) {
    Assert.notNull(widgetRepository, "widgetRepository must not be null");
    this.widgetRepository = widgetRepository;
  }

  // Tried to keep service nice and clean to make it easier to follow

  // Would add data validation for Create and Update methods
  // like starRating is between 1 and 5,
  // name is within accepted length
  // and Age is price is a positive number


  public List<Widget> getAllWidgets() {
    return widgetRepository.findAll();
  }

  public Optional<Widget> findWidget(String nameToFind) {
    return widgetRepository.findByName(nameToFind);
  }

  public Widget createWidget(Widget newWidget) {
    return widgetRepository.save(newWidget);
  };

  public Widget updateWidget(Widget widgetToUpdate) {
    return widgetRepository.update(widgetToUpdate);
  }

  public String deleteWidget(String widgetName) {
    return widgetRepository.deleteWidget(widgetName);
  }
}
