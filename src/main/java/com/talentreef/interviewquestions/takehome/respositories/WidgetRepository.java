package com.talentreef.interviewquestions.takehome.respositories;

import com.talentreef.interviewquestions.takehome.models.Widget;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class WidgetRepository {
  // Since there's no actual database to query and validate unique Id's,
  // name validations had to be done manually to allow only unique names.

  private List<Widget> table = new ArrayList<>();

  public List<Widget> deleteByName(String name) {
    this.table = table.stream()
                      .filter((Widget widget) -> !name.equals(widget.getName()))
                      .collect(Collectors.toCollection(ArrayList::new));
    return table;
  }

  public List<Widget> findAll() {
    return table;
  }

  public Optional<Widget> findByName(String name) {
    Optional<Widget> result = table.stream()
                                   .filter((Widget widget) -> name.equals(widget.getName()))
                                   .findAny();
    return result;
  }

  // Even if findByName does almost the same, this boolean method would be
  // more useful in the future if requirements keep growing.
  public Boolean widgetExists(String name){
    return findByName(name).isPresent();
  }

  public Widget save(Widget widget) {
    if (widgetExists(widget.getName())){
      return null;
    }
    table.add(widget);
    return widget;
  }

  // Without a relational database easiest way to update is deleting and then saving
  public Widget update(Widget widgetToUpdate) {
    if (widgetExists(widgetToUpdate.getName())){
      table.add(widgetToUpdate);
      return widgetToUpdate;
    }
    return null;
  }

  public String deleteWidget(String widgetName) {
    if (widgetExists(widgetName)){
      deleteByName(widgetName);
      return widgetName;
    }
    return null;
  }
}
