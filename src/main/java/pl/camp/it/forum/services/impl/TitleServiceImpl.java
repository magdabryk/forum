package pl.camp.it.forum.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.forum.database.ITitleDAO;
import pl.camp.it.forum.model.Post;
import pl.camp.it.forum.model.Title;
import pl.camp.it.forum.services.ITitleService;

import java.util.List;
import java.util.Optional;

@Service
public class TitleServiceImpl implements ITitleService {

    @Autowired
    ITitleDAO titleDAO;

    @Override
    public List<String> getTitleList() {
        return this.titleDAO.getTitleList();
    }

    @Override
    public List<Title> getTitle() {
        return this.titleDAO.getTitle();
    }

    @Override
    public Optional<Title> getTitleById(int id) {
        return this.titleDAO.getTitleById(id);
    }

    @Override
    public void addTitle(Title title) {
        this.titleDAO.addTitle(title);
    }
}
