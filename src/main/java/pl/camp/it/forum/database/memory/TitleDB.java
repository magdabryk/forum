package pl.camp.it.forum.database.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.forum.database.IPostDAO;
import pl.camp.it.forum.database.ITitleDAO;
import pl.camp.it.forum.model.Post;
import pl.camp.it.forum.model.Title;
import pl.camp.it.forum.sequence.ITitleIdSequence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TitleDB implements ITitleDAO {

    @Autowired
    IPostDAO postDAO;

    @Autowired
    ITitleIdSequence titleSequence;

    private final List<Title> titles = new ArrayList<>();

    public TitleDB(@Autowired IPostDAO postDAO, @Autowired ITitleIdSequence titleSequence) {
        this.postDAO = postDAO;
        this.titleSequence = titleSequence;
        this.titles.add(new Title(this.titleSequence.getId(),"Plotki", this.postDAO.getPostByTitleId(1), LocalDateTime.now(), 2));
        this.titles.add(new Title(this.titleSequence.getId(), "Prawdziwe plotki", this.postDAO.getPostByTitleId(2), LocalDateTime.now(), 2));
        this.titles.add(new Title(this.titleSequence.getId(),"Z pudelka", this.postDAO.getPostByTitleId(3), LocalDateTime.now(), 1));
    }

    @Override
    public List<Title> getTitle() {
        return titles;
    }

    @Override
    public void addTitle(Title title) {
        title.setPostList(this.postDAO.getPostByTitleId(title.getId()));
        this.titles.add(title);
    }

    @Override
    public void editTopic(Title title) {

    }

    @Override
    public void removeTitle(Title title) {

    }

    @Override
    public List<String> getTitleList() {
        List<String> titleList = new ArrayList<>();
        for(Title title : titles){
            titleList.add(title.getName());
        }
        return titleList;
    }

    @Override
    public Optional<Title> getTitleById(int id) {
        for(Title title :this.titles){
            if(title.getId() == id){
                return Optional.of(title);
            }
        }
        return Optional.empty();
    }
}
