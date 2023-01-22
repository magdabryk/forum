package pl.camp.it.forum.sequence;

import org.springframework.stereotype.Component;


public class PostIdSequenceImpl implements IPostIdSequence{

    private int id = 0;

    @Override
    public int getId() {
        return ++id;
    }
}
