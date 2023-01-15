package pl.camp.it.forum.database;

import pl.camp.it.forum.model.Post;
import pl.camp.it.forum.model.Title;

import java.util.List;
import java.util.Optional;

public interface ITitleDAO {

    void addTitle(Title title);
    void editTopic(Title title);
    void removeTitle(Title title);
    List<String> getTitleList();
    List<Title> getTitle();
    Optional<Title> getTitleById(int id);

}
