package pl.camp.it.forum.services;

import pl.camp.it.forum.model.Post;
import pl.camp.it.forum.model.Title;

import java.util.List;
import java.util.Optional;
import java.util.Timer;

public interface ITitleService {
    List<String> getTitleList();
    List<Title> getTitle();
    Optional<Title> getTitleById(int id);

    void addTitle(Title title);
}
