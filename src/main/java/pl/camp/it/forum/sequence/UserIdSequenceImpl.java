package pl.camp.it.forum.sequence;

import org.springframework.stereotype.Component;

public class UserIdSequenceImpl implements IUserIdSequence{

    private int id = 0;

    @Override
    public int getId() {
        return ++id;
    }
}
